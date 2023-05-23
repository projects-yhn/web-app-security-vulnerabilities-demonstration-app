package com.yhn.webappcyberattacksdemoapp.xss.repository;

import com.yhn.webappcyberattacksdemoapp.xss.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getAllByTitleContains(String postTitle);
}
