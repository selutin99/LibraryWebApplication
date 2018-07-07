package com.example.demo.Entities.Genre;

import com.example.demo.Entities.Author.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Genre> findAll() {
        return jdbcTemplate.query("SELECT idGenre, title FROM genre",
                (rs, rowNum) -> new Genre(
                        rs.getLong("idGenre"),
                        rs.getString("title")
                )
        );
    }

    public void update(Genre genre) {
        jdbcTemplate.update("UPDATE genre SET title=? WHERE idGenre=?",
                genre.getTitle(), genre.getId());
    }

    public void insert(Genre genre) {
        jdbcTemplate.update("INSERT INTO genre (title) VALUES(?)",
                genre.getTitle());
    }

    public void delete(Genre genre) {
        jdbcTemplate.update("DELETE FROM genre WHERE idGenre=?", genre.getId());
    }
}
