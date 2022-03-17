package de.fourtytwoways.onion.application.usecases.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.application.repositories.AccountingRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjustStartDateServiceTest extends ContractServiceTestHelper {

    @Test
    void adjustStartDate() {
        saveContract(createTestContract("0815"));

        assertEquals(Money.valueOf(4711), loadContract("0815").getBenefit());
        assertEquals(Money.valueOf(19.71), loadContract("0815").getPremium());

        Contract changedContract = new AdjustStartDateService().adjustStartDate("0815", LocalDate.of(2027, 4, 1));
        assertEquals(Money.valueOf(3528.09), changedContract.getBenefit());

        assertEquals(Money.valueOf(3528.09), loadContract("0815").getBenefit());
        assertEquals(Money.valueOf(19.71), loadContract("0815").getPremium());

        List<String> expectedPrintOutput =
                ImmutableList.of("""
                                         Lieber Kunde,
                                         wir freuen uns, Ihnen im Anhang die Unterlagen Ihres Versicherungsvertrages des Produkts MyTestProduct übersenden zu können.
                                         Herzlichst,
                                         Ihre Onion First
                                         """,
                                 """
                                         Versicherungspolice für das Produkt MyTestProduct
                                         Die Leistung beträgt 3528.09 EUR
                                         Der Beitrag beträgt 19.71 EUR
                                         """);
        assertEquals(expectedPrintOutput, getDocumentPrintOutput());

        TestAccountingRepository accountingRepository =
                (TestAccountingRepository) RepositoryRegistry.getInstance().getRepository(AccountingRepository.class);
        assertEquals("[AccountingInterfaceData[customer=Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06," +
                             " sex=Sex(2, M, Male), addresses=[], bankAccounts=[])," +
                             " contract=Contract(contractNumber=0815, product=Product(42, TEST, MyTestProduct)," +
                             " beneficiary=Person(id=42, name=Tom, surname=Flint, birthday=1966-06-06," +
                             " sex=Sex(2, M, Male), addresses=[], bankAccounts=[])," +
                             " startDate=2027-04-01, endDate=2042-03-31," +
                             " benefit=Money[amount=3528.09, currency=EUR]," +
                             " premium=Money[amount=19.71, currency=EUR])]]",
                     accountingRepository.getInterfaceData().toString());
    }

}