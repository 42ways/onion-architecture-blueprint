package de.fourtytwoways.onion.domain.event;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import java.util.Collection;
import java.util.HashSet;

public class DomainEventPublisher {
    private static final DomainEventPublisher instance = new DomainEventPublisher();

    private static final Collection<DomainEventSubscriber> subscribers = new HashSet<>();

    public static DomainEventPublisher getInstance() {
        return instance;
    }

    public DomainEventPublisher clearSubscribers() {
        subscribers.clear();
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public DomainEventPublisher subscribe(DomainEventSubscriber subscriber) {
        subscribers.add(subscriber);
        return this;
    }

    public void publish(DomainEvent domainEvent) {
        Class<?> eventType = domainEvent.getClass();
        for (DomainEventSubscriber subscriber : subscribers) {
            Class<?> subscribedTo = subscriber.subscribedToEventType();
            if (subscribedTo == eventType ||
                    subscribedTo == DomainEvent.class)
                subscriber.handleEvent(domainEvent);
        }
    }
}
