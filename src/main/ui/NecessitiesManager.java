package ui;

import model.Necessities;
import model.Necessity;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Scanner;

import static org.omg.CORBA.ORB.init;

// Necessities management and notification application
public class NecessitiesManager extends JFrame {
    private Scanner input = new Scanner(System.in);
    private Necessities currentList = new Necessities();
    private LinkedList<String> newList;
    private static final String NECESSITIES_FILE = "./data/necessities.txt";

    public NecessitiesManager() {
        super("Necessities Manager");
        loadList();
        JPanel cards = new JPanel(new CardLayout());
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("Thank you for using my Necessities Manager, "
                + "your file has been loaded from last saving.");
        jp1.add(jl1);
        jp1Buttons(jp1, cards);
        cards.add(jp1, "card1");
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "card1");
        add(cards);
        setBounds(600, 300, 600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //runManager();
    }

    //EFFECTS: set properties of jp2
    public void setJp2(JPanel cards) {
        JPanel jp2 = new JPanel();
        JLabel jl2 = new JLabel("The following item(s) is currently in the list: " + currentList.returnCurrentList());
        jp2.add(jl2);
        JButton btn7 = new JButton("Go Back To Main Menu");
        jp2.add(btn7);
        displayInformation(jp2);
        goBackMainMenu(cards, btn7);
        cards.add(jp2, "card2");
    }

