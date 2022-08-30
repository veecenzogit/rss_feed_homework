package com.main.vytamb.rss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RssController {

    private final FeedService service;

    @Autowired
    public RssController(FeedService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        return "index";
    }

    @GetMapping(value = "/add")
    public String addNewRssFeed(Model m) {
        m.addAttribute("newFeed", NewFeed.builder().build());
        return "add";
    }

    @PostMapping(value = "/add")
    public RedirectView addNewRssFeed(@ModelAttribute("newFeed") NewFeed newFeed, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/add", true);
        final NewFeed savedFeed = service.addFeed(newFeed);
        redirectAttributes.addFlashAttribute("savedFeed", savedFeed);
        if (null != savedFeed) {
            redirectAttributes.addFlashAttribute("newFeedAdded", true);
        } else {
            redirectAttributes.addFlashAttribute("malformedUrl", true);
        }
        return redirectView;
    }

    @GetMapping(value = "/view")
    public String viewAllFeeds(Model model) {
        model.addAttribute("feeds", service.getAvailableFeeds());
        return "view-feeds";
    }

    @GetMapping(value = "/view/{feedId}")
    public String viewAllFeedItem(@PathVariable Long feedId, Model model) {
        model.addAttribute("feedInfo", FeedViewResponse.fromFeed(service.getFeedById(feedId)));
        model.addAttribute("feedItems", service.getSpecificFeedItems(feedId));
        return "view-single";
    }

}
