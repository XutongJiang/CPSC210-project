package model;

import persistence.Savable;
import persistence.Writer;

import java.io.PrintWriter;
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
    public boolean checkNecessity(String n) throws NullPointerException {
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
    public boolean removeNecessity(String n) {
        if (checkNecessity(n)) {
            Necessity item = returnGivenNecessity(n);
            necessities.remove(item);
            return true;
        } else {
            return false;
        }
    }

    //REQUIRES: the necessity with give name must have been found in the list
    //EFFECTS: returns the amount of the necessity with the given name
    public double returnSpecificAmount(String s) {
        for (Necessity i : necessities) {
            if (s.equals(i.getName())) {
                return i.getAmount();
            }
        }
        return 0.0;
    }

    //REQUIRES: the necessity with give name must have been found in the list
    //EFFECTS: returns the daily usage of the necessity with the given name
    public double returnSpecificUsage(String s) {
        for (Necessity i : necessities) {
            if (s.equals(i.getName())) {
                return i.getDailyUsage();
            }
        }
        return 0.0;
    }

    //REQUIRES: the necessity with give name must have been found in the list
    //EFFECTS: returns the remaining days of the necessity with the given name
    public int returnRemainingDay(String s) {
        for (Necessity i : necessities) {
            if (s.equals(i.getName())) {
                return i.getRemainingDay();
            }
        }
        return 0;
    }

    //REQUIRES: the necessity must be in the necessities list
    //EFFECTS: return the necessity in the list with given name
    public Necessity returnGivenNecessity(String n) {
        for (Necessity i : necessities) {
            if (n.equals(i.getName())) {
                return i;
            }
        }
        return null;
    }

    //EFFECTS: return a list of necessities' name which will run out in a week
    public LinkedList<String> runOutInWeekList(LinkedList<String> m) {
        for (Necessity i : necessities) {
            if (i.runOutInWeek()) {
                m.add(i.getName());
            }
        }
        return m;
    }

    //MODIFIES: this
    //EFFECTS: update every necessity in the list by subtracting daily usage from remaining amount
    public boolean updateNecessities() {
        if (!necessities.isEmpty()) {
            for (Necessity i : necessities) {
                i.updateNecessity();
            }
            System.out.println("All of the necessities in the list have been updated!");
            return true;
        } else {
            System.out.println("It seems that currently there isn't any necessity in the list.");
            return false;
        }
    }

    // MODIFIES: printWriter
    // EFFECTS: record the savable to printWriter
    public void saveList(Writer writer) {
        for (Necessity necessity : necessities) {
            writer.write(necessity);
        }
    }

    // EFFECTS: returns the current list of all necessities
    public LinkedList<String> returnCurrentList() {
        LinkedList<String> list = new LinkedList<>();
        for (Necessity i : necessities) {
            list.add(i.getName());
        }
        return list;
    }

}