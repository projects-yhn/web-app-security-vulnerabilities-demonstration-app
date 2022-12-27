package com.yhn.webappcyberattacksdemoapp.xxe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XXEProductResponse {
    private Long id;
    private String name;
    private String category;
    private String imageUrl;
    private BigDecimal price;
    private String description;
}
