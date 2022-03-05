package de.fourtytwoways.onion.contracts;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.enums.types.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode
public class Contract {
    @Getter @Setter private String contractNumber;
    @Getter @Setter private Product product;
    @Getter @Setter private LocalDate startDate;
    @Getter @Setter private LocalDate endDate;
    @Getter @Setter private double premium;

    protected Contract() {
    }

    public Contract(String contractNumber, Product product, LocalDate startDate, LocalDate endDate, double premium) {
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
