package de.fourtytwoways.onion.application.usecases.contract;


// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.enumeration.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ChangeBenefitServiceTest {

    @Test
    void changeBenefit() {
        RepositoryRegistry.getInstance().registerRepository(ContractRepository.class, new ChangeBenefitServiceTest.TestContractRepository());

        saveContract(createContract("0815"));

        assertEquals(BigDecimal.valueOf(4711), loadContract("0815").getBenefit());
        assertEquals(BigDecimal.valueOf(19.71), loadContract("0815").getPremium());

        Contract changedContract = new ChangeBenefitService().changeBenefit("0815", BigDecimal.valueOf(5310.58));
        assertEquals(BigDecimal.valueOf(5310.58), changedContract.getBenefit());

        assertEquals(BigDecimal.valueOf(5310.58), loadContract("0815").getBenefit());
        assertEquals(BigDecimal.valueOf(22.22), loadContract("0815").getPremium());
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

    private static class TestContractRepository implements ContractRepository {

        HashMap<String, Contract> contractHashMap = new HashMap<>();

        @Override
        public Contract createContract(String contractNumber, Product product, LocalDate startDate, LocalDate endDate, BigDecimal benefit, BigDecimal premium) {
            Contract myContract = new Contract(contractNumber, product, startDate, endDate, benefit, premium);
            return myContract;
        }

        @Override
        public Contract getContractByNumber(String contractNumber) {
            return (Contract) contractHashMap.get(contractNumber);
        }

        @Override
        public boolean saveContract(Contract contract) {
            contractHashMap.put(contract.getContractNumber(), contract);
            return true;
        }
    }

}