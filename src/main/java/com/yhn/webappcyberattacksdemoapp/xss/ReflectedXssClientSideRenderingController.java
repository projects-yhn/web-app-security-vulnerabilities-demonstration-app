package com.yhn.webappcyberattacksdemoapp.xss;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xss/reflected/client-side")
public class ReflectedXssClientSideRenderingController {

    @GetMapping("/books")
    public ResponseEntity<Object> search(@RequestParam(value = "name", required = false) String searchValue) {
        Map<String, Object> responseBody = new HashMap<>();

        if (searchValue != null && searchValue.contains("<script>")) {
            responseBody.put("search", searchValue);
            return ResponseEntity.badRequest().body(responseBody);
        } else {
            responseBody.put("author", "Yalco");
            responseBody.put("releaseDate", 1998);
            responseBody.put("Title", "Poems");
            responseBody.put("Publisher", "JG");
            return ResponseEntity.ok(responseBody);
        }
    }
}

