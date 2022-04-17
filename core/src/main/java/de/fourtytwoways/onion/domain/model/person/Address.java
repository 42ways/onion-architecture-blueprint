package de.fourtytwoways.onion.domain.model.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import lombok.Builder;
import lombok.NonNull;
import lombok.With;

@Builder
public record Address(int id,
                      @With boolean primary,
                      @With @NonNull String street,
                      @With @NonNull String number,
                      @With @NonNull String zipCode,
                      @With @NonNull String city) {
}
