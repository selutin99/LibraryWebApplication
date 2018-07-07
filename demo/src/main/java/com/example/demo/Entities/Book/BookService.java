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


    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT idBook, title, bookAuthor, bookGenre, publisher, year, city FROM book",
                (rs, rowNum) -> new Book(
                        rs.getLong("idBook"),
                        rs.getString("title"),
                        rs.getString("bookAuthor"),
                        rs.getString("bookGenre"),
                        rs.getString("publisher"),
                        rs.getString("year"),
                        rs.getString("city")
                )
        );
    }

    public List<Book> findAllByName(String value) {
        return jdbcTemplate.query("SELECT idBook, title, bookAuthor, bookGenre, publisher, year, city " +
                        "FROM book WHERE title LIKE '%"+value+"%'",
                (rs, rowNum) -> new Book(
                        rs.getLong("idBook"),
                        rs.getString("title"),
                        rs.getString("bookAuthor"),
                        rs.getString("bookGenre"),
                        rs.getString("publisher"),
                        rs.getString("year"),
                        rs.getString("city")
                )
        );
    }

    public List<Book> findAllByPublisher(String value) {
        return jdbcTemplate.query("SELECT idBook, title, bookAuthor, bookGenre, publisher, year, city " +
                        "FROM book WHERE publisher LIKE '%"+value+"%'",
                (rs, rowNum) -> new Book(
                        rs.getLong("idBook"),
                        rs.getString("title"),
                        rs.getString("bookAuthor"),
                        rs.getString("bookGenre"),
                        rs.getString("publisher"),
                        rs.getString("year"),
                        rs.getString("city")
                )
        );
    }

    public List<Book> findAllByAuthor(String value) {
        return jdbcTemplate.query("SELECT idBook, title, bookAuthor, bookGenre, publisher, year, city " +
                        "FROM book WHERE bookAuthor LIKE '%"+value+"%'",
                (rs, rowNum) -> new Book(
                        rs.getLong("idBook"),
                        rs.getString("title"),
                        rs.getString("bookAuthor"),
                        rs.getString("bookGenre"),
                        rs.getString("publisher"),
                        rs.getString("year"),
                        rs.getString("city")
                )
        );
    }

    public void update(Book book) {
        jdbcTemplate.update("UPDATE book SET " +
                        "title=?, bookAuthor=?, bookGenre=?, publisher=?, year=?, city=?  " +
                        "WHERE idAuthor=?",
                book.getTitle(), book.getBookAuthor(), book.getBookGenre(), book.getPublisher(), book.getYear(), book.getCity());
    }

    public void insert(Book book) {
        jdbcTemplate.update("INSERT INTO book (title, bookAuthor, bookGenre, publisher, year, city) VALUES(?,?,?,?,?,?)",
                book.getTitle(),book.getBookAuthor(),book.getBookGenre(),book.getPublisher(),book.getYear(),book.getCity());
    }

    public void delete(Book book) {
        jdbcTemplate.update("DELETE FROM book WHERE idBook=?", book.getId());
    }
}
