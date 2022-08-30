package com.main.vytamb.rss;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FeedViewResponse {
    private String name;
    private String url;
    private LocalDateTime updated;
    private int articleCount;

    public static FeedViewResponse fromFeed(Feed feed) {
        return FeedViewResponse.builder()
                .name(feed.getName())
                .updated(feed.getCreated())
                .url(feed.getUrl())
                .articleCount(feed.getFeedItems().size())
                .build();
    }
}
