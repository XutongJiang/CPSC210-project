package ui;

import model.Necessities;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

// check status button and its following buttons
public class SaveListButton extends Button {
    private static final String NECESSITIES_FILE = "./data/necessities.txt";

    public SaveListButton(JPanel card, Necessities n) {
        super("Save List", card, n);
        setButton5(button);
    }

    //EFFECTS: set function of button5
    public void setButton5(JButton btn5) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Writer writer = new Writer(new File(NECESSITIES_FILE));
                    necessities.saveList(writer);
                    writer.close();
                    JPanel jp3 = new JPanel();
                    JLabel jl3 = new JLabel("The list has been successfully saved!");
                    JButton btn8 = new JButton("Go Back To Main Menu");
                    setButton8(btn8);
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
    public void setButton8(JButton btn8) {
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, "card1");
            }
        });
    }
}
