package com.yhn.webappcyberattacksdemoapp.path.traversal;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/path-traversal")
public class PathTraversalController {

    private final PathTraversalService pathTraversalService;

    @GetMapping("/product/image")
    public ResponseEntity<ByteArrayResource> getImage(@RequestParam String name) {
        return ResponseEntity.ok(pathTraversalService.getProductImage(name));
    }
}
