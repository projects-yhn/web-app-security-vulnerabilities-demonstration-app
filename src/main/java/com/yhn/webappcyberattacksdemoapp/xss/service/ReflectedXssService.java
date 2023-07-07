package com.yhn.webappcyberattacksdemoapp.xss.service;

import com.yhn.webappcyberattacksdemoapp.xss.model.PostDto;
import com.yhn.webappcyberattacksdemoapp.xss.model.entity.Post;
import com.yhn.webappcyberattacksdemoapp.xss.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReflectedXssService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public List<PostDto> searchPostByTitle(String postTitle) {
        List<Post> foundPosts = postRepository.getAllByTitleContains(postTitle);
        List<PostDto> posts = new ArrayList<>();

        for (Post post : foundPosts) {
            posts.add(modelMapper.map(post, PostDto.class));
        }
        return posts;
    }
}
