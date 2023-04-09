package com.yhn.webappcyberattacksdemoapp.command.injection.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListFilesResponse {
    private String path;
    private List<FileResponse> files;
}
