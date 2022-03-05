package de.fourtytwoways.onion.domain.model.contracts;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.enums.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode
public class Contract {
    @Getter @Setter private String contractNumber;
    @Getter @Setter private Product product;
    @Getter @Setter private LocalDate startDate;
    @Getter @Setter private LocalDate endDate;
    @Getter @Setter private BigDecimal premium;

    protected Contract() {
    }

    public Contract(String contractNumber, Product product, LocalDate startDate, LocalDate endDate, BigDecimal premium) {
        this.contractNumber = contractNumber;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.premium = premium;
    }

    public String toString() {
        return "CONTRACT [" + contractNumber + " - " + product + ", from " + startDate + " to " + endDate + " with premium " + premium + "]";
    }

}
