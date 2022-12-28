package com.yhn.webappcyberattacksdemoapp.path.traversal;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PathTraversalService {
    private static final String IMAGES_DIRECTORY_PATH = "src/main/resources/images";
    private static final boolean PROTECTION_ENABLED = true;

    public ByteArrayResource getProductImage(String imageName) {
        Path imagePath = Paths.get(IMAGES_DIRECTORY_PATH, imageName);
        if (PROTECTION_ENABLED && !imagePath.normalize().startsWith(IMAGES_DIRECTORY_PATH)) {
            throw new RuntimeException("WARNING PATH TRAVERSAL ATTACK");
        }
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayResource(imageBytes);
    }
}
