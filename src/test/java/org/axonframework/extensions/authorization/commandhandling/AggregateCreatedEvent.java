package org.axonframework.extensions.authorization.commandhandling;

import java.util.UUID;

/**
 * Test event
 *
 * @author Roald Bankras
 */
public class AggregateCreatedEvent {
    private final UUID aggregateId;

    public AggregateCreatedEvent(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }
}
