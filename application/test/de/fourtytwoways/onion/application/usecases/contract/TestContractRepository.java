package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.enumeration.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

class TestContractRepository implements ContractRepository {

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
