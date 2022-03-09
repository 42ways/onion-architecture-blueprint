package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeBenefitServiceTest extends ContractServiceTestHelper {

    @Test
    void changeBenefit() {
        saveContract(createTestContract("0815"));

        assertEquals(Money.valueOf(4711), loadContract("0815").getBenefit());
        assertEquals(Money.valueOf(19.71), loadContract("0815").getPremium());

        Contract changedContract =
                new ChangeBenefitService().changeBenefit("0815", Money.valueOf(5310.58));
        assertEquals(Money.valueOf(5310.58), changedContract.getBenefit());

        assertEquals(Money.valueOf(5310.58), loadContract("0815").getBenefit());
        assertEquals(Money.valueOf(22.22), loadContract("0815").getPremium());

        List<String > expectedPrintOutput =
                ImmutableList.of("POLICY for MyTestProduct\n" +
                                         "Benefit is Money(amount=5310.58, currency=EUR)\n" +
                                         "Premium is Money(amount=22.22, currency=EUR)\n");
        assertEquals(expectedPrintOutput, getDocumentPrintOutput());
    }

}