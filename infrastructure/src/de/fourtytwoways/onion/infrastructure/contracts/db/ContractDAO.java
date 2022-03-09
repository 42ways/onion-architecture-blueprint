package de.fourtytwoways.onion.infrastructure.contracts.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    ContractDAO() {
    }

    public ContractDAO(String contractNumber, Product product, LocalDate beginDate, LocalDate endDate, BigDecimal benefit, BigDecimal premium) {
        super(contractNumber, product, beginDate, endDate, benefit, premium);
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "contractNumber")
    public String getContractNumber() { return super.getContractNumber(); }

    @Access(AccessType.PROPERTY)
    @Column(name = "productId")
    public String getProductId() {
        return super.getProduct().getKey();
    }

    public void setProductId(String productId) {
        Product product = (Product)enumRepository.getEntryByKey(EnumType.PRODUCT, productId).orElse(null);
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
    @Column(name = "benefit")
    public BigDecimal getBenefit() {
        return super.getBenefit();
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "premium")
    public BigDecimal getPremium() {
        return super.getPremium();
    }

}
