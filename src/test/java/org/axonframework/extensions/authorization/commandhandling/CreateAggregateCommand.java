package org.axonframework.extensions.authorization.commandhandling;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

@PreAuthorize("aggregate.create")
public class CreateAggregateCommand {
    private final UUID aggregateId;

    public CreateAggregateCommand(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }
}
