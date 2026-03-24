package pl.coderslab.controller;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dto.MovieDTO;
import pl.coderslab.repository.MovieRepository;
import pl.coderslab.mapper.MovieMapper;
import pl.coderslab.services.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    @Transactional
    public MovieDTO getById(@PathVariable("id") Long id) {
        return movieService.findById(id);
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
