package de.fourtytwoways.onion.infrastructure.contracts.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.EnumRepository;
import de.fourtytwoways.onion.application.repositories.PersonRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

@Entity
@Table(name = "CONTRACTS")
public class ContractDAO extends Contract {

    private static EnumRepository enumRepository;

    private static EnumRepository getEnumRepository() {
        if (enumRepository == null) {
            enumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        }
        return enumRepository;
    }

    private static PersonRepository personRepository;

    private static PersonRepository getPersonRepository() {
        if (personRepository == null) {
            personRepository = (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);
        }
        return personRepository;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    protected ContractDAO() {
    }

    public ContractDAO(String contractNumber, Product product, Person beneficiary, LocalDate beginDate, LocalDate endDate, Money benefit, Money premium) {
        super(contractNumber, product, beneficiary, beginDate, endDate, benefit, premium);
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "contractNumber")
    public String getContractNumber() {
        return super.getContractNumber();
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "productId")
    public String getProductId() {
        Product product = getProduct();
        return product == null ? null : product.getKey();
    }

    public void setProductId(String productId) {
        Product product = (Product) getEnumRepository().getEntryByKey(EnumType.PRODUCT, productId).orElse(null);
        super.setProduct(product);
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "beneficiaryId")
    public int getBeneficiaryId() {
        Person beneficiaryPerson = super.getBeneficiary();
        return beneficiaryPerson == null ? 0 : beneficiaryPerson.getId();
    }

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiary = getPersonRepository().getPersonById(beneficiaryId);
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "startDate")
    public LocalDate getStartDate() {
        return super.getStartDate();
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "endDate")
    public LocalDate getEndDate() {
        return super.getEndDate();
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "benefit_amount")
    public BigDecimal getBenefitAmount() {
        return Optional.ofNullable(getBenefit()).map(Money::amount).orElse(null);
    }

    public void setBenefitAmount(BigDecimal amount) {
        setBenefit(createValidMoneyFromPartialData(amount, getBenefitCurrency()));
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "benefit_currency")
    public String getBenefitCurrency() {
        return Optional.ofNullable(getBenefit()).map(Money::currency).map(Currency::getCurrencyCode).orElse(null);
    }

    public void setBenefitCurrency(String currency) {
        setBenefit(createValidMoneyFromPartialData(getBenefitAmount(), currency));
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "premium_amount")
    public BigDecimal getPremiumAmount() {
        return Optional.ofNullable(getPremium()).map(Money::amount).orElse(null);
    }

    public void setPremiumAmount(BigDecimal amount) {
        setPremium(createValidMoneyFromPartialData(amount, getPremiumCurrency()));
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "premium_currency")
    public String getPremiumCurrency() {
        return Optional.ofNullable(getPremium()).map(Money::currency).map(Currency::getCurrencyCode).orElse(null);
    }

    public void setPremiumCurrency(String currency) {
        setPremium(createValidMoneyFromPartialData(getPremiumAmount(), currency));
    }

    private Money createValidMoneyFromPartialData(BigDecimal amount, String currency) {
        BigDecimal newAmount = amount != null ? amount : BigDecimal.valueOf(0);
        Currency newCurrency = currency != null ? Currency.getInstance(currency) : Money.defaultCurrency;
        return Money.valueOf(newAmount, newCurrency);
    }
}
