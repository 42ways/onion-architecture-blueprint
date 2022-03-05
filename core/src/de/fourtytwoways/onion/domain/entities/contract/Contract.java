package de.fourtytwoways.onion.domain.entities.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.enumeration.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@EqualsAndHashCode
public class Contract {
    @Getter @Setter private String contractNumber;
    @Getter @Setter private Product product;
    @Getter @Setter private LocalDate startDate;
    @Getter @Setter private LocalDate endDate;
    @Getter @Setter private BigDecimal benefit;
    @Getter @Setter private BigDecimal premium;

    protected Contract() {
    }

    public Contract(String contractNumber, Product product, LocalDate startDate, LocalDate endDate, BigDecimal benefit, BigDecimal premium) {
        this.contractNumber = contractNumber;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.benefit = benefit;
        this.premium = premium;
    }

    public Period getDuration() {
        return startDate.until(endDate);
    }

    public BigDecimal getDurationInMonths() {
        Period duration = getDuration();
        return BigDecimal.valueOf(duration.getYears() * 12 + duration.getMonths());
    }

    public String toString() {
        return "CONTRACT [" + contractNumber + " - " + product + ", from " + startDate + " to " + endDate + " with benefit " + benefit + " and premium " + premium + "]";
    }

}
