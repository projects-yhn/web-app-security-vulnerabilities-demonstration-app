package com.yhn.webappcyberattacksdemoapp.xss.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllPostsResponse {
    private List<PostDto> posts;
}
