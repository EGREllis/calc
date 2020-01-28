package net.calc.view;

import net.calc.controller.Controller;
import net.calc.model.DisplayOperation;
import net.calc.model.MemoryOperation;
import net.calc.model.MathOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SwingView implements View {
    private int defaultClose = WindowConstants.EXIT_ON_CLOSE;
    private final Map<String,JButton> buttons;
    private final Controller controller;
    private final JTextField display = new JTextField("0");
    private final JFrame window = new JFrame("Calculator");

    public SwingView(Controller controller) {
        this.controller = controller;
        this.buttons = new HashMap<>();
    }

    @Override
    public String getDisplay() {
        return display.getText();
    }

    protected void setDefaultClose(int defaultClose) {
        this.defaultClose = defaultClose;
    }

    @Override
    public void start() {
        window.setDefaultCloseOperation(defaultClose);
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
                {"(", ")", MemoryOperation.MEMORY_CLEAR.getLabel(), MemoryOperation.MEMORY_ADD.getLabel(), MemoryOperation.MEMORY_SUBTRACT.getLabel(), MemoryOperation.MEMORY_RECALL.getLabel(), "AC", DisplayOperation.PLUS_MINUS.getLabel(), MathOperation.PERCENTAGE.getLabel(), MathOperation.DIVIDE.getLabel()},
                {"2nd", MathOperation.SQUARE.getLabel(), MathOperation.CUBE.getLabel(), "x^y", "e^x", "10^x", DisplayOperation.SEVEN.getLabel(), DisplayOperation.EIGHT.getLabel(), DisplayOperation.NINE.getLabel(), MathOperation.MULTIPLY.getLabel()},
                {MathOperation.RECIPROCAL.getLabel(), MathOperation.SQUARE_ROOT.getLabel(), MathOperation.CUBE_ROOT.getLabel(), "x^1/y", MathOperation.NATURAL_LOG.getLabel(), MathOperation.LOG_10.getLabel(), DisplayOperation.FOUR.getLabel(), DisplayOperation.FIVE.getLabel(), DisplayOperation.SIX.getLabel(), MathOperation.SUBTRACT.getLabel()},
                {MathOperation.FACTORIAL.getLabel(), MathOperation.SIN.getLabel(), MathOperation.COS.getLabel(), MathOperation.TAN.getLabel(), MathOperation.RECALL_E.getLabel(), "EE", DisplayOperation.ONE.getLabel(), DisplayOperation.TWO.getLabel(), DisplayOperation.THREE.getLabel(), MathOperation.ADD.getLabel()},
                {"Rad", MathOperation.SINH.getLabel(), MathOperation.COSH.getLabel(), MathOperation.TANH.getLabel(), MathOperation.RECALL_PI.getLabel(), MathOperation.RECALL_RAND.getLabel(), DisplayOperation.ZERO.getLabel(), DisplayOperation.POINT.getLabel(), "="}
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
                buttons.put(label, button);
                window.add(button, constraints);
            }
        }
        window.setVisible(true);
        window.pack();
    }

    public Map<String,JButton> getButtons() {
        return Collections.unmodifiableMap(buttons);
    }

    @Override
    public void updateDisplay() {
        String newDisplay = controller.getDisplay();
        display.setText(newDisplay);

    }

    @Override
    public void stop() {
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
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
