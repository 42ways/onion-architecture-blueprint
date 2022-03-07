package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdjustEndDateServiceTest extends ContractServiceTestHelper {

    @Test
    void adjustStartDate() {
        RepositoryRegistry.getInstance().registerRepository(ContractRepository.class, new TestContractRepository());

        saveContract(createTestContract("0815"));

        assertEquals(BigDecimal.valueOf(4711), loadContract("0815").getBenefit());
        assertEquals(BigDecimal.valueOf(19.71), loadContract("0815").getPremium());

        Contract changedContract = new AdjustEndDateService().adjustEndDate("0815", LocalDate.of(2027, 4, 1));
        assertEquals(BigDecimal.valueOf(1182.6), changedContract.getBenefit().stripTrailingZeros());

        assertEquals(BigDecimal.valueOf(1182.6), loadContract("0815").getBenefit().stripTrailingZeros());
        assertEquals(BigDecimal.valueOf(19.71), loadContract("0815").getPremium());
    }

}