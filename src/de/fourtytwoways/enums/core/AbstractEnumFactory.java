package de.fourtytwoways.enums.core;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.enums.types.EnumType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractEnumFactory implements EnumFactory {
    public List<EnumEntry> getAllEntries(EnumType enumType) {
        return Collections.unmodifiableList(getEntriesImpl(enumType));
    }

    public Optional<EnumEntry> getEntry(EnumType enumType, int enumId) {
        List<EnumEntry> entries = getAllEntries(enumType);
        return entries.stream().filter(enumEntry -> enumEntry.getId() == enumId).findFirst();
    }

    public Optional<EnumEntry> getEntryByKey(EnumType enumType, String enumKey) {
        List<EnumEntry> entries = getAllEntries(enumType);
        return entries.stream().filter(enumEntry -> enumKey.equals(enumEntry.getKey())).findFirst();
    }

    public Optional<EnumEntry> getEntryByValue(EnumType enumType, String enumValue) {
        List<EnumEntry> entries = getAllEntries(enumType);
        return entries.stream().filter(enumEntry -> enumValue.equals(enumEntry.getValue())).findFirst();
    }

    protected abstract List<EnumEntry> getEntriesImpl(EnumType enumType);
}
