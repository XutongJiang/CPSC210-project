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
        System.out.println("b. Make change on necessities.");
        System.out.println("c. Get a alert of what will run out in the following week.");
        System.out.println("d. Update list (Use it once a day unless you use extra amount).");
        System.out.println("e. Exit the program.");
        String selection = inp.nextLine();
        makeSelection(selection);

    }

    //EFFECTS: make a selection from a to e
    private void makeSelection(String selection) {
        switch (selection) {
            case "a":
                checkNecessities();
                break;
            case "b":
                makeChangeNecessities();
                break;
            case "c":
                sendAlert();
                break;
            case "d":
                updateList();
                break;
            default:
                System.out.println("See you next time!");
                break;
        }
    }

    //EFFECTS: let users check current status of a necessity if it exists in the list,
    //         if the amount is <= 0, ask the user to make purchase until it is above 0
    public void checkNecessities() {
        System.out.println("What necessity would you like to check? Please type in the name "
                + "with all characters in lowercase: ");
        String checked = input.next();
        if (currentList.checkNecessity(checked)) {
            double amt = currentList.returnSpecificAmount(checked);
            double du = currentList.returnSpecificUsage(checked);
            int day = currentList.returnRemainingDay(checked);
            if (amt == 0) {
                System.out.println("There is not enough " + checked + " left, please make a purchase.");
            } else {
                System.out.println(checked + "'s remaining amount is " + amt);
                System.out.println(checked + "'s daily usage is " + du);
                System.out.println(checked + " will run out in " + day + " days.");
            }

        } else {
            System.out.println("Sorry, we cannot find the necessity you just entered in the list, so we will go back "
                    + "to the main menu.");
        }
        System.out.println();
        makeSelection();
    }

    //EFFECTS: let user choose from the three options which would make change to the list
    public void makeChangeNecessities() {
        System.out.println("Would you like to add or remove a necessity or just purchased some existed necessities?");
        System.out.println("a. add b. remove c. make purchase");
        String answer = input.next();
        switch (answer) {
            case "a":
                selectA();
                break;
            case "b":
                selectB();
                break;
            case "c":
                selectC();
                break;
            default:
                System.out.println("You did not enter a valid option, you will be take back to main menu.");
                System.out.println();
                makeSelection();
                break;
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
                        + " no more than a week.");
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

    //REQUIRES: this can only be done once a day.
    //MODIFIES: this
    //EFFECTS: subtract one daily amount from the remaining amount,
    //         if after the subtract the amount will be below 0, then stop and ask the user to make a purchase
    public void updateList() {
        System.out.println("Please type the name of the necessity you want to update or type all if you want to"
                + " update the whole list:");
        Scanner inp = new Scanner(System.in);
        String s = inp.nextLine();
        if (s.equals("all")) {
            currentList.updateNecessities();
        } else {
            if (currentList.checkNecessity(s)) {
                Necessity item = currentList.returnGivenNecessity(s);
                item.updateNecessity();
                System.out.println("The update has been done!");
            } else {
                System.out.println("The necessity you are looking for does not exist in the necessities list, thus"
                        + " we cannot update it.");
            }
        }
        System.out.println();
        makeSelection();
    }


}
