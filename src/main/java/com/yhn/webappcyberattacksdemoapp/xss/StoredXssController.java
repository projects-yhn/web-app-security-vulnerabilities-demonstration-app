package com.yhn.webappcyberattacksdemoapp.xss;

import com.yhn.webappcyberattacksdemoapp.xss.model.AddPostCommentRequest;
import com.yhn.webappcyberattacksdemoapp.xss.model.AllPostsResponse;
import com.yhn.webappcyberattacksdemoapp.xss.model.CommentDto;
import com.yhn.webappcyberattacksdemoapp.xss.model.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xss/stored")
public class StoredXssController {

    private final StoredXssService storedXssService;

    @GetMapping("/posts")
    public ResponseEntity<AllPostsResponse> getAllPosts() {
        return ResponseEntity.ok(storedXssService.getAllPosts());
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(storedXssService.getPostWithComment(postId));
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> addNewComment(@PathVariable Long postId, @RequestBody AddPostCommentRequest createComment) {
        return ResponseEntity.ok(storedXssService.addCommentToPost(postId, createComment));
    }
}
