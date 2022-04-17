package de.fourtytwoways.onion.domain.model.enumeration;
// (c) 2022 Thomas Herrmann, 42ways GmbH

// We use a "real" enum here in order to simplify our code (e.g. having switch() statements)
// TODO: Simple, consistent and DRY way to unify Java enum with customizable EnumValue instances
public enum ComputationTarget {
    BENEFIT,
    PREMIUM
}
