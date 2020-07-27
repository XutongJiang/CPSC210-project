package model;

import java.util.Calendar;
import java.util.Date;

// Represents a necessity of life with its name, average daily usage and remaining amount
public class Necessity {
    private String name;
    private double dailyUsage;
    private double amount;

    // REQUIRES: both amount and dailyUsage >= 0,
    //           y, m, d together form a valid date.
    // EFFECTS: name of the necessity is set to nam,
    //          daily usage is set to usage,
    //          remaining amount of necessity is set to amt,
    //          last checked date is set to y, m, d
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

    public int getRemainingDay() {
        return (int) (amount / dailyUsage);
    }

    //MODIFIES: this
    //EFFECTS: set the current daily usage of a necessity
    public void setDailyUsage(double amt) {
        this.dailyUsage = amt;
    }

    //MODIFIES: this
    //EFFECTS: set the current amount of a necessity
    public void setAmount(double amt) {
        this.amount = amt;
    }


    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: purchase a amount of certain necessity and add the amount to its remaining and returns true,
    //          if the purchase not enough, returns false.
    public boolean makePurchase(double amt) {
        if ((this.amount + amt) >= (7 * this.dailyUsage)) {
            setAmount(amount + amt);
            return true;
        } else {
            setAmount(amount + amt);
            return false;
        }
    }

    // REQUIRES: amount >= 0
    // EFFECTS: return true if the necessity is gonna run out in a week
    public boolean runOutInWeek() {
        return amount / dailyUsage <= 7;
    }

}
