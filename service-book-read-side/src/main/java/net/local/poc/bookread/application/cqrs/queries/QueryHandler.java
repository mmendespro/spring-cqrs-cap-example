package net.local.poc.bookread.application.cqrs.queries;

public interface QueryHandler<Q, R> {
    R handle(Q query);
    Class<?> queryType();
}