package net.local.poc.bookread.domain.event;

import net.local.poc.bookread.domain.entities.Book;

public class DomainEvent {

    private final Book book;
    private final String eventName;

    public DomainEvent(final Book book, final String eventName) {
        this.book = book;
        this.eventName = eventName;
    }

    public Book getBook() {
        return book;
    }

    public String getEventName() {
        return this.eventName;
    }
}
