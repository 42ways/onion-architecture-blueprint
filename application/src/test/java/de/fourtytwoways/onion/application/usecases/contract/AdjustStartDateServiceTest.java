package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjustStartDateServiceTest extends ContractServiceTestHelper {

    @Test
    void adjustStartDate() {
        saveContract(createTestContract("0815"));

        assertEquals(Money.valueOf(4711), loadContract("0815").getBenefit());
        assertEquals(Money.valueOf(19.71), loadContract("0815").getPremium());

        Contract changedContract = new AdjustStartDateService().adjustStartDate("0815", LocalDate.of(2027, 4, 1));
        assertEquals(Money.valueOf(3528.09), changedContract.getBenefit());

        assertEquals(Money.valueOf(3528.09), loadContract("0815").getBenefit());
        assertEquals(Money.valueOf(19.71), loadContract("0815").getPremium());

        List<String > expectedPrintOutput =
                ImmutableList.of("POLICY for MyTestProduct\n" +
                                         "Benefit is Money(amount=3528.09, currency=EUR)\n" +
                                         "Premium is Money(amount=19.71, currency=EUR)\n");
        assertEquals(expectedPrintOutput, getDocumentPrintOutput());
    }

}