package de.fourtytwoways.onion.domain.entities.event;
// (c) 2022 Thomas Herrmann, 42ways GmbH

public interface DomainEventSubscriber {
    void handleEvent(DomainEvent domainEvent);
    Class<?> subscribedToEventType();
}
