package de.fourtytwoways.onion.domain.entities.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.person.Person;
import de.fourtytwoways.onion.domain.values.Money;
import de.fourtytwoways.onion.domain.values.enumeration.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    private String contractNumber;
    private Product product;
    protected Person beneficiary;
    private LocalDate startDate;
    private LocalDate endDate;
    private Money benefit;
    private Money premium;

    public Period getDuration() {
        return startDate.until(endDate);
    }

    public BigDecimal getDurationInMonths() {
        Period duration = getDuration();
        return BigDecimal.valueOf(duration.getYears() * 12L + duration.getMonths());
    }
}
