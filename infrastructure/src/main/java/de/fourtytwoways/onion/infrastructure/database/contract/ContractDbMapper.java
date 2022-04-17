package de.fourtytwoways.onion.infrastructure.database.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

// This is the "mapping layer" for our database access framework
// It contains only mapping functions that could not be generated from the data and domain model information,
// i.e. special foreign key / relation implementation, complicated value mapping, splitting or joining
// attribute groups etc.
// In a "real" system there would be a template for this class as a starting point, and it would hopefully
// be very small, since only attributes that have to be mapped would be included

import de.fourtytwoways.onion.application.repository.PersonRepository;
import de.fourtytwoways.onion.application.repository.RepositoryRegistry;
import de.fourtytwoways.onion.domain.model.contract.Contract;
import de.fourtytwoways.onion.domain.model.person.Person;
import de.fourtytwoways.onion.domain.model.asset.Money;
import de.fourtytwoways.onion.domain.model.enumeration.Product;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class ContractDbMapper extends Contract {

    private static PersonRepository personRepository;

    protected ContractDbMapper() {
    }

    public ContractDbMapper(String contractNumber, Product product, Person beneficiary, LocalDate beginDate, LocalDate endDate, Money benefit, Money premium) {
        super(contractNumber, product, beneficiary, beginDate, endDate, benefit, premium);
    }

    private static PersonRepository getPersonRepository() {
        if (personRepository == null) {
            personRepository = (PersonRepository) RepositoryRegistry.getInstance().getRepository(PersonRepository.class);
        }
        return personRepository;
    }

    // This is just an example for "handwritten" mapping code, to show the pattern
    // (in lack of a more appropriate example in our simple model)
    // Of course this foreign key based relation to the people table could easily be generated or done in common code
    public int getBeneficiaryId() {
        Person beneficiaryPerson = getBeneficiary();
        return beneficiaryPerson == null ? 0 : beneficiaryPerson.getId();
    }

    @SuppressWarnings("unused") // needed by hibernate to set beneficiary
    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiary = ContractDbMapper.getPersonRepository().getPersonById(beneficiaryId);
    }
}
