package de.fourtytwoways.onion.application.usecases.contract;// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.enumeration.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

abstract public class ContractServiceTestHelper {
    protected void saveContract(Contract contract) {
        ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).saveContract(contract);
    }

    protected Contract loadContract(String s) {
        return ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).getContractByNumber(s);
    }

    protected Contract createTestContract(String contractNumber) {
        return ((ContractRepository)
                RepositoryRegistry.getInstance().getRepository(ContractRepository.class)).createContract(
                contractNumber,
                new Product(42, "TEST", "MyTestProduct"),
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2042, 3, 31),
                BigDecimal.valueOf(4711), BigDecimal.valueOf(19.71));
    }
}
