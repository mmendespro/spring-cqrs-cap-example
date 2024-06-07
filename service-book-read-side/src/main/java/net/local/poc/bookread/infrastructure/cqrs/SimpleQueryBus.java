package net.local.poc.bookread.infrastructure.cqrs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.local.poc.bookread.application.cqrs.events.QueryEvent;
import net.local.poc.bookread.application.cqrs.queries.Query;
import net.local.poc.bookread.application.cqrs.queries.QueryBus;
import net.local.poc.bookread.application.cqrs.queries.QueryHandler;

@Component
@SuppressWarnings({"rawtypes","unchecked"})
public class SimpleQueryBus implements QueryBus {

    private final ObjectMapper mapper;
    private final ApplicationEventPublisher publisher;
    private final Map<Class<?>, QueryHandler> handlers = new HashMap<>();

    public SimpleQueryBus(List<QueryHandler> handlerList, ApplicationEventPublisher publisher, ObjectMapper mapper) {
        this.mapper = mapper;
        this.publisher = publisher;
        handlerList.forEach(handler -> handlers.put(handler.queryType(), handler));
    }

    @Override
    public <T> T execute(Query<T> query) {
        var event = new QueryEvent(query, mapper);
        try {
            return (T) handlers.get(query.getClass()).handle(query);
        } catch (Exception exception) {
            event.setException(exception);
            throw exception;
        } finally {
            event.stopTimer();
            publisher.publishEvent(event);
        }
    }
}