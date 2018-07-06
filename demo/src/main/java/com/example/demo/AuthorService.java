package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorService {

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

    public List<Author> findAll(String filter) {
        return jdbcTemplate.query("SELECT idAuthor, firstName, lastName, patronymic " +
                        "FROM author WHERE firstName LIKE '"+filter+"%'",
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

}