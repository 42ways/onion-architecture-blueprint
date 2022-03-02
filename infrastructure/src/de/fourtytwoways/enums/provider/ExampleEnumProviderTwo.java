package de.fourtytwoways.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.types.Product;
import de.fourtytwoways.enums.types.Tariff;
import de.fourtytwoways.enums.values.EnumValue;

import java.util.ArrayList;
import java.util.List;

public class ExampleEnumProviderTwo {
    List<EnumValue> getProducts() {
        List<EnumValue> products = new ArrayList<>();
        products.add(new Product(1, "P870", "Gemischte Leben"));
        products.add(new Product(2, "P890", "Was auch immer für ein Produkt"));
        return products;
    }

    List<EnumValue> getTariffs() {
        List<EnumValue> tariffs = new ArrayList<>();
        tariffs.add(new Tariff(1, "T870", "Gemischte Leben"));
        tariffs.add(new Tariff(2, "BU210", "Berufsunfähigkeit Zusatzversicherung"));
        tariffs.add(new Tariff(3, "T890", "Was auch immer für ein Tarif"));
        return tariffs;
    }
}
