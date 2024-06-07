package net.local.poc.bookread.application.usecases.loadbooks;

import java.util.List;

import net.local.poc.bookread.application.cqrs.queries.Query;
import net.local.poc.bookread.domain.entities.Book;

public class LoadBooksQuery implements Query<List<Book>> {
}
