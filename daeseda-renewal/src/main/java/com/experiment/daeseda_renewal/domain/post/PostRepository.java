package com.experiment.daeseda_renewal.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

  @Query("""
      select p
      from Post p
      where (
        :q is null or :q = ''
        or lower(p.postTitle)   like concat('%', lower(:q), '%')
        or lower(p.postContent) like concat('%', lower(:q), '%')
      )
      """)
  Page<Post> search(@Param("q") String q, Pageable pageable);
}
