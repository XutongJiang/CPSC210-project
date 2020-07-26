package ui;

import model.Necessities;
import model.Necessity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

// Necessities management and notification application
public class NecessitiesManager {
    private Scanner input;
    private Necessities currentList;

    public NecessitiesManager() {
        runManager();
    }

    // EFFECTS: run the manager application
    public void runManager() {
        input = new Scanner(System.in);
        String selection;
        currentList = new Necessities();

        System.out.println("How can I help? Please choose from the following two options by simply type a, b or c.");
        System.out.println("a. Check the status of one certain necessity.");
        System.out.println("b. Update list.");
        System.out.println("c. Get a alert of what will run out in the following week.");
        selection = input.nextLine();
        if (selection.equals("a")) {
            checkNecessities();
        } else if (selection.equals("b")) {
            updatePurchase();
        } else {
            sendAlert();
        }
    }

    //
    public void checkNecessities() {
        System.out.println("What necessity would you like to check? Please type in the name without punctuation: ");
        String checked = input.next();
        if (currentList.checkNecessity(checked)) {
            double amt = currentList.returnSpecificAmount("checked");
            double du = currentList.returnSpecificUsage("checked");
            int day = currentList.returnRemainingDay("checked");
            System.out.println(checked + "'s remaining amount is " + amt + ", daily usage is "
                    + du + " and will run out in " + day + " days.");
        } else {
            System.out.println("Sorry, we cannot find the necessity you just entered in the list, try to update "
                    + "or check if there is a typo.");
        }


    }

    //
    public void updatePurchase() {


    }

    //
    public void sendAlert() {

    }


}
