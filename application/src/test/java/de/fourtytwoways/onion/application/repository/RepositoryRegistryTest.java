package de.fourtytwoways.onion.application.repository;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryRegistryTest {

    @Test
    void getInstance() {
        RepositoryRegistry myRegistry = RepositoryRegistry.getInstance();
        assertNotNull(myRegistry);
        assertEquals(RepositoryRegistry.class, myRegistry.getClass());
        RepositoryRegistry mySecondRegistry = RepositoryRegistry.getInstance();
        //noinspection ConstantConditions
        assert myRegistry == mySecondRegistry;
    }

    private static class MyRepository implements Repository {
        @SuppressWarnings("SameReturnValue")
        public int getMagicNumber() { return 42; }
    }

    @Test
    void registerRepository() {
        RepositoryRegistry myRegistry = RepositoryRegistry.getInstance();
        assertNull(myRegistry.getRepository(MyRepository.class));
        myRegistry.registerRepository(MyRepository.class, new MyRepository());
        assertNotNull(myRegistry.getRepository(MyRepository.class));
    }

    @Test
    void getRepository() {
        RepositoryRegistry myRegistry = RepositoryRegistry.getInstance();
        myRegistry.registerRepository(MyRepository.class, new MyRepository());
        assertEquals(42, ((MyRepository)myRegistry.getRepository(MyRepository.class)).getMagicNumber());
    }
}