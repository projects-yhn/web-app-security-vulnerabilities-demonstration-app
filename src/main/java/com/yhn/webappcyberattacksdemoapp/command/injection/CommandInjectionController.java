package com.yhn.webappcyberattacksdemoapp.command.injection;

import com.yhn.webappcyberattacksdemoapp.command.injection.model.FileDownloadRequest;
import com.yhn.webappcyberattacksdemoapp.command.injection.model.FileDownloadResponse;
import com.yhn.webappcyberattacksdemoapp.command.injection.model.ListFilesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/command-injection")
public class CommandInjectionController {
    private final CommandInjectionService commandInjectionService;

    @GetMapping("/files")
    public ResponseEntity<ListFilesResponse> getFiles(@RequestParam(required = false, name = "path") String path) {
        return ResponseEntity.ok(commandInjectionService.getFilesAndDirectoriesFromPath(path));
    }

    @PostMapping("/files/file")
    public ResponseEntity<FileDownloadResponse> downloadFile(@RequestBody FileDownloadRequest request) {
        return ResponseEntity.ok(commandInjectionService.downloadFile(request.getFilePath()));
    }
}
