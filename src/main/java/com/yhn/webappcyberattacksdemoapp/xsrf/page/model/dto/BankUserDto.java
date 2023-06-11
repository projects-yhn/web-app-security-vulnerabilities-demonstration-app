package com.yhn.webappcyberattacksdemoapp.xsrf.page.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankUserDto {
    private Long id;
    private String email;
    private String names;
    private String usernames;
}
