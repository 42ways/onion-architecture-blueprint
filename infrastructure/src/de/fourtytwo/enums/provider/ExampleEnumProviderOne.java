package de.fourtytwo.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwo.enums.core.EnumEntry;
import de.fourtytwo.enums.types.BonusSystem;

import java.util.ArrayList;
import java.util.List;

public class ExampleEnumProviderOne {
     List<EnumEntry> getBonusSystems() {
        List<EnumEntry> bonusSystems = new ArrayList<>();
        bonusSystems.add(new BonusSystem(1, "BEIT", "Beitragsverrechnung"));
        bonusSystems.add(new BonusSystem(2, "VA", "Verzinsliche Ansammlung"));
        return bonusSystems;
    }
}
