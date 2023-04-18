package com.mparikh.movieApi.controller;

import com.mparikh.movieApi.exception.ResourceNotFoundException;
import com.mparikh.movieApi.model.Movie;
import com.mparikh.movieApi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    // get all movies endpoint
    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    // create Movie rest api
    @PostMapping("/movie")
    public Movie createMovies(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    // get Movie by id rest api
    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id :" + id));
        return ResponseEntity.ok(movie);
    }

    // update movie rest api

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovies(@PathVariable Long id, @RequestBody Movie movieDetails){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id :" + id));

        movie.setTitle(movieDetails.getTitle());
        movie.setDirector(movieDetails.getDirector());
        Movie updatedMovie = movieRepository.save(movie);
        return ResponseEntity.ok(updatedMovie);
    }

    // delete movie rest api
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMovie(@PathVariable Long id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not exist with id :" + id));

        movieRepository.delete(movie);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
