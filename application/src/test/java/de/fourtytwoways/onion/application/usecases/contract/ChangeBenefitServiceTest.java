package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeBenefitServiceTest extends ContractServiceTestHelper {

    @Test
    void changeBenefit() {
        saveContract(createTestContract("4711"));

        assertEquals(Money.valueOf(4711), loadContract("4711").getBenefit());
        assertEquals(Money.valueOf(19.71), loadContract("4711").getPremium());

        Contract changedContract =
                new ChangeBenefitService().changeBenefit("4711", Money.valueOf(5310.58));
        assertEquals(Money.valueOf(5310.58), changedContract.getBenefit());

        assertEquals(Money.valueOf(5310.58), loadContract("4711").getBenefit());
        assertEquals(Money.valueOf(22.22), loadContract("4711").getPremium());

        List<String> expectedPrintOutput =
                ImmutableList.of("""
                                         Lieber Kunde,
                                         wir freuen uns, Ihnen im Anhang die Unterlagen Ihres Versicherungsvertrages des Produkts MyTestProduct übersenden zu können.
                                         Herzlichst,
                                         Ihre Onion First
                                         """,
                                 """
                                         Versicherungspolice für das Produkt MyTestProduct
                                         Die Leistung beträgt 5310.58 EUR
                                         Der Beitrag beträgt 22.22 EUR
                                         """);
        assertEquals(expectedPrintOutput, getDocumentPrintOutput());
    }

}