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
    public String getContractNumber() {
        return super.getContractNumber();
    }

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
        super.setBenefit(createValidMoneyFromPartialData(amount, getBenefitCurrency()));
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "benefit_currency")
    public String getBenefitCurrency() {
        // TODO: This is super ugly! There has to be a better way in Java to handle NULL values in call chain...
        Money benefit = super.getBenefit();
        if (benefit != null)
            return benefit.getCurrency().toString();
        else
            return null;
    }

    public void setBenefitCurrency(String currency) {
        super.setBenefit(createValidMoneyFromPartialData(getBenefitAmount(), currency));
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
        super.setPremium(createValidMoneyFromPartialData(amount, getPremiumCurrency()));
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "premium_currency")
    public String getPremiumCurrency() {
        // TODO: This is super ugly! There has to be a better way in Java to handle NULL values in call chain...
        Money premium = super.getPremium();
        if (premium != null)
            return premium.getCurrency().toString();
        else
            return null;
    }

    public void setPremiumCurrency(String currency) {
        super.setPremium(createValidMoneyFromPartialData(getPremiumAmount(), currency));
    }

    private Money createValidMoneyFromPartialData(BigDecimal amount, String currency) {
        Money.Currency newCurrency = "USD".equals(currency) ? Money.Currency.USD : Money.Currency.EUR;
        BigDecimal newAmount = amount != null ? amount : BigDecimal.valueOf(0);
        return Money.valueOf(newAmount, newCurrency);
    }
}
