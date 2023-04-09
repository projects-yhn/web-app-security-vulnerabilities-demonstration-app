package com.yhn.webappcyberattacksdemoapp.command.injection;

import com.yhn.webappcyberattacksdemoapp.command.injection.model.FileDownloadResponse;
import com.yhn.webappcyberattacksdemoapp.command.injection.model.FileResponse;
import com.yhn.webappcyberattacksdemoapp.command.injection.model.ListFilesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandInjectionService {
    private static final String RESOURCE_PATH = "profile";
    private static final List<String> windowsCommands = new ArrayList<>(Arrays.asList("cmd.exe", "/c", "dir", "/b"));
    private static final List<String> unixCommands = new ArrayList<>(Arrays.asList("/bin/sh ", "-c", "ls"));
    private static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("windows");

    public ListFilesResponse getFilesAndDirectoriesFromPath(String searchedPath) {
        String[] searchedPathSplit = {};
        if (StringUtils.hasText(searchedPath)) {
            String decodedSearchPath = new String(Base64.getDecoder().decode(searchedPath));
            searchedPathSplit = decodedSearchPath.split(" ");
        }

        String resourceRootPath = getResourcePath();
        if (resourceRootPath == null) {
            return null;
        }
        if (IS_WINDOWS && resourceRootPath.startsWith("/")) {
            resourceRootPath = resourceRootPath.substring(1);
        }
        Path osSpecificPath = null;
        List<String> commands = getOSSpecificCommands(IS_WINDOWS);
        if (searchedPathSplit.length == 1) {
            osSpecificPath = Paths.get(resourceRootPath, searchedPathSplit[0]);
//            commands.add(osSpecificPath.toString());
        } else {
            osSpecificPath = Paths.get(resourceRootPath);
//            commands.add(osSpecificPath.toString());
            if (searchedPathSplit.length > 0) {
                commands.addAll(Arrays.asList(searchedPathSplit));
            }
        }
        List<FileResponse> fileList = new ArrayList<>();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(commands);
        processBuilder.directory(new File(osSpecificPath.toString()));
        try {
            Process process = processBuilder.start();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                boolean isDirectory = Files.isDirectory(Paths.get(osSpecificPath.toString(), line));
                fileList.add(new FileResponse(line, isDirectory));
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ListFilesResponse(osSpecificPath.toString(), fileList);
    }

    public FileDownloadResponse downloadFile(String filePath) {

        String resourceRootPath = getResourcePath();
        if (resourceRootPath == null) {
            return null;
        }
        if (IS_WINDOWS && resourceRootPath.startsWith("/")) {
            resourceRootPath = resourceRootPath.substring(1);
        }
        Path osSpecificPath = Paths.get(resourceRootPath, filePath);
        try {
            byte[] fileBytes = Files.readAllBytes(osSpecificPath);
            String encodedFile = new String(Base64.getEncoder().encode(fileBytes));
            return new FileDownloadResponse(null, encodedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getResourcePath() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            return classLoader.getResource(RESOURCE_PATH).toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getOSSpecificCommands(boolean isWindows) {
        return new ArrayList<>(isWindows ? windowsCommands : unixCommands);
    }
}
