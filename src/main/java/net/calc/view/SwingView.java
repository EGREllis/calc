package net.calc.view;

import net.calc.controller.Controller;
import net.calc.model.Operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
                {"(", ")", Operation.MEMORY_CLEAR.getLabel(), Operation.MEMORY_ADD.getLabel(), Operation.MEMORY_SUBTRACT.getLabel(), Operation.MEMORY_RECALL.getLabel(), "AC", "+/-", "%", Operation.DIVIDE.getLabel()},
                {"2nd", Operation.SQUARE.getLabel(), Operation.CUBE.getLabel(), "x^y", "e^x", "10^x", "7", "8", "9", Operation.MULTIPLY.getLabel()},
                {"1/x", Operation.SQUARE_ROOT.getLabel(), Operation.CUBE_ROOT.getLabel(), "x^1/y", "ln", "log10", "4", "5", "6", Operation.SUBTRACT.getLabel()},
                {"x!", Operation.SIN.getLabel(), Operation.COS.getLabel(), Operation.TAN.getLabel(), Operation.RECALL_E.getLabel(), "EE", "1", "2", "3", Operation.ADD.getLabel()},
                {"Rad", Operation.SINH.getLabel(), Operation.COSH.getLabel(), Operation.TANH.getLabel(), Operation.RECALL_PI.getLabel(), Operation.RECALL_RAND.getLabel(), "0", ".", "="}
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
        window.addWindowListener(new CloseApp());
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

    private static class CloseApp implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Closing application");
            System.out.flush();
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }
}
