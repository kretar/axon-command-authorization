package org.axonframework.extensions.authorization.commandhandling;

import java.util.UUID;

public class AggregateCreatedEvent {
    private final UUID aggregateId;

    public AggregateCreatedEvent(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }
}
