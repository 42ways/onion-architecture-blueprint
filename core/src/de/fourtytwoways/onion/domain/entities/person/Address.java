package de.fourtytwoways.onion.domain.entities.person;
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
    @NonNull private boolean primary;
    @NonNull private String street;
    @NonNull private String number;
    @NonNull private String zipCode;
    @NonNull private String city;
}
