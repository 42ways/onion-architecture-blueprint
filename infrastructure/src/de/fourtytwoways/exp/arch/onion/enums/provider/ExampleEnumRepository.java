package de.fourtytwoways.exp.arch.onion.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.exp.arch.onion.enums.values.AbstractEnumRepository;
import de.fourtytwoways.exp.arch.onion.enums.values.EnumValue;
import de.fourtytwoways.exp.arch.onion.enums.types.EnumType;

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
        }
        return null;
    }
}
