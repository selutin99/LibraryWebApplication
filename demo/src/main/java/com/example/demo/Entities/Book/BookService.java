package com.example.demo.Entities.Book;

import com.example.demo.Entities.Author.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Author> findAll() {
        return jdbcTemplate.query("SELECT idAuthor, firstName, lastName, patronymic FROM author",
                (rs, rowNum) -> new Author(
                        rs.getLong("idAuthor"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("patronymic")
                )
        );
    }

    public void update(Author author) {
        jdbcTemplate.update("UPDATE author SET firstName=?, lastName=?, patronymic=? WHERE idAuthor=?",
                author.getFirstName(), author.getLastName(), author.getPatronymic(), author.getId());
    }

    public void insert(Author author) {
        jdbcTemplate.update("INSERT INTO author (firstName, lastName, patronymic) VALUES(?,?,?)",
                author.getFirstName(),author.getLastName(),author.getPatronymic());
    }

    public void delete(Author author) {
        jdbcTemplate.update("DELETE FROM author WHERE idAuthor=?", author.getId());
    }
}
