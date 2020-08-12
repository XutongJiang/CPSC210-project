package ui;

import model.Necessities;

import javax.swing.*;

public abstract class Button {
    JButton button;
    JPanel cards;
    Necessities necessities;

    public Button(String name, JPanel card, Necessities n) {
        button = new JButton(name);
        this.cards = card;
        this.necessities = n;
    }

    public JButton getButton() {
        return button;
    }
}
