package ui;

public class Main {
    public static void main(String[] args) {
        System.out.println("Thank you for using this necessities manager, let's get tarted!");
        System.out.println("It is recommended to save the necessities list after each time you make change to it,"
                + "otherwise you may lose the updated information after exiting.");
        new NecessitiesManager();
    }
}
