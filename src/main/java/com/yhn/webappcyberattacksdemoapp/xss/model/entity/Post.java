package com.yhn.webappcyberattacksdemoapp.xss.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="xss_stored_posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_profile_pictire")
    private String userProfilePicture;

    @Column(name = "user_names")
    private String userNames;

    @Column(name = "creation_ts")
    private Instant creationTs;
}
