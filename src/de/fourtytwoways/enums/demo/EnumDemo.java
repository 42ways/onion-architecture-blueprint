package de.fourtytwoways.enums.demo;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.core.EnumEntry;
import de.fourtytwoways.enums.core.EnumFactory;
import de.fourtytwoways.enums.types.EnumType;
import de.fourtytwoways.enums.provider.ExampleEnumFactory;

public class EnumDemo {

    public static void main(String[] args) {
        EnumFactory myEnumFactory = new ExampleEnumFactory();

        System.out.println("My products are:");
        for (EnumEntry e:
             myEnumFactory.getAllEntries(EnumType.PRODUCT)) {
            System.out.println(e);
        }

        System.out.println("My tariffs are:");
        for (EnumEntry e:
                myEnumFactory.getAllEntries(EnumType.TARIFF)) {
            System.out.println(e);
        }

        System.out.println("Tariff 2 is: " + myEnumFactory.getEntry(EnumType.TARIFF, 2).orElse(null));

        System.out.println("Bonussystem VA is: " + myEnumFactory.getEntryByKey(EnumType.BONUS_SYSTEM, "VA").orElse(null));

        System.out.println("Product Gemischte Leben is: " + myEnumFactory.getEntryByValue(EnumType.PRODUCT, "Gemischte Leben").orElse(null));
    }
}
