package net.local.poc.bookread.application.usecases.loadbooks;

import java.util.List;

import org.springframework.stereotype.Service;

import net.local.poc.bookread.application.cqrs.queries.QueryHandler;
import net.local.poc.bookread.application.ports.BookGateway;
import net.local.poc.bookread.domain.entities.Book;

@Service
public class LoadBooksQueryHandler implements QueryHandler<LoadBooksQuery, List<Book>> {

    private final BookGateway gateway;

    public LoadBooksQueryHandler(BookGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<Book> handle(LoadBooksQuery query) {
        return gateway.load();
    }

    @Override
    public Class<?> queryType() {
        return LoadBooksQuery.class;
    }
    
}
