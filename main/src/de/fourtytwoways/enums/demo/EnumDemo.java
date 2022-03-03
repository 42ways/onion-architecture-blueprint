package de.fourtytwoways.enums.demo;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.values.EnumValue;
import de.fourtytwoways.enums.provider.ExampleEnumRepository;
import de.fourtytwoways.enums.types.EnumType;
import de.fourtytwoways.enums.values.EnumRepository;

public class EnumDemo {

    public static void main(String[] args) {
        EnumRepository myEnumRepository = new ExampleEnumRepository();

        System.out.println("My products are:");
        for (EnumValue e:
             myEnumRepository.getAllEntries(EnumType.PRODUCT)) {
            System.out.println(e);
        }

        System.out.println("My tariffs are:");
        for (EnumValue e:
                myEnumRepository.getAllEntries(EnumType.TARIFF)) {
            System.out.println(e);
        }

        System.out.println("Tariff with id 2 is: " + myEnumRepository.getEntry(EnumType.TARIFF, 2).orElse(null));
        System.out.println("Tariff with id 3 is: " + myEnumRepository.getEntry(EnumType.TARIFF, 3).orElse(null));

        System.out.println("Bonussystem VA is: " + myEnumRepository.getEntryByKey(EnumType.BONUS_SYSTEM, "VA").orElse(null));

        System.out.println("Product Gemischte Versicherung is: " + myEnumRepository.getEntryByValue(EnumType.PRODUCT, "Gemischte Versicherung").orElse(null));
    }
}
