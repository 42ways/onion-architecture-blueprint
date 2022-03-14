package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjustEndDateServiceTest extends ContractServiceTestHelper {

    @Test
    void adjustStartDate() {
        saveContract(createTestContract("0815"));

        assertEquals(Money.valueOf(4711), loadContract("0815").getBenefit());
        assertEquals(Money.valueOf(19.71), loadContract("0815").getPremium());

        Contract changedContract = new AdjustEndDateService().adjustEndDate("0815", LocalDate.of(2027, 4, 1));
        assertEquals(Money.valueOf(1182.6).amount(), changedContract.getBenefit().amount());

        assertEquals(Money.valueOf(1182.6).amount(), loadContract("0815").getBenefit().amount());
        assertEquals(Money.valueOf(19.71), loadContract("0815").getPremium());

        List<String> expectedPrintOutput =
                ImmutableList.of("""
                                         Lieber Kunde,
                                         wir freuen uns, Ihnen im Anhang die Unterlagen Ihres Versicherungsvertrages des Produkts MyTestProduct übersenden zu können.
                                         Herzlichst,
                                         Ihre Onion First
                                         """,
                                 """
                                         Versicherungspolice für das Produkt MyTestProduct
                                         Die Leistung beträgt 1182.60 EUR
                                         Der Beitrag beträgt 19.71 EUR
                                         """);
        assertEquals(expectedPrintOutput, getDocumentPrintOutput());
    }

}