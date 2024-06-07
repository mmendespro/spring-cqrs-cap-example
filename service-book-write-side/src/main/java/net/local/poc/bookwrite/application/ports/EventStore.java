package net.local.poc.bookwrite.application.ports;

import net.local.poc.bookwrite.domain.events.AbstractDomainEvent;

public interface EventStore {
    void save(AbstractDomainEvent event);
}
