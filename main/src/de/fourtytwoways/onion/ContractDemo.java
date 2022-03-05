package de.fourtytwoways.onion;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.ContractRepository;
import de.fourtytwoways.onion.application.EnumRepository;
import de.fourtytwoways.onion.application.RepositoryRegistry;
import de.fourtytwoways.onion.domain.model.contracts.Contract;
import de.fourtytwoways.onion.infrastructure.contracts.db.ExampleContractRepository;
import de.fourtytwoways.onion.infrastructure.enums.provider.ExampleEnumRepository;
import de.fourtytwoways.onion.domain.model.enums.EnumType;
import de.fourtytwoways.onion.domain.model.enums.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContractDemo {

    public static void registerRepos() {
        EnumRepository enumRepository = new ExampleEnumRepository();
        ContractRepository contractRepository = new ExampleContractRepository(enumRepository);

        RepositoryRegistry.getInstance().
                registerRepository(EnumRepository.class, enumRepository).
                registerRepository(ContractRepository.class, contractRepository);
    }

    public static void main(String[] args) {

        registerRepos();

        EnumRepository myEnumRepository = (EnumRepository)  RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        ContractRepository myContractRepository = (ContractRepository) RepositoryRegistry.getInstance().getRepository(ContractRepository.class);

        Product gemischteVersicherung = (Product) myEnumRepository.getEntryByKey(EnumType.PRODUCT, "GV").orElse(null);

        Contract c1 =
                myContractRepository.createContract("42",
                        gemischteVersicherung,
                        LocalDate.of(2022, 4, 1), LocalDate.of(2042, 3, 31),
                        null /* calculate this later */,
                        BigDecimal.valueOf( 666.));
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
                        BigDecimal.valueOf(4711), null /* calculate later */);
        System.out.println(c4);
        myContractRepository.saveContract(c4);

        Contract c5 = myContractRepository.getContractByNumber("0815");
        System.out.println(c5);
        Contract c6 = myContractRepository.getContractByNumber("42");
        System.out.println(c6);
    }
}
