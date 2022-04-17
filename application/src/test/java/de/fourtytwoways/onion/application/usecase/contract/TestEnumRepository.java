package de.fourtytwoways.onion.application.usecase.contract;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.application.repository.AbstractEnumRepository;
import de.fourtytwoways.onion.domain.model.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.model.enumeration.EnumType;
import de.fourtytwoways.onion.domain.model.enumeration.EnumValue;

import java.util.List;

public class TestEnumRepository extends AbstractEnumRepository {

    @Override
    protected List<EnumValue> getEntriesImpl(EnumType enumType) {
        return ImmutableList.of(DocumentType.POLICY);
    }
}
