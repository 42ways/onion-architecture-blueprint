package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangePremiumServiceTest extends ContractServiceTestHelper {

    @Test
    void changePremium() {
        saveContract(createTestContract("0815"));

        assertEquals(Money.valueOf(19.71), loadContract("0815").getPremium());
        assertEquals(Money.valueOf(4711), loadContract("0815").getBenefit());

        Contract changedContract =
                new ChangePremiumService().changePremium("0815", Money.valueOf(22.22));
        assertEquals(Money.valueOf(22.22), changedContract.getPremium());

        assertEquals(BigDecimal.valueOf(22.22), loadContract("0815").getPremium().getAmount());
        assertEquals(Money.Currency.EUR, loadContract("0815").getPremium().getCurrency());
        assertEquals(BigDecimal.valueOf(5310.58), loadContract("0815").getBenefit().getAmount());
        assertEquals(Money.Currency.EUR, loadContract("0815").getBenefit().getCurrency());

        List<String> expectedPrintOutput =
                ImmutableList.of("""
                                         Lieber Kunde,
                                         wir freuen uns, Ihnen im Anhang die Unterlagen Ihres Versicherungsvertrages des Produkts MyTestProduct übersenden zu können.
                                         Herzlichst,
                                         Ihre Onion First
                                         """,
                                 """
                                         Versicherungspolice für das Produkt MyTestProduct
                                         Die Leistung beträgt Money(amount=5310.58, currency=EUR)
                                         Der Beitrag beträgt Money(amount=22.22, currency=EUR)
                                         """);
        assertEquals(expectedPrintOutput, getDocumentPrintOutput());
    }

}