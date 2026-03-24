package pl.coderslab.controller;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dto.MovieDTO;
import pl.coderslab.entity.Genre;
import pl.coderslab.entity.Movie;
import pl.coderslab.repository.MovieRepository;
import pl.coderslab.services.MovieService;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/{id}")
    @Transactional
    public MovieDTO getById(@PathVariable("id") Long id) {
        return MovieService.toDTO(movieRepository.findById(id).get());
    }

//    private static @NonNull MovieDTO toDTO(Movie movie) {
//        MovieDTO movieDTO = new MovieDTO();
//        movieDTO.setId(movie.getId());
//        movieDTO.setTitle(movie.getTitle());
//        movieDTO.setRating(movie.getRating());
//        movieDTO.setReleaseYear(movie.getReleaseYear());
//        List<String> genres = movie.getGenres().stream().map(Genre::toString).toList();
//        movieDTO.setGenres(genres);
//        return movieDTO;
//    }
}
