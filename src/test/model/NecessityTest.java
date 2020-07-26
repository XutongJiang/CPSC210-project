package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NecessityTest {
    private Necessity tn;

    @BeforeEach
    public void setUp() {
        tn = new Necessity("test", 0.0, 0.0);
    }

    @Test
    public void testMakeEnoughPurchase() {
        tn.setDailyUsage(1.0);
        assertEquals(tn.getDailyUsage(), 1.0);
        assertTrue(tn.makePurchase(7.0));
    }

    @Test
    public void testMakeNotEnoughPurchase() {
        tn.setDailyUsage(1.0);
        assertFalse(tn.makePurchase(5.0));
    }

    @Test
    public void checkDailyUsage() {
        tn.setDailyUsage(1.0);
        double test = tn.getDailyUsage();
        assertEquals(test, 1.0);
    }
}