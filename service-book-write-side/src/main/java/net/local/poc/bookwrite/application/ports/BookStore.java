package net.local.poc.bookwrite.application.ports;

import java.util.Optional;
import java.util.UUID;

import net.local.poc.bookwrite.domain.entities.Book;

public interface BookStore {
    Optional<Book> load(UUID bookId);
    Optional<Book> loadByTitle(String bookTitle);
    void save(Book book);
    void delete(UUID bookId);
}
