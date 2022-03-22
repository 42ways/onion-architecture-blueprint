package de.fourtytwoways.onion.domain.entities.contract
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.person.Person
import de.fourtytwoways.onion.domain.values.Money
import de.fourtytwoways.onion.domain.values.enumeration.Product
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Period

open class Contract(
    open var contractNumber: String? = null,
    var product: Product? = null,
    var beneficiary: Person? = null,
    open var startDate: LocalDate? = null,
    open var endDate: LocalDate? = null,
    open var benefit: Money? = null,
    open var premium: Money? = null
) {

    val duration: Period get() = startDate!!.until(endDate)
    val durationInMonths: BigDecimal get() {
            val duration = duration
            return BigDecimal.valueOf(duration.years * 12L + duration.months)
        }

    override fun toString(): String {
        return "Contract(contractNumber=$contractNumber, product=$product, beneficiary=$beneficiary, startDate=$startDate, endDate=$endDate, benefit=$benefit, premium=$premium)"
    }
}