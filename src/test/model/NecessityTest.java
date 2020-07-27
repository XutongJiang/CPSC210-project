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

    @Test
    public void testGetName() {
        assertEquals(tn.getName(), "test");
    }

    @Test
    public void testGetAmount() {
        assertEquals(tn.getAmount(), 0.0);
    }

    @Test
    public void testGetRemainingDay() {
        Necessity tn2 = new Necessity("test", 1.0, 10.0);
        assertEquals(tn.getRemainingDay(), 0);
        assertEquals(tn2.getRemainingDay(), 10);
    }

    @Test
    public void testRunOutInWeek() {
        Necessity tn2 = new Necessity("test", 1.0, 10.0);
        Necessity tn3 = new Necessity("test", 1.0, 5.0);
        assertFalse(tn2.runOutInWeek());
        assertTrue(tn3.runOutInWeek());
    }
}