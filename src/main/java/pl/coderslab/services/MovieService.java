package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.dto.MovieDTO;
import pl.coderslab.mapper.MovieMapper;
import pl.coderslab.repository.MovieRepository;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }


    public MovieDTO findById(Long id) {
        return movieMapper.toDTO(movieRepository.findById(id).get());
    }
}
