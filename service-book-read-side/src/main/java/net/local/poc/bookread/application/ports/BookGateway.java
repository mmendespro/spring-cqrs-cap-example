package net.local.poc.bookread.application.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.local.poc.bookread.domain.entities.Book;

public interface BookGateway {
    List<Book> load();
    Optional<Book> load(UUID bookId);
    Optional<Book> loadByTitle(String bookTitle);
    void save(Book book);
    void delete(UUID bookId);
}
