package de.fourtytwoways.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.core.AbstractEnumFactory;
import de.fourtytwoways.enums.types.BonusSystem;
import de.fourtytwoways.enums.core.EnumEntry;
import de.fourtytwoways.enums.core.EnumType;
import de.fourtytwoways.enums.types.Product;
import de.fourtytwoways.enums.types.Tariff;

import java.util.ArrayList;
import java.util.List;

public class ExampleEnumFactory extends AbstractEnumFactory {
    @Override
    protected List<EnumEntry> getEntriesImpl(EnumType enumType) {
        switch (enumType) {
            case TARIFF -> {
                return getTariffs();
            }
            case PRODUCT -> {
                return getProducts();
            }
            case BONUS_SYSTEM -> {
                return getBonusSystems();
            }
        }
        return null;
    }

    private List<EnumEntry> getBonusSystems() {
        List<EnumEntry> bonusSystems = new ArrayList<>();
        bonusSystems.add(new BonusSystem(1, "BEIT", "Beitragsverrechnung"));
        bonusSystems.add(new BonusSystem(2, "VA", "Verzinsliche Ansammlung"));
        return bonusSystems;
    }

    private List<EnumEntry> getProducts() {
        List<EnumEntry> products = new ArrayList<>();
        products.add(new Product(1, "P870", "Gemischte Leben"));
        products.add(new Product(2, "P890", "Was auch immer für ein Produkt"));
        return products;
    }

    private List<EnumEntry> getTariffs() {
        List<EnumEntry> tariffs = new ArrayList<>();
        tariffs.add(new Tariff(1, "T870", "Gemischte Leben"));
        tariffs.add(new Tariff(2, "BU210", "Berufsunfähigkeit Zusatzversicherung"));
        tariffs.add(new Tariff(3, "T890", "Was auch immer für ein Tarif"));
        return tariffs;
    }
}
