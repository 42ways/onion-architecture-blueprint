package de.fourtytwoways.onion.infrastructure.contracts.db;

import de.fourtytwoways.onion.domain.model.contracts.Contract;
import de.fourtytwoways.onion.domain.model.enums.EnumType;
import de.fourtytwoways.onion.domain.model.enums.Product;
import de.fourtytwoways.onion.application.EnumRepository;

import javax.persistence.*;
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

    public ContractDAO(String contractNumber, Product product, LocalDate beginDate, LocalDate endDate, double premium) {
        super(contractNumber, product, beginDate, endDate, premium);
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
    @Column(name = "premium")
    public double getPremium() {
        return super.getPremium();
    }

}
