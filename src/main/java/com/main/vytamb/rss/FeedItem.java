package com.main.vytamb.rss;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class FeedItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long feedId;
    private String title;
    private String link;
    @Lob
    private String description;

    private Date published;
    @CreatedDate
    private LocalDateTime created;
}
