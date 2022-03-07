package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.enumeration.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

class ChangePremiumServiceTest {

    @Test
    void changePremium() {
        RepositoryRegistry.getInstance().registerRepository(ContractRepository.class, new TestContractRepository());

        saveContract(createContract("0815"));

        assertEquals(BigDecimal.valueOf(19.71), loadContract("0815").getPremium());
        assertEquals(BigDecimal.valueOf(4711), loadContract("0815").getBenefit());

        Contract changedContract = new ChangePremiumService().changePremium("0815", BigDecimal.valueOf(22.22));
        assertEquals(BigDecimal.valueOf(22.22), changedContract.getPremium());

        assertEquals(BigDecimal.valueOf(22.22), loadContract("0815").getPremium());
        assertEquals(BigDecimal.valueOf(5310.58), loadContract("0815").getBenefit());
    }

    private void saveContract(Contract contract) {
        ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).saveContract(contract);
    }

    private Contract loadContract(String s) {
        return ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).getContractByNumber(s);
    }

    private Contract createContract(String contractNumber) {
        return ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).createContract(
                contractNumber,
                new Product(42, "TEST", "MyTestProduct"),
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2042, 3, 31),
                BigDecimal.valueOf(4711), BigDecimal.valueOf(19.71));
    }

}