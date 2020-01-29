package net.calc.view;

import net.calc.controller.Controller;
import net.calc.controller.ControllerOperation;
import net.calc.model.DisplayOperation;
import net.calc.model.MemoryOperation;
import net.calc.model.MathematicOperation;

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
                {MathematicOperation.LEFT_BRACKET.getLabel(), MathematicOperation.RIGHT_BRACKET.getLabel(), MemoryOperation.MEMORY_CLEAR.getLabel(), MemoryOperation.MEMORY_ADD.getLabel(), MemoryOperation.MEMORY_SUBTRACT.getLabel(), MemoryOperation.MEMORY_RECALL.getLabel(), ControllerOperation.ALL_CLEAR.getLabel(), DisplayOperation.PLUS_MINUS.getLabel(), MathematicOperation.PERCENTAGE.getLabel(), MathematicOperation.DIVIDE.getLabel()},
                {"2nd", MathematicOperation.SQUARE.getLabel(), MathematicOperation.CUBE.getLabel(), MathematicOperation.TO_THE_POWER.getLabel(), MathematicOperation.E_TO_POWER.getLabel(), MathematicOperation.TEN_TO_POWER.getLabel(), DisplayOperation.SEVEN.getLabel(), DisplayOperation.EIGHT.getLabel(), DisplayOperation.NINE.getLabel(), MathematicOperation.MULTIPLY.getLabel()},
                {MathematicOperation.RECIPROCAL.getLabel(), MathematicOperation.SQUARE_ROOT.getLabel(), MathematicOperation.CUBE_ROOT.getLabel(), MathematicOperation.NUMBER_TO_ROOT.getLabel(), MathematicOperation.NATURAL_LOG.getLabel(), MathematicOperation.LOG_10.getLabel(), DisplayOperation.FOUR.getLabel(), DisplayOperation.FIVE.getLabel(), DisplayOperation.SIX.getLabel(), MathematicOperation.SUBTRACT.getLabel()},
                {MathematicOperation.FACTORIAL.getLabel(), MathematicOperation.SIN.getLabel(), MathematicOperation.COS.getLabel(), MathematicOperation.TAN.getLabel(), MathematicOperation.RECALL_E.getLabel(), "EE", DisplayOperation.ONE.getLabel(), DisplayOperation.TWO.getLabel(), DisplayOperation.THREE.getLabel(), MathematicOperation.ADD.getLabel()},
                {"Rad", MathematicOperation.SINH.getLabel(), MathematicOperation.COSH.getLabel(), MathematicOperation.TANH.getLabel(), MathematicOperation.RECALL_PI.getLabel(), MathematicOperation.RECALL_RAND.getLabel(), DisplayOperation.ZERO.getLabel(), DisplayOperation.POINT.getLabel(), ControllerOperation.EQUALS.getLabel()}
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
            String newDisplay = SwingView.this.controller.buttonPressed(text);
            display.setText(newDisplay);
        }
    }
}
