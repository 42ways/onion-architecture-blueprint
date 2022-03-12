package de.fourtytwoways.onion.domain.entities.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BankAccount {
    private int id;
    private final boolean primary;
    @NonNull private final String accountHolderName;
    @NonNull private final String bankName;
    @NonNull private final String iban;
    @NonNull private final String bic;
}
