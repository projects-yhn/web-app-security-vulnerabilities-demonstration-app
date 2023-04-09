package com.yhn.webappcyberattacksdemoapp.command.injection.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private String fileName;
    private Boolean isDirectory;
}
