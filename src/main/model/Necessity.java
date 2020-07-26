package model;

// Represents a necessity of life with its name, average daily usage and remaining amount
public class Necessity {
    private String name;
    private double dailyUsage;
    private double amount;

    // REQUIRES: both amount and dailyUsage >= 0
    // EFFECTS: name of the necessity is set to nam,
    //          daily usage is set to usage,
    //          remaining amount of necessity is set to amt.
    public Necessity(String nam, double usage, double amt) {
        this.name = nam;
        this.dailyUsage = usage;
        this.amount = amt;
    }

    public String getName() {
        return name;
    }

    public double getDailyUsage() {
        return dailyUsage;
    }

    public double getAmount() {
        return amount;
    }

    //MODIFIES: this
    //EFFECTS: change the current daily usage of a necessity
    public void setDailyUsage(double amt) {
        this.dailyUsage = amt;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: purchase a amount of certain necessity and add the amount to its remaining and returns true,
    //          if the purchase not enough, returns false.
    public boolean makePurchase(double amt) {
        if ((this.amount + amt) >= (7 * this.dailyUsage)) {
            this.amount = amount + amt;
            return true;
        } else {
            this.amount = amount + amt;
            return false;
        }
    }


}
