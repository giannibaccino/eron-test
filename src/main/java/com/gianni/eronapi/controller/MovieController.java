package com.gianni.eronapi.controller;

import com.gianni.eronapi.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/directors")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDirectors(@RequestParam(defaultValue = "0") int threshold) {
        List<String> directors = movieService.getDirectors(threshold);
        return new ResponseEntity<>(Map.of("directors", directors), HttpStatus.OK);
    }
}
