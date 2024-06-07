package net.local.poc.bookread.adapters.http.rest.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.local.poc.bookread.application.cqrs.queries.QueryBus;
import net.local.poc.bookread.application.usecases.loadbook.LoadBookQuery;
import net.local.poc.bookread.application.usecases.loadbooks.LoadBooksQuery;
import net.local.poc.bookread.domain.entities.Book;

@RestController
@RequestMapping("books")
public class ReadBookController {
    
    private final QueryBus queryBus;

    public ReadBookController(QueryBus queryBus) {
        this.queryBus = queryBus;
    }
    
    @GetMapping
    public ResponseEntity<List<Book>> loadBooks() {
        return ResponseEntity.ok(queryBus.execute(new LoadBooksQuery()));
    }

    @GetMapping("{bookId}")
    public ResponseEntity<Book> loadBook(@PathVariable UUID bookId) {
        return ResponseEntity.ok(queryBus.execute(new LoadBookQuery(bookId)));
    }
}
