package net.local.poc.bookwrite.application.cqrs.events;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.local.poc.bookwrite.application.cqrs.commands.Command;

public class CommandEvent extends BaseEvent {

    private final ObjectMapper mapper;
    private final Command command;

    public CommandEvent(Command command, ObjectMapper mapper) {
        startTimer();
        this.mapper = mapper;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public String toJson() {
        try 
        {
            Map<String, Object> message = new HashMap<>(Map.of("event", command.getClass().getSimpleName()));
            message.put("content", getCommand());
            message.put("elapsedTimeInMilli", getElapsedTimeInMilli());
            if (hasError()) {
                message.put("message", getException().getMessage());
            }
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException jsonException) {
            return String.format("%s - %s", command, jsonException);
        }
    }    
}

