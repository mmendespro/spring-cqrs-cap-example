package net.local.poc.bookread.infrastructure.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.local.poc.bookread.application.cqrs.events.BaseEvent;

@Slf4j
@Component
public class LogListener {
    
    @Async
    @EventListener
    void onEvent(BaseEvent event) {
        if (event.isSuccess()) {
            log.info(event.toJson());
        } else {
            log.error(event.toJson(), event.getException());
        }
    }
}
