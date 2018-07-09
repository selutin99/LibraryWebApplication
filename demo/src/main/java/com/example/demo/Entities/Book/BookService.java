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
        return jdbcTemplate.query("SELECT idBook, name, lastName, title, publisher, year, city FROM book INNER JOIN author ON author.idAuthor = book.bookAuthor INNER JOIN genre ON genre.idGenre = book.bookGenre",
                (rs, rowNum) -> new Book(
                        rs.getLong("idBook"),
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getString("title"),
                        rs.getString("publisher"),
                        rs.getString("year"),
                        rs.getString("city")
                )
        );
    }

    public List<Book> findAllByName(String value) {
        return jdbcTemplate.query("SELECT idBook, name, lastName, title, publisher, year, city FROM book INNER JOIN author ON author.idAuthor = book.bookAuthor INNER JOIN genre ON genre.idGenre = book.bookGenre WHERE name LIKE '%"+value+"%'",
                (rs, rowNum) -> new Book(
                        rs.getLong("idBook"),
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getString("title"),
                        rs.getString("publisher"),
                        rs.getString("year"),
                        rs.getString("city")
                )
        );
    }

    public List<Book> findAllByPublisher(String value) {
        return jdbcTemplate.query("SELECT idBook, name, lastName, title, publisher, year, city FROM book INNER JOIN author ON author.idAuthor = book.bookAuthor INNER JOIN genre ON genre.idGenre = book.bookGenre WHERE publisher LIKE '%"+value+"%'",
                (rs, rowNum) -> new Book(
                        rs.getLong("idBook"),
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getString("title"),
                        rs.getString("publisher"),
                        rs.getString("year"),
                        rs.getString("city")
                )
        );
    }

    public List<Book> findAllByAuthor(String value) {
        return jdbcTemplate.query("SELECT idBook, name, lastName, title, publisher, year, city FROM book INNER JOIN author ON author.idAuthor = book.bookAuthor INNER JOIN genre ON genre.idGenre = book.bookGenre WHERE lastName LIKE '%"+value+"%'",
                (rs, rowNum) -> new Book(
                        rs.getLong("idBook"),
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getString("title"),
                        rs.getString("publisher"),
                        rs.getString("year"),
                        rs.getString("city")
                )
        );
    }

    public void update(Book book, String idAuthor, String idGenre) {
        jdbcTemplate.update("UPDATE book SET name=?, bookAuthor=?, bookGenre=?, publisher=?, year=?, city=? WHERE idBook=?",
                book.getName(), idAuthor, idGenre, book.getPublisher(), book.getYear(), book.getCity(), book.getId());
    }

    public void insert(Book book, String idAuthor, String idGenre) {
        jdbcTemplate.update("INSERT INTO book (name, bookAuthor, bookGenre, publisher, year, city) VALUES(?,?,?,?,?,?)",
                book.getName(),idAuthor,idGenre,book.getPublisher(),book.getYear(),book.getCity());
    }

    public void delete(Book book) {
        jdbcTemplate.update("DELETE FROM book WHERE idBook=?", book.getId());
    }

    public String getGenreID(String value){
        return jdbcTemplate.queryForObject("SELECT idGenre FROM genre WHERE title = ?",new Object[] {value}, String.class);
    }

    public String getAuthorID(String value){
        return jdbcTemplate.queryForObject("SELECT idAuthor FROM author WHERE lastName = ?",new Object[] {value}, String.class);
    }
}
