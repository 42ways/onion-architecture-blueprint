package de.fourtytwoways.onion.domain.entities.event;
// (c) 2022 Thomas Herrmann, 42ways GmbH

public interface DomainEventSubscriber<T> {
    void handleEvent(DomainEvent domainEvent);
    Class<T> subscribedToEventType();
}
