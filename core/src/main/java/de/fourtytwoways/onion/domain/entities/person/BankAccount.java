package de.fourtytwoways.onion.domain.entities.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import lombok.Builder;
import lombok.NonNull;
import lombok.With;

@Builder
public record BankAccount(int id,
                          @With boolean primary,
                          @With @NonNull String accountHolderName,
                          @With @NonNull String bankName,
                          @With @NonNull String iban,
                          @With @NonNull String bic) {
}
