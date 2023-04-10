package com.yhn.webappcyberattacksdemoapp.xss.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPostCommentRequest {
    private String comment;
    private Long userId;
    private String userNames;
    private String userProfilePicture;
}
