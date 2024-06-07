package net.local.poc.bookwrite.application.usecases.deletebook;

import java.text.MessageFormat;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.local.poc.bookwrite.application.cqrs.commands.CommandHandler;
import net.local.poc.bookwrite.application.ports.BookStore;
import net.local.poc.bookwrite.domain.events.BookDeletedEvent;

@Service
public class DeleteBookCommandHandle implements CommandHandler<DeleteBookCommand> {

    private final BookStore gateway;
    private final ObjectMapper mapper;
    private final ApplicationEventPublisher publisher;

    public DeleteBookCommandHandle(BookStore gateway, ObjectMapper mapper, ApplicationEventPublisher publisher) {
        this.gateway = gateway;
        this.mapper = mapper;
        this.publisher = publisher;
    }    

    @Override
    public void handle(DeleteBookCommand command) {
        var foundBook = gateway.load(UUID.fromString(command.getBookId())).orElseThrow(() -> {
            throw new RuntimeException(MessageFormat.format("Book {0} not found", command.getBookId()));
        });

        gateway.delete(foundBook.getBookId());

        publisher.publishEvent(BookDeletedEvent.of(mapper, foundBook));
    }

    @Override
    public Class<?> commandType() {
        return DeleteBookCommand.class;
    }
}
