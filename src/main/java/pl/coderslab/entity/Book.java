package pl.coderslab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5)
    @NotBlank
    private String title;

    @Min(1)
    @Max(10)
    private int rating;

    @Size(max = 600)
    private String description;

    @ManyToOne
    @NotNull
    private Publisher publisher;

    @Min(1)
    private Integer pages;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "books_authors")
    private List<Author> authors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany
    @JoinColumn(name = "comments")
    private List<Comment> comments;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("<br>")
                .append("Title: ").append(title).append("<br>")
                .append("Rating: ").append(rating).append("<br>")
                .append("Description: ").append(description).append("<br>");
        return sb.toString();
    }
}
