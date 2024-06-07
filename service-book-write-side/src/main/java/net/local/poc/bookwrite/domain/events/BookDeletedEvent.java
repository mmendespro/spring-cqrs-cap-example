package net.local.poc.bookwrite.domain.events;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.local.poc.bookwrite.domain.entities.Book;

public class BookDeletedEvent extends AbstractDomainEvent {
    
    private final Book book;
    private long eventTime;
    
    public BookDeletedEvent(ObjectMapper mapper, Book book) {
        super(mapper);
        this.book = book;
        this.eventTime = System.nanoTime();
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String getEventName() {
        return this.getClass().getSimpleName();
    }    

    @Override
    public String toJson() {
        try {
            Map<String, Object> message = new HashMap<>(Map.of("event", getEventName()));
            message.put("content", book);
            message.put("eventTime", eventTime);
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException jsonException) {
            return String.format("%s - %s", getEventName(), jsonException);
        }
    }
    
    public static BookDeletedEvent of(ObjectMapper mapper, Book book) {
        return new BookDeletedEvent(mapper, book);
    }
}
