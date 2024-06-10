package net.local.poc.bookread.infrastructure.gateways;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import net.local.poc.bookread.application.ports.BookGateway;
import net.local.poc.bookread.domain.entities.Book;

@Repository
public class JdbcBookGateway implements BookGateway {

    private final JdbcClient jdbcClient;

    public JdbcBookGateway(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Book> load() {
        return jdbcClient.sql("SELECT BOOK_ID, TITLE, AUTHOR, BOOK_YEAR FROM BOOKS")
                         .query((rs, rowNum) -> new Book(UUID.fromString(rs.getString("BOOK_ID")), 
                                                         rs.getString("TITLE"), 
                                                         rs.getString("AUTHOR"), 
                                                         rs.getInt("BOOK_YEAR")))
                         .list();

    }

    @Override
    public Optional<Book> load(UUID bookId) {
        return jdbcClient.sql("SELECT BOOK_ID, TITLE, AUTHOR, BOOK_YEAR FROM BOOKS WHERE BOOK_ID = :bookId")
                         .param("bookId", bookId)
                         .query((rs, rowNum) -> new Book(UUID.fromString(rs.getString("BOOK_ID")), 
                                                         rs.getString("TITLE"), 
                                                         rs.getString("AUTHOR"), 
                                                         rs.getInt("BOOK_YEAR")))
                         .optional();

    }

    @Override
    public Optional<Book> loadByTitle(String bookTitle) {
        return jdbcClient.sql("SELECT BOOK_ID, TITLE, AUTHOR, BOOK_YEAR FROM BOOKS WHERE TITLE = :title")
                         .param("title", bookTitle)
                         .query((rs, rowNum) -> new Book(UUID.fromString(rs.getString("BOOK_ID")), 
                                                         rs.getString("TITLE"), 
                                                         rs.getString("AUTHOR"), 
                                                         rs.getInt("BOOK_YEAR")))
                         .optional();

    }

    @Override
    public void save(Book book) {
        jdbcClient.sql("INSERT INTO BOOKS(BOOK_ID, TITLE, AUTHOR, BOOK_YEAR) VALUES (:bookId, :title, :author, :bookYear)")
                  .param("bookId", book.getBookId())
                  .param("title", book.getTitle())
                  .param("author", book.getAuthor())
                  .param("bookYear", book.getYear()).update();
    }

    @Override
    public void delete(UUID bookId) {
        jdbcClient.sql("DELETE FROM BOOKS WHERE BOOK_ID = :bookId")
                  .param("bookId", bookId).update();
    }   
}