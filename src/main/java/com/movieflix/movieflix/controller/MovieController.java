package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.controller.request.MovieRequest;
import com.movieflix.movieflix.controller.response.MovieResponse;
import com.movieflix.movieflix.entity.Movie;
import com.movieflix.movieflix.mapper.MovieMapper;
import com.movieflix.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movieflix/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest movieRequest){
        Movie savedMovie = movieService.save(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping
    public ResponseEntity <List<MovieResponse>> findAll(){
        return ResponseEntity.ok(movieService.findAll().stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }
}
