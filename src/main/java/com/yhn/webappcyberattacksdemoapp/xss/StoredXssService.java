package com.yhn.webappcyberattacksdemoapp.xss;

import com.yhn.webappcyberattacksdemoapp.xss.model.AddPostCommentRequest;
import com.yhn.webappcyberattacksdemoapp.xss.model.AllPostsResponse;
import com.yhn.webappcyberattacksdemoapp.xss.model.CommentDto;
import com.yhn.webappcyberattacksdemoapp.xss.model.PostDto;
import com.yhn.webappcyberattacksdemoapp.xss.model.entity.Comment;
import com.yhn.webappcyberattacksdemoapp.xss.model.entity.Post;
import com.yhn.webappcyberattacksdemoapp.xss.repository.CommentRepository;
import com.yhn.webappcyberattacksdemoapp.xss.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoredXssService {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public AllPostsResponse getAllPosts() {
        List<Post> allPosts = postRepository.findAll();
        List<PostDto> responsePosts = new ArrayList<>();
        for (Post post : allPosts) {
            responsePosts.add(modelMapper.map(post, PostDto.class));
        }
        return new AllPostsResponse(responsePosts);
    }

    public PostDto getPostWithComment(Long id) {
        List<Comment> comments = commentRepository.getAllByPostId(id);
        PostDto postDto = null;
        for (Comment comment : comments) {
            if (postDto == null) {
                postDto = modelMapper.map(comment.getPost(), PostDto.class);
                postDto.setComments(new ArrayList<>());
            }
            postDto.getComments().add(modelMapper.map(comment, CommentDto.class));
        }
        return postDto;
    }

    public List<CommentDto> addCommentToPost(Long postId, AddPostCommentRequest addCommentToPost) {
        Post post = postRepository.findById(postId)
                .orElseThrow();
        Comment comment = new Comment();
        comment.setUserId(addCommentToPost.getUserId());
        comment.setUserProfilePicture(addCommentToPost.getUserProfilePicture());
        comment.setUserNames(addCommentToPost.getUserNames());
        comment.setContent(addCommentToPost.getComment());
        comment.setPost(post);
        comment.setCreationTs(Instant.now());
        commentRepository.save(comment);

        List<Comment> comments = commentRepository.getAllByPostId(postId);
        List<CommentDto> allCommentsDto = new ArrayList<>();
        for (Comment c : comments) {
            allCommentsDto.add(modelMapper.map(c, CommentDto.class));
        }
        return allCommentsDto;
    }

}
