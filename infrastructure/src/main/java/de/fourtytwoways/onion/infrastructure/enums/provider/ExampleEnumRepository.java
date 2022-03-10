package de.fourtytwoways.onion.infrastructure.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.application.repositories.AbstractEnumRepository;
import de.fourtytwoways.onion.domain.values.enumeration.EnumValue;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;

import java.util.List;

public class ExampleEnumRepository extends AbstractEnumRepository {
    ExampleEnumProviderOne providerOne = new ExampleEnumProviderOne();
    ExampleEnumProviderTwo providerTwo = new ExampleEnumProviderTwo();

    @Override
    protected List<EnumValue> getEntriesImpl(EnumType enumType) {
        switch (enumType) {
            case TARIFF -> {
                return providerTwo.getTariffs();
            }
            case PRODUCT -> {
                return providerTwo.getProducts();
            }
            case BONUS_SYSTEM -> {
                return providerOne.getBonusSystems();
            }
            case SEX -> {
                return providerOne.getSexes();
            }
            case DOCUMENT_TYPE -> {
                return providerOne.getDocumentTypes();
            }
        }
        return null;
    }
}
