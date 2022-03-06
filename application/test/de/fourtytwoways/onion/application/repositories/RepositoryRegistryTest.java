package de.fourtytwoways.onion.application.repositories;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import static org.junit.jupiter.api.Assertions.*;

class RepositoryRegistryTest {

    @org.junit.jupiter.api.Test
    void getInstance() {
        RepositoryRegistry myRegistry = RepositoryRegistry.getInstance();
        assertNotNull(myRegistry);
        assertEquals(RepositoryRegistry.class, myRegistry.getClass());
        RepositoryRegistry mySecondRegistry = RepositoryRegistry.getInstance();
        assert myRegistry == mySecondRegistry;
    }

    private static class MyRepository implements Repository {
        public int getMagicNumber() { return 42; }
    }

    @org.junit.jupiter.api.Test
    void registerRepository() {
        RepositoryRegistry myRegistry = RepositoryRegistry.getInstance();
        assertNull(myRegistry.getRepository(MyRepository.class));
        myRegistry.registerRepository(MyRepository.class, new MyRepository());
        assertNotNull(myRegistry.getRepository(MyRepository.class));
    }

    @org.junit.jupiter.api.Test
    void getRepository() {
        RepositoryRegistry myRegistry = RepositoryRegistry.getInstance();
        myRegistry.registerRepository(MyRepository.class, new MyRepository());
        assertEquals(42, ((MyRepository)myRegistry.getRepository(MyRepository.class)).getMagicNumber());
    }
}