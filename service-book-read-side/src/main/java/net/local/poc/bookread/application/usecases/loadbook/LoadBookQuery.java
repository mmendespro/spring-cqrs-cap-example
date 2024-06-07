package net.local.poc.bookread.application.usecases.loadbook;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import net.local.poc.bookread.application.cqrs.queries.Query;
import net.local.poc.bookread.application.cqrs.validation.SelfValidating;
import net.local.poc.bookread.domain.entities.Book;

public class LoadBookQuery extends SelfValidating<LoadBookQuery> implements Query<Book> {
    
    @NotNull(message = "[bookId]: field is required")
    private final UUID bookId;

    public LoadBookQuery(UUID bookId) {
        this.bookId = bookId;
        selfValidate(this);
    }

    public UUID getBookId() {
        return bookId;
    }
}
