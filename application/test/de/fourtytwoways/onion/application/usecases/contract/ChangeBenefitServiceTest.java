package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeBenefitServiceTest extends ContractServiceTestHelper {

    @Test
    void changeBenefit() {
        RepositoryRegistry.getInstance().registerRepository(ContractRepository.class, new TestContractRepository());

        saveContract(createTestContract("0815"));

        assertEquals(BigDecimal.valueOf(4711), loadContract("0815").getBenefit());
        assertEquals(BigDecimal.valueOf(19.71), loadContract("0815").getPremium());

        Contract changedContract = new ChangeBenefitService().changeBenefit("0815", BigDecimal.valueOf(5310.58));
        assertEquals(BigDecimal.valueOf(5310.58), changedContract.getBenefit());

        assertEquals(BigDecimal.valueOf(5310.58), loadContract("0815").getBenefit());
        assertEquals(BigDecimal.valueOf(22.22), loadContract("0815").getPremium());
    }

}