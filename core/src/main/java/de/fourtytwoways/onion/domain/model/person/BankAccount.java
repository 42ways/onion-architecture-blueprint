package de.fourtytwoways.onion.domain.model.person;
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

    public interface Validator {
        boolean validate(String iban, String bic);
    }

    public static BankAccount.Validator validator = null;

    public BankAccount {
        if (validator != null && !validator.validate(iban, bic)) {
            throw new java.lang.IllegalArgumentException(
                    String.format("Invalid IBAN (%s) or BIC (%)", iban, bic));
        }
    }

}
