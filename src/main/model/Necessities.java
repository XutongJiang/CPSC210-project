package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

// Represents a list of the remaining necessities at home
public class Necessities {
    LinkedList<Necessity> necessities;

    //EFFECTS: initialize each newly created necessities list as an empty list
    public Necessities() {
        necessities = new LinkedList<>();
    }

    //EFFECTS: check if the certain type of necessity has already existed in the necessities list.
    public boolean checkNecessity(String n) {
        for (Necessity i : necessities) {
            if (n.equals(i.getName())) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: add a new necessity to the necessities list and returns true,
    //         but if it has already existed, returns false
    public boolean addNecessity(Necessity n) {
        if (checkNecessity(n.getName())) {
            return false;
        } else {
            necessities.add(n);
            return true;
        }
    }

    //MODIFIES: this
    //EFFECTS: remove a no longer needed necessity from the necessities list and returns true,
    //         if the necessity does not exist in the list, returns false
    public boolean removeNecessity(Necessity n) {
        if (checkNecessity(n.getName())) {
            necessities.remove(n);
            return true;
        } else {
            return false;
        }
    }

    //
    public double returnSpecificAmount(String s) {
        for (Necessity i : necessities) {
            if (s.equals(i.getName())) {
                return i.getAmount();
            }
        }
        return 0.0;
    }

    //
    public double returnSpecificUsage(String s) {
        for (Necessity i : necessities) {
            if (s.equals(i.getName())) {
                return i.getDailyUsage();
            }
        }
        return 0.0;
    }

    //
    public int returnRemainingDay(String s) {
        for (Necessity i : necessities) {
            if (s.equals(i.getName())) {
                return i.getRemainingDay();
            }
        }
        return 0;
    }

}