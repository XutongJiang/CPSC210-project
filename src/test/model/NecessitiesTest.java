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
        tn = new Necessity("test", 0, 0);
    }

    @Test
    public void testNotAlreadyExists() {
        boolean test = tns.addNecessity(tn);
        assertTrue(test);
        boolean test2 = tns.checkNecessity(tn);
        assertTrue(test2);
    }

    @Test
    public void testAlreadyExists() {
        boolean test = tns.addNecessity(tn);
        Necessity tn2 = new Necessity("test", 1,1);
        boolean test2 = tns.addNecessity(tn2);
        assertFalse(test2);
    }
}
