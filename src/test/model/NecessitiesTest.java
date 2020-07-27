package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class NecessitiesTest {
    private Necessities tns;
    private Necessity tn;
    @BeforeEach
    public void setUp() {
        tns = new Necessities();
        tn = new Necessity("test", 1.0, 10.0);
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
        Necessity tn2 = new Necessity("test", 1.0,1.0);
        boolean test2 = tns.addNecessity(tn2);
        assertFalse(test2);
    }

    @Test
    public void testRemoveNotAlreadyExists() {
        boolean test = tns.removeNecessity(tn.getName());
        assertFalse(test);
    }

    @Test
    public void testRemoveAlreadyExists() {
        String nam = tn.getName();
        boolean test1 = tns.addNecessity(tn);
        assertTrue(test1);
        assertTrue(tns.checkNecessity(nam));
        boolean test2 = tns.removeNecessity(nam);
        assertTrue(test2);
        boolean test3 = tns.checkNecessity(nam);
        assertFalse(test3);
    }

    @Test
    public void testReturnSpecificAmount() {
        tns.addNecessity(tn);
        assertEquals(tns.returnSpecificAmount("test"), 10.0);
        assertEquals(tns.returnSpecificAmount("random"), 0.0);


    }

    @Test
    public void testReturnSpecificUsage() {
        tns.addNecessity(tn);
        assertEquals(tns.returnSpecificUsage(tn.getName()), 1.0);
        assertEquals(tns.returnSpecificUsage("random"), 0.0);
    }

    @Test
    public void testReturnRemainingDay() {
        tns.addNecessity(tn);
        assertEquals(tns.returnRemainingDay(tn.getName()), 10);
        assertEquals(tns.returnRemainingDay("random"), 0);
    }

    @Test
    public void testReturnNotInListNecessity() {
        tns.addNecessity(tn);
        assertEquals(tns.returnGivenNecessity("test2"), null);
    }

    @Test
    public void testRunOutInWeekList() {
        Necessity tn2 = new Necessity("test2", 1.0, 5.0);
        tns.addNecessity(tn2);
        LinkedList<String> tl = new LinkedList<>();
        tns.runOutInWeekList(tl);
        assertEquals(tl.size(), 1);
        assertTrue(tl.contains("test2"));
    }

    @Test
    public void testUpdateNecessities() {
        Necessity tn2 = new Necessity("test2", 2.0, 1.0);
        tns.addNecessity(tn);
        tns.addNecessity(tn2);
        tns.updateNecessities();
        assertEquals(tn.getAmount(), 9.0);
        assertEquals(tn2.getAmount(), 0.0);
    }
}
