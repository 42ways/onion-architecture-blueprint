package de.fourtytwoways.onion.infrastructure.enums.provider;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.values.enumeration.BonusSystem;
import de.fourtytwoways.onion.domain.values.enumeration.DocumentType;
import de.fourtytwoways.onion.domain.values.enumeration.Sex;
import de.fourtytwoways.onion.domain.values.enumeration.EnumValue;

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
         // TODO: This is a fixed enumeration type and should probably be defined in application or core layer!
        List<EnumValue> sexes = new ArrayList<>();
        sexes.add(new Sex(1, "F", "Female"));
        sexes.add(new Sex(2, "M", "Male"));
        return sexes;
    }

    List<EnumValue> getDocumentTypes() {
        // TODO: This is a fixed enumeration type and should probably be defined in application or core layer!
        List<EnumValue> documentTypes = new ArrayList<>();
        documentTypes.add(DocumentType.POLICY);
        return documentTypes;
    }
}
