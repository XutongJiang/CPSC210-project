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
        currentList = new Necessities();

        makeSelection();
    }

        //EFFECTS: provide several selections and take to different result.
    private void makeSelection() {
        Scanner inp = new Scanner(System.in);
        System.out.println("How can I help? Please choose from the following two options by simply type a, b, c or d.");
        System.out.println("a. Check the status of one certain necessity.");
        System.out.println("b. Update list.");
        System.out.println("c. Get a alert of what will run out in the following week.");
        System.out.println("d. Exit the program.");
        String selection = inp.nextLine();
        if (selection.equals("a")) {
            checkNecessities();
        } else if (selection.equals("b")) {
            updatePurchase();
        } else if (selection.equals("c")) {
            sendAlert();
        } else {
            System.out.println("See you next time!");
        }
    }

    //
    public void checkNecessities() {
        System.out.println("What necessity would you like to check? Please type in the name "
                + "with all characters in lowercase: ");
        String checked = input.next();
        if (currentList.checkNecessity(checked)) {
            double amt = currentList.returnSpecificAmount(checked);
            double du = currentList.returnSpecificUsage(checked);
            int day = currentList.returnRemainingDay(checked);
            System.out.println(checked + "'s remaining amount is " + amt + ", daily usage is "
                    + du + " and will run out in " + day + " days.");
            makeSelection();
        } else {
            System.out.println("Sorry, we cannot find the necessity you just entered in the list, so we will go back "
                    + "to the main menu.");
            makeSelection();
        }
    }

    //
    public void updatePurchase() {
        System.out.println("Would you like to add or remove a necessity or modify one?");
        System.out.println("a. add b. remove c. modify");
        String answer = input.next();
        if (answer.equals("a")) {
            System.out.println("Please type in the name of the necessity: ");
            String name = input.next();
            System.out.println("Then type in the daily estimate usage of the necessity (in the format: integer.0): ");
            double usage = input.nextDouble();
            System.out.println("Then type in the amount of the necessity (in the format: integer.0): ");
            double amt = input.nextDouble();
            Necessity addOne = new Necessity(name, usage, amt);
            if (currentList.addNecessity(addOne)) {
                System.out.println("Great! The necessity has been successfully added. We will go back to the main menu")
                ;
                makeSelection();
            } else {
                System.out.println("Sorry,the necessity you just entered has already existed in the list,"
                        + " so we will go back to the main menu.");
                makeSelection();
            }
        }

    }

    //
    public void sendAlert() {

    }


}
