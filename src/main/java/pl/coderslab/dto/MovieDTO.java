package pl.coderslab.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.entity.Genre;

import java.util.List;

@Setter
@Getter
public class MovieDTO {
    private Long id;
    private String title;
    private int releaseYear;
    private List<String> genres;
    private double rating;
}
