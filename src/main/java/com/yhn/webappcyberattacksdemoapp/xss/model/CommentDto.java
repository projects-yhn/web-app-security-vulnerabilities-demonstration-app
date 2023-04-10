package com.yhn.webappcyberattacksdemoapp.xss.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Long userId;
    private String userProfilePicture;
    private String userNames;
    private String content;
    private Instant creationTs;
}
