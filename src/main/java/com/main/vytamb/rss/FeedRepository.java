package com.main.vytamb.rss;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedRepository extends CrudRepository<Feed, Long> {
    List<Feed> findAll();
    Optional<Feed> findById(Long id);
}
