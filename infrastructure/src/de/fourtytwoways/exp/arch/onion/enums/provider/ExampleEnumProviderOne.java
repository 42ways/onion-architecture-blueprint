package de.fourtytwoways.exp.arch.onion.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.exp.arch.onion.enums.types.BonusSystem;
import de.fourtytwoways.exp.arch.onion.enums.types.Sex;
import de.fourtytwoways.exp.arch.onion.enums.values.EnumValue;

import java.util.ArrayList;
import java.util.List;

public class ExampleEnumProviderOne {
     List<EnumValue> getBonusSystems() {
        List<EnumValue> bonusSystems = new ArrayList<>();
        bonusSystems.add(new BonusSystem(1, "BEIT", "Beitragsverrechnung"));
        bonusSystems.add(new BonusSystem(2, "VA", "Verzinsliche Ansammlung"));
        return bonusSystems;
    }

    List<EnumValue> getSexes() {
        List<EnumValue> sexes = new ArrayList<>();
        sexes.add(new Sex(1, "F", "Female"));
        sexes.add(new Sex(2, "M", "Male"));
        return sexes;
    }
}
