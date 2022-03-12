package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.Product;

import java.time.LocalDate;
import java.util.HashMap;

class TestContractRepository implements ContractRepository {

    final HashMap<String, Contract> contractHashMap = new HashMap<>();

    @Override
    public Contract createContract(String contractNumber, Product product, LocalDate startDate, LocalDate endDate, Money benefit, Money premium) {
        return new Contract(contractNumber, product, startDate, endDate, benefit, premium);
    }

    @Override
    public Contract getContractByNumber(String contractNumber) {
        return contractHashMap.get(contractNumber);
    }

    @Override
    public boolean saveContract(Contract contract) {
        contractHashMap.put(contract.getContractNumber(), contract);
        return true;
    }
}
