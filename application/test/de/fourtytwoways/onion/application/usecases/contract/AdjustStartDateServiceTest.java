package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjustStartDateServiceTest extends ContractServiceTestHelper {

    @Test
    void adjustStartDate() {
        saveContract(createTestContract("0815"));

        assertEquals(BigDecimal.valueOf(4711), loadContract("0815").getBenefit());
        assertEquals(BigDecimal.valueOf(19.71), loadContract("0815").getPremium());

        Contract changedContract = new AdjustStartDateService().adjustStartDate("0815", LocalDate.of(2027, 4, 1));
        assertEquals(BigDecimal.valueOf(3528.09), changedContract.getBenefit());

        assertEquals(BigDecimal.valueOf(3528.09), loadContract("0815").getBenefit());
        assertEquals(BigDecimal.valueOf(19.71), loadContract("0815").getPremium());

        List<String > expectedPrintOutput =
                ImmutableList.of("POLICY for MyTestProduct\nBenefit is 3528.09\nPremium is 19.71\n");
        assertEquals(expectedPrintOutput, getDocumentPrintOutput());
    }

}