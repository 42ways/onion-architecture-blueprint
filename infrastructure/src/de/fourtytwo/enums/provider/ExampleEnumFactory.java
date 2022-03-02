package de.fourtytwo.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwo.enums.core.AbstractEnumFactory;
import de.fourtytwo.enums.core.EnumEntry;
import de.fourtytwo.enums.types.EnumType;

import java.util.List;

public class ExampleEnumFactory extends AbstractEnumFactory {
    ExampleEnumProviderOne providerOne = new ExampleEnumProviderOne();
    ExampleEnumProviderTwo providerTwo = new ExampleEnumProviderTwo();

    @Override
    protected List<EnumEntry> getEntriesImpl(EnumType enumType) {
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
