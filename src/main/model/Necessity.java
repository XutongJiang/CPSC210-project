package model;

import persistence.Reader;
import persistence.Savable;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

// Represents a necessity of life with its name, average daily usage and remaining amount
public class Necessity implements Savable {
    private String name;
    private double dailyUsage;
    private double amount;
    private static int nextNecessityId = 1;
    private int id;

    // REQUIRES: both amount and dailyUsage >= 0,
    //           y, m, d together form a valid date.
    // EFFECTS: name of the necessity is set to nam,
    //          daily usage is set to usage,
    //          remaining amount of necessity is set to amt,
    //          last checked date is set to y, m, d
    public Necessity(String nam, double usage, double amt) {
        id = nextNecessityId++;
        this.name = nam;
        this.dailyUsage = usage;
        this.amount = amt;
    }

    // This constructor is to be used only when constructing
    // an account from data stored in file
    public Necessity(int nextId, int id, String nam, double usage, double amt) {
        nextNecessityId = nextId;
        this.id = id;
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

    public int getId() {
        return  id;
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
        if ((this.amount + amt) > (7 * this.dailyUsage)) {
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
        return amount / dailyUsage < 8;
    }

    public void updateNecessity() {
        this.amount = Math.max(amount - dailyUsage, 0.0);
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(nextNecessityId);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(id);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(name);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(dailyUsage);
        printWriter.print(Reader.DELIMITER);
        printWriter.println(amount);
    }

}
