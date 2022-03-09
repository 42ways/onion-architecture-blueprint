package de.fourtytwoways.onion.infrastructure.contracts.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.Product;
import de.fourtytwoways.onion.application.repositories.EnumRepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CONTRACTS")
public class ContractDAO extends Contract {
    static EnumRepository enumRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    ContractDAO() {
    }

    public ContractDAO(String contractNumber, Product product, LocalDate beginDate, LocalDate endDate, Money benefit, Money premium) {
        super(contractNumber, product, beginDate, endDate, benefit, premium);
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "contractNumber")
    public String getContractNumber() {return super.getContractNumber();}

    @Access(AccessType.PROPERTY)
    @Column(name = "productId")
    public String getProductId() {
        return super.getProduct().getKey();
    }

    public void setProductId(String productId) {
        Product product = (Product) enumRepository.getEntryByKey(EnumType.PRODUCT, productId).orElse(null);
        super.setProduct(product);
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
        // TODO: This is super ugly! There has to be a better way in Java to handle NULL values in call chain...
        Money benefit = super.getBenefit();
        if (benefit != null)
            return benefit.getAmount();
        else
            return null;
    }

    public void setBenefitAmount(BigDecimal amount) {
        // TODO: There has to be a better way for null handling...
        if (amount != null)
            super.setBenefit(new Money(amount, getBenefitCurrency()));
        else
            super.setBenefit(null);
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "benefit_currency")
    public Money.Currency getBenefitCurrency() {
        // TODO: This is super ugly! There has to be a better way in Java to handle NULL values in call chain...
        Money benefit = super.getBenefit();
        if (benefit != null)
            return benefit.getCurrency();
        else
            return null;
    }

    public void setBenefitCurrency(String currency) {
        // TODO: set correct currency !
        BigDecimal amount = getBenefitAmount();

        super.setBenefit(new Money(getBenefitAmount(), Money.Currency.EUR));
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "premium_amount")
    public BigDecimal getPremiumAmount() {
        // TODO: This is super ugly! There has to be a better way in Java to handle NULL values in call chain...
        Money premium = super.getPremium();
        if (premium != null)
            return premium.getAmount();
        else
            return null;
    }

    public void setPremiumAmount(BigDecimal amount) {
        super.setPremium(new Money(amount, getPremiumCurrency()));
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "premium_currency")
    public Money.Currency getPremiumCurrency() {
        // TODO: This is super ugly! There has to be a better way in Java to handle NULL values in call chain...
        Money premium = super.getPremium();
        if (premium != null)
            return premium.getCurrency();
        else
            return null;
    }

    public void setPremiumCurrency(String currency) {
        // TODO: set correct currency !
        super.setPremium(new Money(getPremiumAmount(), Money.Currency.EUR));
    }

}
