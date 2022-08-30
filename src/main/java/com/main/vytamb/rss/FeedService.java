package com.main.vytamb.rss;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedService {

    private final FeedRepository feedRepo;
    private final FeedItemRepository feedItemRepo;

    private final int recentArticleAmount;

    @Autowired
    public FeedService(FeedRepository repository, FeedItemRepository itemRepository, @Value("${article.amount}") int amount) {
        this.feedRepo = repository;
        this.feedItemRepo = itemRepository;
        this.recentArticleAmount = amount;
    }

    public NewFeed addFeed(NewFeed newFeed) {
        final URL url = validateUrl(newFeed.getUrl());
        if (null != url) {
            System.out.println(newFeed.getName());
            System.out.println(newFeed.getUrl());
            saveFeed(newFeed);
            return newFeed;
        } else {
            return null;
        }
    }

    public List<Feed> getAvailableFeeds() {
        return feedRepo.findAll();
    }

    public Feed getFeedById(Long feedId) {
        return feedRepo.findById(feedId).orElse(null);
    }

    public List<FeedItem> getSpecificFeedItems(Long feedId) {
        return feedItemRepo.findAllByFeedId(feedId).stream()
                .sorted(Comparator.comparing(FeedItem::getPublished))
                .limit(recentArticleAmount)
                .collect(Collectors.toList());
    }

    private void saveFeed(NewFeed newFeed) {
        final List<FeedItem> feedItemList = new ArrayList<>();
        final SyndFeed parsedFeed = parseFeed(validateUrl(newFeed.getUrl()));
        if (parsedFeed != null) {
            final Feed savedFeed = feedRepo.save(fromNewFeed(newFeed, parsedFeed.getTitle()));
            parsedFeed.getEntries().forEach(o -> {
                        if (o instanceof SyndEntry) {
                            feedItemList.add(fromEntry(savedFeed, o));
                        }
                    }
            );
            feedItemRepo.saveAll(feedItemList);
        }
    }

    private static FeedItem fromEntry(Feed feed, Object entry) {
        final FeedItem item = new FeedItem();
        item.setLink(((SyndEntry) entry).getLink());
        item.setDescription(((SyndEntry) entry).getDescription().getValue().trim());
        item.setTitle(((SyndEntry) entry).getTitle());
        item.setPublished(((SyndEntry) entry).getPublishedDate());
        item.setCreated(LocalDateTime.now());
        item.setFeedId(feed.getId());
        return item;
    }

    private static Feed fromNewFeed(NewFeed newFeed, String title) {
        final Feed feed = new Feed();
        feed.setName(newFeed.getName());
        feed.setUrl(newFeed.getUrl());
        feed.setTitle(title);
        feed.setCreated(LocalDateTime.now());
        return feed;
    }

    private URL validateUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException ex) {
            return null;
        }
    }

    private SyndFeed parseFeed(URL url) {
        try {
            return new SyndFeedInput().build(
                    DocumentBuilderFactory.newInstance()
                            .newDocumentBuilder()
                            .parse(url.openConnection().getInputStream())
            );
        } catch (MalformedURLException urlException) {
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
