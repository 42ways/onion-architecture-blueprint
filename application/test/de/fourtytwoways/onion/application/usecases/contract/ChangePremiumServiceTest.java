package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangePremiumServiceTest extends ContractServiceTestHelper {

    @Test
    void changePremium() {
        RepositoryRegistry.getInstance().registerRepository(ContractRepository.class, new TestContractRepository());

        saveContract(createTestContract("0815"));

        assertEquals(BigDecimal.valueOf(19.71), loadContract("0815").getPremium());
        assertEquals(BigDecimal.valueOf(4711), loadContract("0815").getBenefit());

        Contract changedContract = new ChangePremiumService().changePremium("0815", BigDecimal.valueOf(22.22));
        assertEquals(BigDecimal.valueOf(22.22), changedContract.getPremium());

        assertEquals(BigDecimal.valueOf(22.22), loadContract("0815").getPremium());
        assertEquals(BigDecimal.valueOf(5310.58), loadContract("0815").getBenefit());
    }

}