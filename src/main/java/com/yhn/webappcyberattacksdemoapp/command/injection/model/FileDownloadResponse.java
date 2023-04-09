package com.yhn.webappcyberattacksdemoapp.command.injection.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDownloadResponse {
    private String fileName;
    private String fileContent;
}
