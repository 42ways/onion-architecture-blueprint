package de.fourtytwoways.onion.domain.entities.contract;

// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.values.enumeration.Product;
import de.fourtytwoways.onion.domain.values.Money;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    private String contractNumber;
    private Product product;
    private LocalDate startDate;
    private LocalDate endDate;
    private Money benefit;
    private Money premium;

    public Period getDuration() {
        return startDate.until(endDate);
    }

    public BigDecimal getDurationInMonths() {
        Period duration = getDuration();
        return BigDecimal.valueOf(duration.getYears() * 12 + duration.getMonths());
    }
}
