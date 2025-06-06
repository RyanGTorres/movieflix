package com.movieflix.movieflix.service;

import com.movieflix.movieflix.entity.Movie;
import com.movieflix.movieflix.repository.MovieRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRespository movieRespository;

    public Movie save(Movie movie){
        return movieRespository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRespository.findAll();
    }
}
