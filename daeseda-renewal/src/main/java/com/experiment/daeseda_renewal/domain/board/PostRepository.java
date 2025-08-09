package com.experiment.daeseda_renewal.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

  @Query("select p from Post p where (:q is null or :q = '' or " +
      "lower(p.postTitle) like lower(concat('%',:q,'%')) or " +
      "lower(p.postContent) like lower(concat('%',:q,'%'))")
  Page<Post> search(String q, Pageable pageable);
}
