package net.local.poc.bookread.application.usecases.loadbook;

import org.springframework.stereotype.Service;

import net.local.poc.bookread.application.cqrs.queries.QueryHandler;
import net.local.poc.bookread.application.ports.BookGateway;
import net.local.poc.bookread.domain.entities.Book;

@Service
public class LoadBookQueryHandle implements QueryHandler<LoadBookQuery, Book> {

    private final BookGateway gateway;

    public LoadBookQueryHandle(BookGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Book handle(LoadBookQuery query) {
        return gateway.load(query.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public Class<?> queryType() {
        return LoadBookQuery.class;
    }
    
}
