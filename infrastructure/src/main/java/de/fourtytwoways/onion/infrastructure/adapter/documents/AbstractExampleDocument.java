package de.fourtytwoways.onion.infrastructure.adapter.documents;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.contract.Contract;
import de.fourtytwoways.onion.domain.entities.document.Document;

public abstract class AbstractExampleDocument implements Document {
    protected final int id;
    protected final Contract contract;
    private boolean printed = false;

    AbstractExampleDocument(int id, Contract contract) {
        this.id = id;
        this.contract = contract;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void print() {
        printed = true;
    }

    public boolean isPrinted() {
        return printed;
    }
}
