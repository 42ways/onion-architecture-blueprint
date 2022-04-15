package de.fourtytwoways.onion.domain.entities.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import lombok.*;

@Value @Builder
public class Address {
    int id;
    @With boolean primary;
    @With @NonNull String street;
    @With @NonNull String number;
    @With @NonNull String zipCode;
    @With @NonNull String city;
}
