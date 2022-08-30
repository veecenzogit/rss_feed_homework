package com.main.vytamb.rss;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewFeed {
    private String name;
    private String url;
}
