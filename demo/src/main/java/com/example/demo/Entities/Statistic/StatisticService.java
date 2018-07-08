package com.example.demo.Entities.Statistic;

import com.example.demo.Entities.Genre.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Statistic> findAll() {
        return jdbcTemplate.query("SELECT genre.title, COUNT(bookGenre) AS quantity FROM genre " +
                        "LEFT JOIN book ON genre.idGenre = book.bookGenre GROUP BY title",
                (rs, rowNum) -> new Statistic(
                        rs.getString("title"),
                        rs.getString("quantity")
                )
        );
    }
}
