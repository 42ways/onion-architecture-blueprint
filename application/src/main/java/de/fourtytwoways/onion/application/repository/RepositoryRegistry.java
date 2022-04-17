package de.fourtytwoways.onion.application.repository;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import java.util.HashMap;

public class RepositoryRegistry {
    private static final RepositoryRegistry instance = new RepositoryRegistry();

    private final HashMap<Class<? extends Repository>, Repository> repoMap = new HashMap<>();

    public static RepositoryRegistry getInstance() {
        return instance;
    }

    public RepositoryRegistry registerRepository(Class<? extends Repository> cls, Repository repo) {
        repoMap.put(cls, repo);
        return this;
    }

    public Repository getRepository(Class<? extends Repository> cls) {
        return repoMap.get(cls);
    }
}
