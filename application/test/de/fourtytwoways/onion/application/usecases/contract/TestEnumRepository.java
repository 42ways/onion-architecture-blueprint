package de.fourtytwoways.onion.application.usecases.contract;
// Copyright (c) 2022 Thomas Herrmann, 42ways GmbH

import com.google.common.collect.ImmutableList;
import de.fourtytwoways.onion.application.repositories.AbstractEnumRepository;
import de.fourtytwoways.onion.domain.entities.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.entities.enumeration.EnumType;
import de.fourtytwoways.onion.domain.entities.enumeration.EnumValue;

import java.util.List;

public class TestEnumRepository extends AbstractEnumRepository {

    @Override
    protected List<EnumValue> getEntriesImpl(EnumType enumType) {
        return ImmutableList.of(new DocumentType(1, "POLICY", "Policy"));
    }
}
