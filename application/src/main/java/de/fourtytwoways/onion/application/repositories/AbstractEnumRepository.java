package de.fourtytwoways.onion.application.repositories;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.values.enumeration.EnumType;
import de.fourtytwoways.onion.domain.values.enumeration.EnumValue;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractEnumRepository implements EnumRepository {
    public List<EnumValue> getAllEntries(EnumType enumType) {
        return Collections.unmodifiableList(getEntriesImpl(enumType));
    }

    public Optional<EnumValue> getEntry(EnumType enumType, int enumId) {
        return getAllEntries(enumType).stream().filter(enumEntry ->
                                                               enumEntry.getId() == enumId).findFirst();
    }

    @Override
    public Optional<EnumValue> getEntryByKey(EnumType enumType, String enumKey) {
        return getAllEntries(enumType).stream().filter(enumEntry ->
                                                               enumKey.equals(enumEntry.getKey())).findFirst();
    }

    public Optional<EnumValue> getEntryByValue(EnumType enumType, String enumValue) {
        return getAllEntries(enumType).stream().filter(enumEntry ->
                                                               enumValue.equals(enumEntry.getValue())).findFirst();
    }

    protected abstract List<EnumValue> getEntriesImpl(EnumType enumType);
}
