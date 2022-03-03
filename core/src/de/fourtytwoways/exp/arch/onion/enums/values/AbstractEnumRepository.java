package de.fourtytwoways.exp.arch.onion.enums.values;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.exp.arch.onion.enums.types.EnumType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractEnumRepository implements EnumRepository {
    public List<EnumValue> getAllEntries(EnumType enumType) {
        return Collections.unmodifiableList(getEntriesImpl(enumType));
    }

    public Optional<EnumValue> getEntry(EnumType enumType, int enumId) {
        List<EnumValue> entries = getAllEntries(enumType);
        return entries.stream().filter(enumEntry -> enumEntry.getId() == enumId).findFirst();
    }

    public Optional<EnumValue> getEntryByKey(EnumType enumType, String enumKey) {
        List<EnumValue> entries = getAllEntries(enumType);
        return entries.stream().filter(enumEntry -> enumKey.equals(enumEntry.getKey())).findFirst();
    }

    public Optional<EnumValue> getEntryByValue(EnumType enumType, String enumValue) {
        List<EnumValue> entries = getAllEntries(enumType);
        return entries.stream().filter(enumEntry -> enumValue.equals(enumEntry.getValue())).findFirst();
    }

    protected abstract List<EnumValue> getEntriesImpl(EnumType enumType);
}
