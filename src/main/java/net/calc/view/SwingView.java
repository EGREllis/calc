package net.calc.view;

import net.calc.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingView implements View {
    private final Controller controller;
    private final JTextField display = new JTextField("0");
    private final JFrame window = new JFrame("Calculator");

    public SwingView(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        window.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridwidth = 10;
        window.add(display, constraints);
        constraints.gridwidth = 1;

        String[][] labels = new String[][]{
                {"(", ")", "MC", "M+", "M-", "MR", "AC", "+/-", "%", "/"},
                {"2nd", "x^2", "x^3", "x^y", "e^x", "10^x", "7", "8", "9", "*"},
                {"1/x", "x^1/2", "x^1/3", "x^1/y", "ln", "log10", "4", "5", "6", "-"},
                {"x!", "sin", "cos", "tan", "e", "EE", "1", "2", "3", "+"},
                {"Rad", "sinh", "cosh", "tanh", "pi", "Rand", "0", ".", "="}
        };

        for (int y = 0; y < labels.length; y++) {
            for (int x = 0; x < labels[y].length; x++) {
                String label = labels[y][x];
                JButton button = new JButton(label);
                button.addActionListener(new DummyActionListener(label));
                if (y == 4 && x > 6) {
                    constraints.gridx = x + 1;
                    constraints.gridy = y + 1;
                } else {
                    constraints.gridx = x;
                    constraints.gridy = y + 1;
                }
                if (labels[y][x].equals("0")) {
                    constraints.gridwidth = 2;
                } else {
                    constraints.gridwidth = 1;
                }
                window.add(button, constraints);
            }
        }
        window.setVisible(true);
        window.pack();
    }

    public void updateDisplay() {
        String newDisplay = controller.getDisplay();
        display.setText(newDisplay);

    }

    private class DummyActionListener implements ActionListener {
        public final String text;

        public DummyActionListener(String text) {
            this.text = text;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SwingView.this.controller.buttonPressed(text);
        }
    }
}
