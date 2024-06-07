package net.local.poc.bookwrite.infrastructure.gateways;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import net.local.poc.bookwrite.application.ports.EventStore;
import net.local.poc.bookwrite.domain.events.AbstractDomainEvent;

@Repository
public class JdbcEventStoreGateway implements EventStore {

    private final JdbcClient jdbcClient;

    public JdbcEventStoreGateway(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void save(AbstractDomainEvent event) {
        jdbcClient.sql("INSERT INTO EVENTS (CONTENT) VALUES (:contentJson)")
                  .param("contentJson", event.toJson())
                  .update();
    }
    
}
