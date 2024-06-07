package net.local.poc.bookread.application.cqrs.events;


import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.local.poc.bookread.application.cqrs.queries.Query;

@SuppressWarnings("rawtypes")
public class QueryEvent extends BaseEvent {

    private final Query query;
    private final ObjectMapper mapper;

    public QueryEvent(Query query, ObjectMapper mapper) {
        startTimer();
        this.query = query;
        this.mapper = mapper;
    }

    public Query getQuery() {
        return query;
    }

    @Override
    public String toJson() {
        try 
        {
            Map<String, Object> message = new HashMap<>(Map.of("event", query.getClass().getSimpleName()));
            message.put("content", getQuery());
            message.put("elapsedTimeInMilli", getElapsedTimeInMilli());
            if (hasError()) {
                message.put("message", getException().getMessage());
            }
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException jsonException) {
            return String.format("%s - %s", query, jsonException);
        }
    }
}
