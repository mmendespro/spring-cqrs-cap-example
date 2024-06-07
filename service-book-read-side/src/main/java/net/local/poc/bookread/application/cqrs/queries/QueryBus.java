package net.local.poc.bookread.application.cqrs.queries;

public interface QueryBus {
    <T> T execute(Query<T> query);
}
