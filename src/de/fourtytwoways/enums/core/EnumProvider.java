package de.fourtytwoways.enums.core;

import java.util.List;

public interface EnumProvider {
    public List<EnumEntry> getEntries(EnumType enumType);
}
