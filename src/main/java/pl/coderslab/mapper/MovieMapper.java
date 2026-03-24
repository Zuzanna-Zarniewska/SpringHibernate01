package pl.coderslab.mapper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import pl.coderslab.dto.MovieDTO;
import pl.coderslab.entity.Genre;
import pl.coderslab.entity.Movie;

import java.util.List;

@Component
public class MovieMapper {
    public MovieDTO toDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setRating(movie.getRating());
        movieDTO.setReleaseYear(movie.getReleaseYear());
        List<String> genres = movie.getGenres().stream().map(Genre::toString).toList();
        movieDTO.setGenres(genres);
        return movieDTO;
    }
}
