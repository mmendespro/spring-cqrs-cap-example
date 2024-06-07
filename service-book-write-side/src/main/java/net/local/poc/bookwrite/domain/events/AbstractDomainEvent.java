package net.local.poc.bookwrite.domain.events;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractDomainEvent {
    
    protected final ObjectMapper mapper;

    public AbstractDomainEvent(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    
    public abstract String getEventName();
    public abstract String toJson();
}
