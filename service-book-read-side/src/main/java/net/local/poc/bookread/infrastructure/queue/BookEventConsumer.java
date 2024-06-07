package net.local.poc.bookread.infrastructure.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.local.poc.bookread.application.ports.BookGateway;
import net.local.poc.bookread.domain.event.DomainEvent;

@Slf4j
@Component
public class BookEventConsumer {
    
    private final BookGateway gateway;

    public BookEventConsumer(BookGateway gateway) {
        this.gateway = gateway;
    }

    @RabbitListener(queues = {"BOOK_PERSIST_TOPIC"})
    void onProcessBookCreatedEvent(DomainEvent event) {
        log.info("Process Event {}", event.getEventName());
        switch (event.getEventName()) {
            case "BookCreatedEvent":
                gateway.save(event.getBook());
            break;
            case "BookDeletedEvent":    
                gateway.delete(event.getBook().getBookId());
            break;
        }
    }
}
