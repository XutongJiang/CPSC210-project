package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NecessitiesTest {
    private Necessities tns;
    private Necessity tn;
    @BeforeEach
    public void setUp() {
        tns = new Necessities();
        tn = new Necessity("test", 0, 0, 2020, 7,26);
    }

    @Test
    public void testAddNotAlreadyExists() {
        boolean test = tns.addNecessity(tn);
        assertTrue(test);
        boolean test2 = tns.checkNecessity(tn.getName());
        assertTrue(test2);
    }

    @Test
    public void testAddAlreadyExists() {
        boolean test = tns.addNecessity(tn);
        Necessity tn2 = new Necessity("test", 1,1, 2020, 7, 26);
        boolean test2 = tns.addNecessity(tn2);
        assertFalse(test2);
    }

    @Test
    public void testRemoveNotAlreadyExists() {
        boolean test = tns.removeNecessity(tn);
        assertFalse(test);
    }

    @Test
    public void testRemoveAlreadyExists() {
        boolean test = tns.addNecessity(tn);
        boolean test2 = tns.removeNecessity(tn);
        assertTrue(test2);
        boolean test3 = tns.checkNecessity(tn.getName());
        assertFalse(test3);
    }
}
