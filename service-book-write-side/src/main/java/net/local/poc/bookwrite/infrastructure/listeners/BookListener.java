package net.local.poc.bookwrite.infrastructure.listeners;

import java.util.UUID;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.local.poc.bookwrite.application.ports.EventStore;
import net.local.poc.bookwrite.domain.events.AbstractDomainEvent;

@Slf4j
@Component
public class BookListener {
    
    private final EventStore eventStore;
    private final RabbitTemplate rabbitTemplate;

    public BookListener(EventStore eventStore, RabbitTemplate rabbitTemplate) {
        this.eventStore = eventStore;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    @EventListener
    void onDomainEvent(AbstractDomainEvent event) {
        log.info("Saving {}", event.getEventName());
        eventStore.save(event);
        // put event on queue
        var key = String.format("%s-book-processing", UUID.randomUUID());
        rabbitTemplate.convertAndSend("BOOK_PERSIST_TOPIC", event, new CorrelationData(key));
    }
}
