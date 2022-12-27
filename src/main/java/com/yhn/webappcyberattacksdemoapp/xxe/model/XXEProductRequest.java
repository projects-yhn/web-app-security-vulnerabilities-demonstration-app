package com.yhn.webappcyberattacksdemoapp.xxe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Product")
public class XXEProductRequest {
    private String name;
    private String category;
}
