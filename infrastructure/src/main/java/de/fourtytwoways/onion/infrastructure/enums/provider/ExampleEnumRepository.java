package de.fourtytwoways.onion.infrastructure.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.AbstractEnumRepository;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.EnumValue;

import java.util.List;

public class ExampleEnumRepository extends AbstractEnumRepository {
    final ExampleEnumProviderOne providerOne = new ExampleEnumProviderOne();
    final ExampleEnumProviderTwo providerTwo = new ExampleEnumProviderTwo();

    @Override
    protected List<EnumValue> getEntriesImpl(EnumType enumType) {
        return switch (enumType) {
            case BONUS_SYSTEM -> providerOne.getBonusSystems();
            case SEX -> providerOne.getSexes();
            case DOCUMENT_TYPE -> providerOne.getDocumentTypes();
            case TARIFF -> providerTwo.getTariffs();
            case PRODUCT -> providerTwo.getProducts();
        };
    }
}
