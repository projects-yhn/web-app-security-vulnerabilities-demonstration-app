package com.yhn.webappcyberattacksdemoapp.xss.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String userProfilePicture;
    private String userNames;
    private Instant creationTs;
    private List<CommentDto> comments;
}
