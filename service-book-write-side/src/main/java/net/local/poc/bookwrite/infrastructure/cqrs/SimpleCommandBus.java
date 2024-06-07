package net.local.poc.bookwrite.infrastructure.cqrs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.local.poc.bookwrite.application.cqrs.commands.Command;
import net.local.poc.bookwrite.application.cqrs.commands.CommandBus;
import net.local.poc.bookwrite.application.cqrs.commands.CommandHandler;
import net.local.poc.bookwrite.application.cqrs.events.CommandEvent;

@Component
@SuppressWarnings({"rawtypes","unchecked"})
public class SimpleCommandBus implements CommandBus {

    private final ObjectMapper mapper;
    private final ApplicationEventPublisher publisher;
    private final Map<Class<?>, CommandHandler> handlers = new HashMap<>();

    public SimpleCommandBus(List<CommandHandler> handlerList, ApplicationEventPublisher publisher, ObjectMapper mapper) {
        this.mapper = mapper;
        this.publisher = publisher;
        handlerList.forEach(handler -> handlers.put(handler.commandType(), handler));
    }

    @Override
    public void execute(Command command) {
        var event = new CommandEvent(command, mapper);
        try {
            handlers.get(command.getClass()).handle(command);
        } catch (Exception exception) {
            event.setException(exception);
            throw exception;
        } finally {
            event.stopTimer();
            publisher.publishEvent(event);
        }
    }
    
}

