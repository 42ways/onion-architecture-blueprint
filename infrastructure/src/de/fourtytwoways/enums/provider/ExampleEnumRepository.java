package de.fourtytwoways.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.values.AbstractEnumRepository;
import de.fourtytwoways.enums.values.EnumValue;
import de.fourtytwoways.enums.types.EnumType;

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
        }
        return null;
    }
}
