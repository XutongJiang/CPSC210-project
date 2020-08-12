package ui;

import model.Necessities;
import model.Necessity;
import persistence.Reader;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import static org.omg.CORBA.ORB.init;

// Necessities management and notification application
public class NecessitiesManager extends JFrame {
    private Scanner input = new Scanner(System.in);
    private Necessities currentList = new Necessities();
    private LinkedList<String> newList;
    private static final String NECESSITIES_FILE = "./data/necessities.txt";
    private SaveListButton saveListButton;
    private CheckStatusButton checkStatusButton;

    public NecessitiesManager() {
        super("Necessities Manager");
        loadList();
        JPanel cards = new JPanel(new CardLayout());
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("Thank you for using my Necessities Manager, "
                + "your file has been loaded from last saving.");
        saveListButton = new SaveListButton(cards,currentList);
        checkStatusButton = new CheckStatusButton(cards, currentList);
        jp1.add(jl1);
        jp1SetButtons(jp1, cards);
        cards.add(jp1, "card1");
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "card1");
        add(cards);
        setBounds(600, 300, 600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EFFECTS: plays sound when certain event happens
    public void playSound() {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("./data/windowsBackground.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
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

    public void jp1SetButtons(JPanel jp, JPanel cards) {
        JButton btn1 = checkStatusButton.getButton();
        JButton btn2 = new JButton("Make Change");
        JButton btn3 = new JButton("Get Alert");
        JButton btn4 = new JButton("Update List");
        JButton btn5 = saveListButton.getButton();
        JButton btn6 = new JButton("Exit");
        setButton2(cards, btn2);
        setButton3(cards, btn3);
        setButton4(cards, btn4);
        setButton6(btn6);
        jp.add(btn1);
        jp.add(btn2);
        jp.add(btn3);
        jp.add(btn4);
        jp.add(btn5);
        jp.add(btn6);
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
                    playSound();
                } else {
                    jl6.setText("It seems currently there isn't any necessity in the list.");
                }
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

    public static void main(String[] args) {
        new NecessitiesManager();
    }

}
