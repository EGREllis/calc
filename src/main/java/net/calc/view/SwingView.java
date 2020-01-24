package net.calc.view;

import javax.swing.*;
import java.awt.*;

public class SwingView {
    private JTextField display = null;

    public void start() {
        JFrame window = new JFrame("Calculator");
        window.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;

        display = new JTextField("0");
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
                JButton button = new JButton(labels[y][x]);
                constraints.gridx = x;
                constraints.gridy = y+1;
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



    public static void main(String[] args) {
        SwingView view = new SwingView();
        view.start();
    }
}
