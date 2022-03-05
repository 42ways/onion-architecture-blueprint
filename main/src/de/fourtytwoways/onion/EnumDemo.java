package de.fourtytwoways.onion;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.enums.values.EnumValue;
import de.fourtytwoways.onion.enums.provider.ExampleEnumRepository;
import de.fourtytwoways.onion.enums.types.EnumType;

public class EnumDemo {

    private static void printEnums(EnumRepository repo, EnumType t) {
        System.out.println("Values for " + t + " are:");
        for (EnumValue e:
                repo.getAllEntries(t)) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        EnumRepository myEnumRepository = new ExampleEnumRepository();

        printEnums(myEnumRepository, EnumType.PRODUCT);
        printEnums(myEnumRepository, EnumType.TARIFF);
        printEnums(myEnumRepository, EnumType.BONUS_SYSTEM);
        printEnums(myEnumRepository, EnumType.SEX);

        System.out.println("Tariff with id 2 is: " + myEnumRepository.getEntry(EnumType.TARIFF, 2).orElse(null));
        System.out.println("Tariff with id 3 is: " + myEnumRepository.getEntry(EnumType.TARIFF, 3).orElse(null));

        System.out.println("Bonussystem VA is: " + myEnumRepository.getEntryByKey(EnumType.BONUS_SYSTEM, "VA").orElse(null));

        System.out.println("Product Gemischte Versicherung is: " + myEnumRepository.getEntryByValue(EnumType.PRODUCT, "Gemischte Versicherung").orElse(null));
    }
}
