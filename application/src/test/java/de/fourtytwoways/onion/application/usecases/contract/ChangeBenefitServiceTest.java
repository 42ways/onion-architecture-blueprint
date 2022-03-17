package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.application.repositories.AccountingRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeBenefitServiceTest extends ContractServiceTestHelper {

    @Test
    void changeBenefit() {
        saveContract(createTestContract("4711"));

        assertEquals(Money.valueOf(4711), loadContract("4711").getBenefit());
        assertEquals(Money.valueOf(19.71), loadContract("4711").getPremium());

        Contract changedContract =
                new ChangeBenefitService().changeBenefit("4711", Money.valueOf(5310.58));
        assertEquals(Money.valueOf(5310.58), changedContract.getBenefit());

        assertEquals(Money.valueOf(5310.58), loadContract("4711").getBenefit());
        assertEquals(Money.valueOf(22.22), loadContract("4711").getPremium());

        List<String> expectedPrintOutput =
                ImmutableList.of("""
                                         Lieber Kunde,
                                         wir freuen uns, Ihnen im Anhang die Unterlagen Ihres Versicherungsvertrages des Produkts MyTestProduct übersenden zu können.
                                         Herzlichst,
                                         Ihre Onion First
                                         """,
                                 """
                                         Versicherungspolice für das Produkt MyTestProduct
                                         Die Leistung beträgt 5310.58 EUR
                                         Der Beitrag beträgt 22.22 EUR
                                         """);
        assertEquals(expectedPrintOutput, getDocumentPrintOutput());

        TestAccountingRepository accountingRepository =
                (TestAccountingRepository) RepositoryRegistry.getInstance().getRepository(AccountingRepository.class);
        assertEquals("[AccountingInterfaceData[customer=Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06," +
                             " sex=Sex(2, M, Male), addresses=[], bankAccounts=[])," +
                             " contract=Contract(contractNumber=4711, product=Product(42, TEST, MyTestProduct)," +
                             " beneficiary=Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06," +
                             " sex=Sex(2, M, Male), addresses=[], bankAccounts=[])," +
                             " startDate=2022-04-01, endDate=2042-03-31," +
                             " benefit=Money[amount=5310.58, currency=EUR]," +
                             " premium=Money[amount=22.22, currency=EUR])]]",
                     accountingRepository.getInterfaceData().toString());
    }

}