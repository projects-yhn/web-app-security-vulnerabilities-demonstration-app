package com.yhn.webappcyberattacksdemoapp.xss.repository;

import com.yhn.webappcyberattacksdemoapp.xss.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByPostId(Long postId);
}
