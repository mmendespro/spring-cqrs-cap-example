package net.local.poc.bookwrite.infrastructure.gateways;

import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import net.local.poc.bookwrite.application.ports.BookStore;
import net.local.poc.bookwrite.domain.entities.Book;

@Repository
public class JdbcBookStoreGateway implements BookStore {

    private final JdbcClient jdbcClient;

    public JdbcBookStoreGateway(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Book> load(UUID bookId) {
        return jdbcClient.sql("SELECT BOOK_ID, TITLE, AUTHOR, BOOK_YEAR FROM BOOKS WHERE BOOK_ID = :bookId")
                         .param("bookId", bookId)
                         .query((rs, rowNum) -> new Book(UUID.fromString(rs.getString("BOOK_ID")), rs.getString("TITLE"), rs.getString("AUTHOR"), rs.getInt("BOOK_YEAR")) ).optional();
    }

    @Override
    public Optional<Book> loadByTitle(String bookTitle) {
        return jdbcClient.sql("SELECT BOOK_ID, TITLE, AUTHOR, BOOK_YEAR FROM BOOKS WHERE TITLE = :title")
                         .param("title", bookTitle)
                         .query((rs, rowNum) -> new Book(UUID.fromString(rs.getString("BOOK_ID")), rs.getString("TITLE"), rs.getString("AUTHOR"), rs.getInt("BOOK_YEAR")) ).optional();
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
