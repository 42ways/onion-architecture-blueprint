package de.fourtytwoways.onion.application.usecase.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.event.DomainEvent;

import java.time.LocalDate;

public record ContractValuesChanged(LocalDate occurredOn, Contract modifiedContract) implements DomainEvent {
}
