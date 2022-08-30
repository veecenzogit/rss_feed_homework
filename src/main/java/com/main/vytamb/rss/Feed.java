package com.main.vytamb.rss;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//Singleton
public class Feed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String url;
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "feedId")
    private List<FeedItem> feedItems;

    @CreatedDate
    private LocalDateTime created;
}
