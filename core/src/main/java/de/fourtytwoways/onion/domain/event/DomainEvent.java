package de.fourtytwoways.onion.domain.event;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import java.time.LocalDate;

public interface DomainEvent {
    LocalDate occurredOn();
}
