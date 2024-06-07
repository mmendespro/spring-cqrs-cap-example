package net.local.poc.bookwrite.adapters.http.rest.api;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.local.poc.bookwrite.application.cqrs.commands.CommandBus;
import net.local.poc.bookwrite.application.usecases.createbook.CreateBookCommand;
import net.local.poc.bookwrite.application.usecases.deletebook.DeleteBookCommand;

@Slf4j
@RestController
@RequestMapping("books")
public class WriteBookController {
    
    private final CommandBus commandBus;

    public WriteBookController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody CreateBookCommand command) {
        log.info("[POST ::: /books]: {}", command);
        commandBus.execute(command);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("book created");
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        log.info("[DELETE ::: /books/{}]", bookId);
        commandBus.execute(new DeleteBookCommand(bookId));
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).body("book deleted");
    }
}
