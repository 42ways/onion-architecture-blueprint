package de.fourtytwoways.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.core.AbstractEnumFactory;
import de.fourtytwoways.enums.core.EnumEntry;
import de.fourtytwoways.enums.core.EnumType;
import de.fourtytwoways.enums.types.Product;
import de.fourtytwoways.enums.types.Tariff;

import java.util.ArrayList;
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
