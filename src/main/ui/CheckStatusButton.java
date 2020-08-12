package ui;

import exceptions.NotInListException;
import model.Necessities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckStatusButton extends Button {

    public CheckStatusButton(JPanel card, Necessities n) {
        super("Check Status", card, n);
        setButton1(button);
    }

    //EFFECTS: set function of button1
    public void setButton1(JButton btn1) {
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setJp2(cards);
                } catch (NotInListException notInListException) {
                    JPanel jp2 = new JPanel();
                    JLabel jl2 = new JLabel("An error has occur.");
                    jp2.add(jl2);
                    JButton btn7 = new JButton("Go Back To Main Menu");
                    jp2.add(btn7);
                    goBackMainMenu(cards, btn7);
                    cards.add(jp2, "card2");
                }
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "card2");
            }
        });
    }

    //EFFECTS: set properties of jp2
    public void setJp2(JPanel cards) throws NotInListException {
        JPanel jp2 = new JPanel();
        JLabel jl2 = new JLabel("The following item(s) is currently in the list: " + necessities.returnCurrentList());
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
    public void displayInformation(JPanel jp) throws NotInListException {
        for (String s : necessities.returnCurrentList()) {
            double amt = necessities.returnSpecificAmount(s);
            double du = necessities.returnSpecificUsage(s);
            int day = necessities.returnRemainingDay(s);
            JLabel jln = new JLabel(s + "'s remaining amount: " + amt + ", daily usage: " + du + ", will run out"
                    + " in " + day + " day(s)");
            jp.add(jln);
        }
        ImageIcon img = new ImageIcon("./data/unnamed.jpg");
        JLabel jlVisual = new JLabel(img);
        jp.add(jlVisual);
    }

}
