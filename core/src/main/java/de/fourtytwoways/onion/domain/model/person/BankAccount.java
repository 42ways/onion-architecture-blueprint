package de.fourtytwoways.onion.domain.model.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import lombok.Builder;
import lombok.NonNull;
import lombok.With;
import org.iban4j.BicUtil;
import org.iban4j.IbanUtil;
@Builder
public record BankAccount(int id,
                          @With boolean primary,
                          @With @NonNull String accountHolderName,
                          @With @NonNull String bankName,
                          @With @NonNull String iban,
                          @With @NonNull String bic) {

    public BankAccount {
        IbanUtil.validate(iban);
        BicUtil.validate(bic);
    }

}
