package net.local.poc.bookwrite.application.usecases.createbook;

import java.text.MessageFormat;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.local.poc.bookwrite.application.cqrs.commands.CommandHandler;
import net.local.poc.bookwrite.application.ports.BookStore;
import net.local.poc.bookwrite.domain.entities.Book;
import net.local.poc.bookwrite.domain.events.BookCreatedEvent;

@Service
public class CreateBookCommandHandler implements CommandHandler<CreateBookCommand> {

    private final BookStore gateway;
    private final ObjectMapper mapper;
    private final ApplicationEventPublisher publisher;

    public CreateBookCommandHandler(BookStore gateway, ObjectMapper mapper, ApplicationEventPublisher publisher) {
        this.gateway = gateway;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public void handle(CreateBookCommand command) {
        gateway.loadByTitle(command.getTitle()).ifPresent(book -> {
            throw new RuntimeException(MessageFormat.format("Book {0} already exists", book.getTitle()));
        });
        
        var newBook = Book.of(command.getTitle(), command.getAuthor(), command.getYear());
        gateway.save(newBook);

        var event = BookCreatedEvent.of(mapper, newBook);
        publisher.publishEvent(event);
    }

    @Override
    public Class<?> commandType() {
        return CreateBookCommand.class;
    }    
}
