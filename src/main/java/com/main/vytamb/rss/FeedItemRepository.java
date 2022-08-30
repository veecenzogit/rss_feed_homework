package com.main.vytamb.rss;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedItemRepository extends CrudRepository<FeedItem, Long> {
    List<FeedItem> findAllByFeedId(Long feedId);

    Optional<FeedItem> findById(Long id);
}
