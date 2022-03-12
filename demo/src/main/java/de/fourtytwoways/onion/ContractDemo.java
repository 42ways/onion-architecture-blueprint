package de.fourtytwoways.onion;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.ContractRepository;
import de.fourtytwoways.onion.application.repositories.DocumentRepository;
import de.fourtytwoways.onion.application.repositories.EnumRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.application.usecases.contract.ChangePremiumService;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.usecases.contract.ContractCalculation;
import de.fourtytwoways.onion.domain.usecases.contract.ContractDurationChange;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.ComputationTarget;
import de.fourtytwoways.onion.infrastructure.contracts.db.ExampleContractRepository;
import de.fourtytwoways.onion.infrastructure.documents.ExampleDocumentRepository;
import de.fourtytwoways.onion.infrastructure.enums.provider.ExampleEnumRepository;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Product;

import java.time.LocalDate;

public class ContractDemo {

    public static void registerRepos() {
        EnumRepository enumRepository = new ExampleEnumRepository();
        ContractRepository contractRepository = new ExampleContractRepository(enumRepository);
        DocumentRepository documentRepository = new ExampleDocumentRepository();

        RepositoryRegistry.getInstance().
                registerRepository(EnumRepository.class, enumRepository).
                registerRepository(ContractRepository.class, contractRepository).
                registerRepository(DocumentRepository.class, documentRepository);
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
                                                    Money.valueOf(666.));
        System.out.println(c1);
        myContractRepository.saveContract(c1);

        Contract c2 = myContractRepository.getContractByNumber("42");
        System.out.println(c2);

        c2 = new ContractCalculation().calculateBenefit(c2);
        System.out.println(c2);

        c2  = new ContractDurationChange().adjustEndDate(c2, LocalDate.of(2032, 3, 31));
        System.out.println(c2);
        c2 = new ContractCalculation().calculateBenefit(c2);
        System.out.println(c2);
        myContractRepository.saveContract(c2);

        Contract c3 = myContractRepository.getContractByNumber("42");
        System.out.println(c3);

        Contract c4 =
                myContractRepository.createContract("0815",
                        gemischteVersicherung,
                        LocalDate.of(2022, 4, 1), LocalDate.of(2042, 3, 31),
                        Money.valueOf(4711), null /* calculate later */);
        System.out.println(c4);
        c4 = new ContractCalculation().calculatePremium(c4);
        System.out.println(c4);
        myContractRepository.saveContract(c4);

        c4 = new ContractDurationChange().adjustStartDate(c4, LocalDate.of(2027, 4, 1), ComputationTarget.PREMIUM);
        System.out.println(c4);
        myContractRepository.saveContract(c4);

        Contract c5 = myContractRepository.getContractByNumber("42");
        System.out.println(c5);

        Contract c6 = myContractRepository.getContractByNumber("0815");
        System.out.println(c6);

        Contract c7 = new ChangePremiumService().
                changePremium("42", Money.valueOf(3216.8, Money.Currency.USD));
        System.out.println(c7);

        Contract c8 = myContractRepository.getContractByNumber("42");
        System.out.println(c8);
    }
}
