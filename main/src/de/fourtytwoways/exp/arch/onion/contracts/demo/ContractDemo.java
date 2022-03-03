package de.fourtytwoways.exp.arch.onion.contracts.demo;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.exp.arch.onion.contracts.Contract;
import de.fourtytwoways.exp.arch.onion.contracts.ContractRepository;
import de.fourtytwoways.exp.arch.onion.contracts.db.ExampleContractRepository;
import de.fourtytwoways.exp.arch.onion.enums.provider.ExampleEnumRepository;
import de.fourtytwoways.exp.arch.onion.enums.types.EnumType;
import de.fourtytwoways.exp.arch.onion.enums.types.Product;
import de.fourtytwoways.exp.arch.onion.enums.values.EnumRepository;

import java.time.LocalDate;

public class ContractDemo {

    public static void main(String[] args) {
        EnumRepository myEnumRepository = new ExampleEnumRepository();
        ContractRepository myContractRepository = new ExampleContractRepository(myEnumRepository);

        Product gemischteVersicherung = (Product) myEnumRepository.getEntryByKey(EnumType.PRODUCT, "GV").orElse(null);

        Contract c1 =
                myContractRepository.createContract("42",
                        gemischteVersicherung,
                        LocalDate.of(2022, 4, 1), LocalDate.of(2042, 3, 31),
                        666.);
        System.out.println(c1);
        myContractRepository.saveContract(c1);

        Contract c2 = myContractRepository.getContractByNumber("42");
        System.out.println(c2);

        c2.setEndDate(LocalDate.of(2032, 3, 31));
        System.out.println(c2);
        myContractRepository.saveContract(c2);

        Contract c3 = myContractRepository.getContractByNumber("42");
        System.out.println(c3);

        Contract c4 =
                myContractRepository.createContract("0815",
                        gemischteVersicherung,
                        LocalDate.of(2022, 4, 1), LocalDate.of(2042, 3, 31),
                        47.11);
        System.out.println(c4);
        myContractRepository.saveContract(c4);

        Contract c5 = myContractRepository.getContractByNumber("0815");
        System.out.println(c5);
        Contract c6 = myContractRepository.getContractByNumber("42");
        System.out.println(c6);
    }
}
