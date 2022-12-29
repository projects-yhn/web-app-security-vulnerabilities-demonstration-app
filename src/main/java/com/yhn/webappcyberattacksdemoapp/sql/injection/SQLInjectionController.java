package com.yhn.webappcyberattacksdemoapp.sql.injection;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sql-injection")
@RequiredArgsConstructor
public class SQLInjectionController {

    private final SQLInjectionService sqlInjectionService;

    // get(query param sql injection)
    @GetMapping("/user-info")
    public ResponseEntity<Object> getUserDataByUsername(@RequestParam String username) {
        return ResponseEntity.ok(sqlInjectionService.getUserInfoByUsernameSqlInjection(username));
    }

    @GetMapping("/union-attack/movie")
    public ResponseEntity<Object> getMovieUnionWithUsers(@RequestParam String title) {
        Object response = sqlInjectionService.getMovieUnionUsersTableSqlInjection(title);
        return ResponseEntity.ok(response);
    }
}
