package de.fourtytwoways.onion.infrastructure.contracts.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

// In a "real" system, this class could be 100% generated from the data model
// It contains all universally applicable mapping from the domain model to the database model
// If there is a need for special ("handwritten") mapping code, that goes to the superclass "ContractDbMapper"

import de.fourtytwoways.onion.application.repositories.EnumRepository;
import de.fourtytwoways.onion.application.repositories.RepositoryRegistry;
import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Product;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

// TODO: Need Getters and Setters to be public? This class is only used by the persistence framework (aka JPA/Hibernate)

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CONTRACTS")
public class ContractDAO extends ContractDbMapper {

    private static EnumRepository enumRepository;

    private static EnumRepository getEnumRepository() {
        if (enumRepository == null) {
            enumRepository = (EnumRepository) RepositoryRegistry.getInstance().getRepository(EnumRepository.class);
        }
        return enumRepository;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    protected ContractDAO() {
        super();
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
        return super.getBeneficiaryId();
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
        setBenefit(Money.valueOfNullable(amount, getBenefitCurrency()));
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "benefit_currency")
    public String getBenefitCurrency() {
        return Optional.ofNullable(getBenefit()).map(Money::currency).map(Currency::getCurrencyCode).orElse(null);
    }

    public void setBenefitCurrency(String currency) {
        setBenefit(Money.valueOfNullable(getBenefitAmount(), currency));
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "premium_amount")
    public BigDecimal getPremiumAmount() {
        return Optional.ofNullable(getPremium()).map(Money::amount).orElse(null);
    }

    public void setPremiumAmount(BigDecimal amount) {
        setPremium(Money.valueOfNullable(amount, getPremiumCurrency()));
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "premium_currency")
    public String getPremiumCurrency() {
        return Optional.ofNullable(getPremium()).map(Money::currency).map(Currency::getCurrencyCode).orElse(null);
    }

    public void setPremiumCurrency(String currency) {
        setPremium(Money.valueOfNullable(getPremiumAmount(), currency));
    }

}
