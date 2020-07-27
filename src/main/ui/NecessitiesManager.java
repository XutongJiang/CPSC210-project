package ui;

import model.Necessities;
import model.Necessity;

import java.util.LinkedList;
import java.util.Scanner;

// Necessities management and notification application
public class NecessitiesManager {
    private Scanner input;
    private Necessities currentList;
    private LinkedList<String> newList;

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
        newList = new LinkedList<>();
        Scanner inp = new Scanner(System.in);
        System.out.println("How can I help? Please choose from the following two options by simply type a, b, c or d.");
        System.out.println("a. Check the status of one certain necessity.");
        System.out.println("b. Update list.");
        System.out.println("c. Get a alert of what will run out in the following week.");
        System.out.println("d. Refresh the daily status.");
        System.out.println("e. Exit the program.");
        String selection = inp.nextLine();
        if (selection.equals("a")) {
            checkNecessities();
        } else if (selection.equals("b")) {
            updatePurchase();
        } else if (selection.equals("c")) {
            sendAlert();
        } else if (selection.equals("d")) {
            refreshStatus();
        } else {
            System.out.println("See you next time!");
        }

    }

    //EFFECTS: let users check current status of a necessity if it exists in the list
    public void checkNecessities() {
        System.out.println("What necessity would you like to check? Please type in the name "
                + "with all characters in lowercase: ");
        String checked = input.next();
        if (currentList.checkNecessity(checked)) {
            double amt = currentList.returnSpecificAmount(checked);
            double du = currentList.returnSpecificUsage(checked);
            int day = currentList.returnRemainingDay(checked);
            System.out.println(checked + "'s remaining amount is " + amt);
            System.out.println(checked + "'s daily usage is " + du);
            System.out.println(checked + "will run out in " + day + " days.");
        } else {
            System.out.println("Sorry, we cannot find the necessity you just entered in the list, so we will go back "
                    + "to the main menu.");
        }
        System.out.println();
        makeSelection();
    }

    //EFFECTS: let user choose from the three options which would make change to the list
    public void updatePurchase() {
        System.out.println("Would you like to add or remove a necessity or just purchased some existed necessities?");
        System.out.println("a. add b. remove c. make purchase");
        String answer = input.next();
        if (answer.equals("a")) {
            selectA();
        } else if (answer.equals("b")) {
            selectB();
        } else if (answer.equals("c")) {
            selectC();
        } else {
            System.out.println("You did not enter a valid option, you will be take back to main menu.");
            System.out.println();
            makeSelection();
        }

    }

    //MODIFIES: this
    //EFFECTS: add a necessity with name, daily usage and amount to the list
    private void selectA() {
        System.out.println("Please type in the name of the necessity: ");
        String name = input.next();
        if (currentList.checkNecessity(name)) {
            System.out.println("Sorry,the necessity you just entered has already existed in the list,"
                    + " so we will go back to the main menu.");
        } else {
            System.out.println("Then type in the daily estimate usage of the necessity (please enter a double): ");
            double usage = input.nextDouble();
            System.out.println("Then type in the amount of the necessity (please enter a double): ");
            double amt = input.nextDouble();
            Necessity addOne = new Necessity(name, usage, amt);
            currentList.addNecessity(addOne);
            System.out.println("Great! The necessity has been successfully added. We will go back to the main menu")
            ;
        }
        System.out.println();
        makeSelection();
    }

    //MODIFIES: this
    //EFFECTS: remove a necessity with given name from the list
    private void selectB() {
        System.out.println("Please type in the name of the necessity: ");
        String name = input.next();
        if (currentList.removeNecessity(name)) {
            System.out.println("Great! The necessity has been successfully removed. We will go back to the main menu")
            ;
        } else {
            System.out.println("Sorry,the necessity you just entered does not exist in the list,"
                    + " so we will go back to the main menu.");
        }
        System.out.println();
        makeSelection();
    }

    //MODIFIES: this
    //EFFECTS: modify a necessity's remaining amount after purchasing,
    //         or suggest to add one if the necessity does not exist in the list
    private void selectC() {
        System.out.println("Please type in the name of the necessity: ");
        String name = input.next();
        if (currentList.checkNecessity(name)) {
            Necessity n = currentList.returnGivenNecessity(name);
            System.out.println("How many " + name + " have you purchased today? (please enter a positive double)");
            double amt = input.nextDouble();
            if (n.makePurchase(amt)) {
                System.out.println("The purchased amount has been successfully added and the remaining will last "
                        + "for more than a week.");
            } else {
                System.out.println("The purchased amount has been successfully added but the remaining will still last"
                        + " no more than a week");
            }
        } else {
            System.out.println("The name you entered does not exist in the list, please use add method instead.");
        }
        System.out.println();
        makeSelection();
    }

    //EFFECTS: print out the names of necessities which would run out in a week
    public void sendAlert() {
        LinkedList<String> runOutList = currentList.runOutInWeekList(newList);
        if (runOutList.size() == 0) {
            System.out.println("All remaining necessities in the list can last more than a week");
        } else {
            for (String nam : runOutList) {
                System.out.println(nam + " will run out soon in a week.");
            }
        }
        System.out.println();
        makeSelection();
    }

    //MODIFIES: this
    //EFFECTS: set the last checked date of necessity to the system time,
    //         and if the former date is one day or more before the system time,
    //         subtract the corresponding daily cost from the amount
    public void refreshStatus() {

    }


}
