package model;

import java.util.LinkedList;

// Represents a list of the remaining necessities at home
public class Necessities {
    LinkedList<Necessity> necessities;

    //EFFECTS: initialize each newly created necessities list as an empty list
    public Necessities() {
        necessities = new LinkedList<>();
    }

    //EFFECTS: check if the certain type of necessity has already existed in the necessities list.
    public boolean checkNecessity(Necessity n) {
        for (Necessity i : necessities) {
            if (n.getName().equals(i.getName())) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: add a new necessity to the necessities list and returns true,
    //         but if it has already existed, returns false
    public boolean addNecessity(Necessity n) {
        if (checkNecessity(n)) {
            return false;
        } else {
            necessities.add(n);
            return true;
        }
    }
}
