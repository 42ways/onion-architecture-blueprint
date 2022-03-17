package de.fourtytwoways.onion.domain.entities.event;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import java.util.HashSet;
import java.util.Set;

public class DomainEventPublisher {
    private static final DomainEventPublisher instance = new DomainEventPublisher();

    private static Set<DomainEventSubscriber> subscribers = new HashSet<DomainEventSubscriber>();

    public static DomainEventPublisher getInstance() {
        return instance;
    }

    public DomainEventPublisher clearSubscribers() {
        subscribers.clear();
        return this;
    }

    public DomainEventPublisher subscribe(DomainEventSubscriber subscriber) {
        subscribers.add(subscriber);
        return this;
    }

    public <T> void publish(DomainEvent domainEvent) {
        Class<?> eventType = domainEvent.getClass();
        for (DomainEventSubscriber<T> subscriber : subscribers) {
            Class<T> subscribedTo = subscriber.subscribedToEventType();
            if (subscribedTo == eventType ||
                    subscribedTo == DomainEvent.class)
                subscriber.handleEvent(domainEvent);
        }
    }
}
