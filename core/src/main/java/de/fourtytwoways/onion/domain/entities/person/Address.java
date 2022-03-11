package de.fourtytwoways.onion.domain.entities.person;

// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

// (c) 2022 Thomas Herrmann, 42ways GmbH

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Address {
    private int id;
    private final boolean primary;
    @NonNull private final String street;
    @NonNull private final String number;
    @NonNull private final String zipCode;
    @NonNull private final String city;
}
