package de.fourtytwoways.onion.application.repositories;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.values.enumeration.BonusSystem;
import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.EnumValue;
import de.fourtytwoways.onion.domain.values.enumeration.Sex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AbstractEnumRepositoryTest {

    private static class TestEnumRepository extends AbstractEnumRepository {
        @Override
        protected List<EnumValue> getEntriesImpl(EnumType enumType) {
            switch (enumType) {
                case SEX -> {
                    List<EnumValue> sexes = new ArrayList<>();
                    sexes.add(new Sex(1, "F", "Female"));
                    sexes.add(new Sex(2, "M", "Male"));
                    return sexes;
                }
                case BONUS_SYSTEM -> {
                    List<EnumValue> bonusSystems = new ArrayList<>();
                    bonusSystems.add(new BonusSystem(1, "BEIT", "Beitragsverrechnung"));
                    bonusSystems.add(new BonusSystem(2, "VA", "Verzinsliche Ansammlung"));
                    return bonusSystems;
                }
            }
            return null;
        }
    }

    @Test
    void getAllEntries() {
        EnumRepository enumRepository = new TestEnumRepository();
        List<EnumValue> values = enumRepository.getAllEntries(EnumType.SEX);
        assertEquals(2, values.size());
        assertEquals("[Sex(1, F, Female), Sex(2, M, Male)]", values.toString());
    }

    @Test
    void getEntry() {
        EnumRepository enumRepository = new TestEnumRepository();
        assertFalse(enumRepository.getEntry(EnumType.BONUS_SYSTEM, 1).isEmpty());
        assertEquals("Optional[BonusSystem(1, BEIT, Beitragsverrechnung)]",
                enumRepository.getEntry(EnumType.BONUS_SYSTEM, 1).toString());
        assertTrue(enumRepository.getEntry(EnumType.BONUS_SYSTEM, 3).isEmpty());
    }

    @Test
    void getEntryByKey() {
        EnumRepository enumRepository = new TestEnumRepository();
        assertEquals("Optional[Sex(2, M, Male)]",
                enumRepository.getEntryByKey(EnumType.SEX, "M").toString());
        assertTrue(enumRepository.getEntryByKey(EnumType.SEX, "X").isEmpty());
    }

    @Test
    void getEntryByValue() {
        EnumRepository enumRepository = new TestEnumRepository();
        assertEquals("Optional[Sex(1, F, Female)]",
                enumRepository.getEntryByValue(EnumType.SEX, "Female").toString());
        assertTrue(enumRepository.getEntryByValue(EnumType.SEX, "XYZ").isEmpty());
    }
}