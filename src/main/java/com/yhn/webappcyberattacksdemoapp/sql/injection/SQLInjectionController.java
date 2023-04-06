package com.yhn.webappcyberattacksdemoapp.sql.injection;

import com.yhn.webappcyberattacksdemoapp.sql.injection.model.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<MovieDto>> getMovieUnionWithUsers(@RequestParam String title) {
        List<MovieDto> response = sqlInjectionService.getMoviesByTitle(title);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> response = sqlInjectionService.getAllMovies();
        return ResponseEntity.ok(response);
    }

}
