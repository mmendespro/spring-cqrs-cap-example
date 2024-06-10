package net.local.poc.bookwrite.domain.entities;

import java.util.UUID;

public class Book {
    
    private final UUID bookId;
    private final String title;
    private final String author;
    private final int year;

    public Book(UUID bookId, String title, String author, int year) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public UUID getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", year=" + year + "]";
    }

    public static Book of(String title, String author, int year) {
        return new Book(UUID.randomUUID(), title, author, year);
    }
}