    //EFFECTS: set a listener which will lead user to main menu
    public void goBackMainMenu(JPanel cards, JButton btn7) {
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "card1");
            }
        });
    }

    //EFFECTS: display all the items and their information
    public void displayInformation(JPanel jp) {
        for (String s : currentList.returnCurrentList()) {
            double amt = currentList.returnSpecificAmount(s);
            double du = currentList.returnSpecificUsage(s);
            int day = currentList.returnRemainingDay(s);
            JLabel jln = new JLabel(s + "'s remaining amount: " + amt + ", daily usage: " + du + ", will run out"
                    + " in " + day + " day(s)");
            jp.add(jln);
        }
        ImageIcon img = new ImageIcon("./data/unnamed.jpg");
        JLabel jlVisual = new JLabel(img);
        jp.add(jlVisual);
    }

    public void jp1Buttons(JPanel jp, JPanel cards) {
        JButton btn1 = new JButton("Check Status");
        JButton btn2 = new JButton("Make Change");
        JButton btn3 = new JButton("Get Alert");
        JButton btn4 = new JButton("Update List");
        JButton btn5 = new JButton("Save List");
        JButton btn6 = new JButton("Exit");
        setButton1(cards, btn1);
        setButton2(cards, btn2);
        setButton3(cards, btn3);
        setButton4(cards, btn4);
        setButton5(cards, btn5);
        setButton6(btn6);
        jp.add(btn1);
        jp.add(btn2);
        jp.add(btn3);
        jp.add(btn4);
        jp.add(btn5);
        jp.add(btn6);
    }

    //EFFECTS: set function of button1
    public void setButton1(JPanel cards, JButton btn1) {
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setJp2(cards);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "card2");
            }
        });
    }

    //EFFECTS: set function of button2
    public void setButton2(JPanel cards, JButton btn2) {
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel jp4 = new JPanel();
                JLabel jl4 = new JLabel();
                JButton btn9 = new JButton("Add Amount");
                JButton btn10 = new JButton("Add Necessity");
                JButton btn11 = new JButton("Remove Necessity");
                JButton btn12 = new JButton("Go Back To Main Menu");
                goBackMainMenu(cards, btn12);
                setButton9(jp4, jl4, btn9);
                setButton10(jp4, jl4, btn10);
                setButton11(jp4, jl4, btn11);
                jp4.add(jl4);
                jp4.add(btn9);
                jp4.add(btn10);
                jp4.add(btn11);
                jp4.add(btn12);
                cards.add(jp4, "card4");
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "card4");
            }
        });
    }

    //EFFECTS: set the function of button3
    public void setButton3(JPanel cards, JButton btn3) {
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel jp6 = new JPanel();
                JButton btn14 = new JButton("Go Back To Main Menu");
                goBackMainMenu(cards, btn14);
                jp6.add(btn14);
                cards.add(jp6, "card6");
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "card6");
                newList = new LinkedList<>();
                LinkedList<String> runOutList = currentList.runOutInWeekList(newList);
                if (runOutList.size() == 0) {
                    JLabel jl0 = new JLabel("All remaining necessities in the list can last more than a week");
                    jp6.add(jl0);
                } else {
                    for (String name : runOutList) {
                        JLabel jl01 = new JLabel(name + " will run out soon in a week.");
                        jp6.add(jl01);
                    }
                }
            }
        });
    }

    //EFFECTS: set function of button5
    public void setButton5(JPanel cards, JButton btn5) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Writer writer = new Writer(new File(NECESSITIES_FILE));
                    currentList.saveList(writer);
                    writer.close();
                    JPanel jp3 = new JPanel();
                    JLabel jl3 = new JLabel("The list has been successfully saved!");
                    JButton btn8 = new JButton("Go Back To Main Menu");
                    setButton8(btn8, cards, cl);
                    jp3.add(jl3);
                    jp3.add(btn8);
                    cards.add(jp3, "card3");
                    cl.show(cards, "card3");
                } catch (FileNotFoundException e1) {
                    cl.show(cards, "card1");
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    //EFFECTS: set the function of button8
    public void setButton8(JButton jb, JPanel card, CardLayout cardLayout) {
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(card, "card1");
            }
        });

    }

    //EFFECTS: set the function of button9
    public void setButton9(JPanel jp4, JLabel jl4, JButton btn9) {

        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String) JOptionPane.showInputDialog(
                        jp4, "Please Type the name: ", "Input", JOptionPane.PLAIN_MESSAGE);
                if (!currentList.checkNecessity(result)) {
                    jl4.setText("The necessity is not in the list!");
                } else {
                    String result2 = (String) JOptionPane.showInputDialog(
                            jp4, "Please Type the amount you want to add: ", "Input", JOptionPane.PLAIN_MESSAGE);
                    Double dou = Double.valueOf(result2.toString());
                    Necessity n = currentList.returnGivenNecessity(result);
                    n.setAmount(n.getAmount() + dou);
                    jl4.setText("Adding successfully done! Now there are " + n.getAmount() + " " + n.getName()
                            + " which will last for " + n.getRemainingDay() + " day(s)");
                }
            }
        });
    }

    //EFFECTS: set function of button10 which add items to the list
    public void setButton10(JPanel jp4, JLabel jl4, JButton btn10) {
        btn10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result3 = (String) JOptionPane.showInputDialog(
                        jp4, "Please Type the name: ", "Input", JOptionPane.PLAIN_MESSAGE);
                if (currentList.checkNecessity(result3)) {
                    jl4.setText("The necessity is already in the list!");
                } else {
                    String result4 = (String) JOptionPane.showInputDialog(
                            jp4, "Please set the estimate daily usage of the necessity: ", "Input",
                            JOptionPane.PLAIN_MESSAGE);
                    String result5 = (String) JOptionPane.showInputDialog(
                            jp4, "Please set the amount of the necessity: ", "Input",
                            JOptionPane.PLAIN_MESSAGE);
                    Double dou = Double.valueOf(result4.toString());
                    Double dou2 = Double.valueOf(result5.toString());
                    Necessity addOne = new Necessity(result3, dou, dou2);
                    currentList.addNecessity(addOne);
                    jl4.setText("The necessity has been successfully added!");
                }
            }
        });
    }

    //EFFECTS: set the function of button11 which remove a necessity from the list
    public void setButton11(JPanel jp4, JLabel jl4, JButton btn11) {
        btn11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result6 = (String) JOptionPane.showInputDialog(
                        jp4, "Please Type the name: ", "Input", JOptionPane.PLAIN_MESSAGE);
                if (!currentList.checkNecessity(result6)) {
                    jl4.setText("The necessity is not in the list!");
                } else {
                    currentList.removeNecessity(result6);
                    jl4.setText("The necessity has been successfully removed!");
                }

            }
        });
    }

    // EFFECTS: set the function of button12
    public void setButton12(JLabel jl6, JButton btn12) {
        btn12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean updateInf = currentList.updateNecessities();
                if (updateInf) {
                    jl6.setText("The list has been successfully updated!");
                } else {
                    jl6.setText("It seems currently there isn't any necessity in the list.");
                }
            }
        });
    }

    //EFFECTS: set the function of button4
    public void setButton4(JPanel cards, JButton btn4) {
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel jp5 = new JPanel();
                JLabel jl5 = new JLabel("Every necessity in the list will be updated by subtracting daily usage from"
                        + " amount. If you want to do so, please click the button.");
                JLabel jl6 = new JLabel();
                JButton btn12 = new JButton("Update");
                JButton btn13 = new JButton("Go Back To Main Menu");
                goBackMainMenu(cards, btn13);
                jp5.add(jl5);
                jp5.add(btn12);
                jp5.add(btn13);
                jp5.add(jl6);
                cards.add(jp5, "card5");
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "card5");
                setButton12(jl6, btn12);
            }
        });
    }

    //EFFECTS: exit the program when choose f
    public void setButton6(JButton btn6) {
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads necessities list from NECESSITIES_FILE, if that file exists;
    // otherwise initializes an empty list
    private void loadList() {
        try {
            currentList = Reader.readNecessities(new File(NECESSITIES_FILE));
        } catch (IOException e) {
            init();
        }
    }

//    // EFFECTS: saves state of necessities list to ACCOUNTS_FILE
//    private void saveNecessity(Necessities n) {
//        try {
//            Writer writer = new Writer(new File(NECESSITIES_FILE));
//            n.saveList(writer);
//            writer.close();
//            System.out.println("The current necessities list has been successfully saved!");
//            System.out.println();
//            makeSelection();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save Necessity to the list.");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // EFFECTS: run the manager application
//    public void runManager() {
//        makeSelection();
//    }
//
//    //EFFECTS: provide several selections and take to different result.
//    private void makeSelection() {
//        newList = new LinkedList<>();
//        Scanner inp = new Scanner(System.in);
//        System.out.println("Please choose from the following options by simply type a, b, c, d, e or f.");
//        System.out.println("a. Check the status of one certain necessity.");
//        System.out.println("b. Make change on necessities.");
//        System.out.println("c. Get a alert of what will run out in the following week.");
//        System.out.println("d. Update list (Use it once a day unless you use extra amount).");
//        System.out.println("e. Save list.");
//        System.out.println("f. Exit the program.");
//        String selection = inp.nextLine();
//        makeSelection(selection);
//
//    }
//
//    //EFFECTS: make a selection from a to e
//    private void makeSelection(String selection) {
//        switch (selection) {
//            case "a":
//                checkNecessities();
//                break;
//            case "b":
//                makeChangeNecessities();
//                break;
//            case "c":
//                sendAlert();
//                break;
//            case "d":
//                updateList();
//                break;
//            case "e":
//                saveNecessity(currentList);
//                break;
//            default:
//                System.out.println("See you next time!");
//                System.exit(0);
//                break;
//        }
//    }
//
//    //EFFECTS: let users check current status of a necessity if it exists in the list,
//    //         if the amount is <= 0, ask the user to make purchase until it is above 0
//    public void checkNecessities() {
//        System.out.println("The following item(s) is currently in the list: " + currentList.returnCurrentList());
//        System.out.println("What necessity would you like to check? Please type in the name "
//                + "with all characters in lowercase: ");
//        String checked = input.next();
//        if (currentList.checkNecessity(checked)) {
//            double amt = currentList.returnSpecificAmount(checked);
//            double du = currentList.returnSpecificUsage(checked);
//            int day = currentList.returnRemainingDay(checked);
//            if (amt == 0) {
//                System.out.println("There is not enough " + checked + " left, please make a purchase.");
//            } else {
//                System.out.println(checked + "'s remaining amount is " + amt);
//                System.out.println(checked + "'s daily usage is " + du);
//                System.out.println(checked + " will run out in " + day + " days.");
//            }
//
//        } else {
//            System.out.println("Sorry, we cannot find the necessity you just entered in the list, so we will go back "
//                    + "to the main menu.");
//        }
//        makeSelection();
//    }
//
//
//    //EFFECTS: let user choose from the three options which would make change to the list
//    public void makeChangeNecessities() {
//        System.out.println("Would you like to add or remove a necessity or just purchased some existed necessities?");
//        System.out.println("a. add b. remove c. make purchase");
//        String answer = input.next();
//        switch (answer) {
//            case "a":
//                selectA();
//                break;
//            case "b":
//                selectB();
//                break;
//            case "c":
//                selectC();
//                break;
//            default:
//                System.out.println("You did not enter a valid option, you will be take back to main menu.");
//                System.out.println();
//                makeSelection();
//                break;
//        }
//
//    }
//
//    //MODIFIES: this
//    //EFFECTS: add a necessity with name, daily usage and amount to the list
//    private void selectA() {
//        System.out.println("Please type in the name of the necessity: ");
//        String name = input.next();
//        if (currentList.checkNecessity(name)) {
//            System.out.println("Sorry,the necessity you just entered has already existed in the list,"
//                    + " so we will go back to the main menu.");
//        } else {
//            System.out.println("Then type in the daily estimate usage of the necessity (please enter a double): ");
//            double usage = input.nextDouble();
//            System.out.println("Then type in the amount of the necessity (please enter a double): ");
//            double amt = input.nextDouble();
//            Necessity addOne = new Necessity(name, usage, amt);
//            currentList.addNecessity(addOne);
//            System.out.println("Great! The necessity has been successfully added. We will go back to the main menu")
//            ;
//        }
//        System.out.println();
//        makeSelection();
//    }
//
//    //MODIFIES: this
//    //EFFECTS: remove a necessity with given name from the list
//    private void selectB() {
//        System.out.println("Please type in the name of the necessity: ");
//        String name = input.next();
//        if (currentList.removeNecessity(name)) {
//            System.out.println("Great! The necessity has been successfully removed. We will go back to the main menu")
//            ;
//        } else {
//            System.out.println("Sorry,the necessity you just entered does not exist in the list,"
//                    + " so we will go back to the main menu.");
//        }
//        System.out.println();
//        makeSelection();
//    }
//
//    //MODIFIES: this
//    //EFFECTS: modify a necessity's remaining amount after purchasing,
//    //         or suggest to add one if the necessity does not exist in the list
//    private void selectC() {
//        System.out.println("Please type in the name of the necessity: ");
//        String name = input.next();
//        if (currentList.checkNecessity(name)) {
//            Necessity n = currentList.returnGivenNecessity(name);
//            System.out.println("How many " + name + " have you purchased today? (please enter a positive double)");
//            double amt = input.nextDouble();
//            if (n.makePurchase(amt)) {
//                System.out.println("The purchased amount has been successfully added and the remaining will last "
//                        + "for more than a week.");
//            } else {
//                System.out.println("The purchased amount has been successfully added but the remaining will still
//                + "last no more than a week.");
//
//            }
//        } else {
//            System.out.println("The name you entered does not exist in the list, please use add method instead.");
//        }
//        System.out.println();
//        makeSelection();
//    }
//
//    //EFFECTS: print out the names of necessities which would run out in a week
//    public void sendAlert() {
//        LinkedList<String> runOutList = currentList.runOutInWeekList(newList);
//        if (runOutList.size() == 0) {
//            System.out.println("All remaining necessities in the list can last more than a week");
//        } else {
//            for (String nam : runOutList) {
//                System.out.println(nam + " will run out soon in a week.");
//            }
//        }
//        System.out.println();
//        makeSelection();
//    }
//
//    //REQUIRES: this can only be done once a day.
//    //MODIFIES: this
//    //EFFECTS: subtract one daily amount from the remaining amount,
//    //         if after the subtract the amount will be below 0, then stop and ask the user to make a purchase
//    public void updateList() {
//        System.out.println("Please type the name of the necessity you want to update or type all if you want to"
//                + " update the whole list:");
//        Scanner inp = new Scanner(System.in);
//        String s = inp.nextLine();
//        if (s.equals("all")) {
//            currentList.updateNecessities();
//        } else {
//            if (currentList.checkNecessity(s)) {
//                Necessity item = currentList.returnGivenNecessity(s);
//                item.updateNecessity();
//                System.out.println("The update has been done!");
//            } else {
//                System.out.println("The necessity you are looking for does not exist in the necessities list, thus"
//                        + " we cannot update it.");
//            }
//        }
//        System.out.println();
//        makeSelection();
//    }

    public static void main(String[] args) {
        //    System.out.println("Thank you for using this necessities manager, let's get tarted!");
        //    System.out.println("It is recommended to save the necessities list after each time you make change to it,"
        //            + "otherwise you may lose the updated information after exiting.");
        new NecessitiesManager();
    }

}
