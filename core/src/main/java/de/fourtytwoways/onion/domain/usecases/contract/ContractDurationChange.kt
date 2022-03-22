package de.fourtytwoways.onion.domain.usecases.contract
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract
import de.fourtytwoways.onion.domain.values.enumeration.ComputationTarget
import java.time.LocalDate

class ContractDurationChange {
    @JvmOverloads
    fun adjustStartDate(contract: Contract, newStartDate: LocalDate, computationTarget: ComputationTarget = ComputationTarget.BENEFIT): Contract {
        // TODO: Wouldn't it be good to have contract immutable?
        contract.startDate = newStartDate
        return ContractCalculation().calculate(contract, computationTarget)
    }

    @JvmOverloads
    fun adjustEndDate(contract: Contract, newEndDate: LocalDate, computationTarget: ComputationTarget = ComputationTarget.BENEFIT): Contract {
        contract.endDate = newEndDate
        return ContractCalculation().calculate(contract, computationTarget)
    }
}