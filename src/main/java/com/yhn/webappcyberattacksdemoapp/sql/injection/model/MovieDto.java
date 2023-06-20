package com.yhn.webappcyberattacksdemoapp.sql.injection.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private String director;
    private Integer year;
    private String genre;
    private String imageUrl;
}
