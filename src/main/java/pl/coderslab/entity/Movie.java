package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "movies")
@Setter
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(name = "release_year")
    private int releaseYear;

    @ManyToMany
    @JoinTable(name = "movies_genres")
    @JsonIgnore
    private List<Genre> genres;

    private double rating;
}
