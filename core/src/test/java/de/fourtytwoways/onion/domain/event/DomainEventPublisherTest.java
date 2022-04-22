package de.fourtytwoways.onion.domain.event;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.event.DomainEvent;
import de.fourtytwoways.onion.domain.event.DomainEventPublisher;
import de.fourtytwoways.onion.domain.event.DomainEventSubscriber;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainEventPublisherTest {

    @Test
    void getInstance() {
        DomainEventPublisher domainEventPublisher = DomainEventPublisher.getInstance();
        assertEquals(domainEventPublisher.getClass(), DomainEventPublisher.class);
        DomainEventPublisher domainEventPublisher1 = DomainEventPublisher.getInstance();
        //noinspection ConstantConditions
        assert domainEventPublisher == domainEventPublisher1;
    }

    @Test
    void clearSubscribers() {
        final int[] counter = {0};
        record MyDomainEvent(LocalDate occurredOn) implements DomainEvent{}
        final DomainEvent domainEvent = new MyDomainEvent(LocalDate.of(2022,4,1));

        DomainEventPublisher domainEventPublisher = DomainEventPublisher.getInstance();
        assertEquals(0, counter[0]);
        domainEventPublisher.subscribe(new DomainEventSubscriber() {
            @Override
            public void handleEvent(DomainEvent domainEvent) {
                counter[0]++;
            }
            @Override
            public Class<?> subscribedToEventType() {
                return domainEvent.getClass();
            }
        });
        assertEquals(0, counter[0]);
        domainEventPublisher.publish(domainEvent);
        assertEquals(1, counter[0]);
        domainEventPublisher.clearSubscribers();
        domainEventPublisher.publish(domainEvent);
        assertEquals(1, counter[0]);
        domainEventPublisher.publish(domainEvent);
        assertEquals(1, counter[0]);
    }

    @Test
    void subscribeAndPublish() {
        final int[] counter = {0};
        record MyDomainEvent(LocalDate occurredOn) implements DomainEvent{}
        final DomainEvent domainEvent = new MyDomainEvent(LocalDate.of(2022,4,1));

        DomainEventPublisher domainEventPublisher = DomainEventPublisher.getInstance();
        assertEquals(0, counter[0]);
        domainEventPublisher.subscribe(new DomainEventSubscriber() {
            @Override
            public void handleEvent(DomainEvent domainEvent) {
                counter[0]++;
            }
            @Override
            public Class<?> subscribedToEventType() {
                return domainEvent.getClass();
            }
        });
        assertEquals(0, counter[0]);
        domainEventPublisher.publish(domainEvent);
        assertEquals(1, counter[0]);
        domainEventPublisher.publish(domainEvent);
        assertEquals(2, counter[0]);

        domainEventPublisher.subscribe(new DomainEventSubscriber() {
            @Override
            public void handleEvent(DomainEvent domainEvent) {
                counter[0]+= 4;
            }
            @Override
            public Class<?> subscribedToEventType() {
                return domainEvent.getClass();
            }
        });
        domainEventPublisher.publish(domainEvent);
        assertEquals(7, counter[0]);
    }
}