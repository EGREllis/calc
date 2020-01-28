package net.calc;

import net.calc.controller.Controller;
import net.calc.controller.ControllerImpl;
import net.calc.model.*;
import net.calc.view.SwingView;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.Map;

public class CalcTest {
    private SwingView view;

    @Before
    public void setup() {
        Model model = new ModelImpl();
        Memory memory = new MemoryImpl();
        Display display = new DisplayImpl();
        Controller controller = new ControllerImpl(model, memory, display);
        view = new SwingView(controller) {
            public void start() {
                setDefaultClose(WindowConstants.DISPOSE_ON_CLOSE);
                super.start();
            }
        };
        controller.setView(view);
        model.setController(controller);
        view.start();
    }

    @Test
    public void when_calculatorStarted_then_displaysZero() {
        assert "0".equals(view.getDisplay());
    }

    @Test
    public void when_digitsEntered_numberIsDisplayed() {
        pressButtons("1", "2", "3");
        assert "123".equals(view.getDisplay()) : view.getDisplay();
    }

    @Test
    public void checkAddition() {
        pressButtons("1", "0", "+");
        assertDisplay("10");
        pressButtons("5");
        assertDisplay("5");
        pressButtons("=");
        assertDisplay("15");
    }

    @Test
    public void checkSubtraction() {
        pressButtons("2", "3", "-");
        assertDisplay("23");
        pressButtons("4");
        assertDisplay("4");
        pressButtons("=");
        assertDisplay("19");
    }

    @Test
    public void checkMultiply() {
        pressButtons("4", "5", "*");
        assertDisplay("45");
        pressButtons("6");
        assertDisplay("6");
        pressButtons("=");
        assertDisplay("270");
    }

    @Test
    public void checkDivide() {
        pressButtons("6", "8", "/");
        assertDisplay("68");
        pressButtons("2");
        assertDisplay("2");
        pressButtons("=");
        assertDisplay("34");
    }

    @Test
    public void checkE() {
        pressButtons("e");
        assertDisplay(Double.toString(Math.E));
    }

    @Test
    public void checkPi() {
        pressButtons("pi");
        assertDisplay(Double.toString(Math.PI));
    }

    @Test
    public void checkRandom() {
        pressButtons("Rand");
        double first = Double.parseDouble(view.getDisplay());
        assert first >= 0 && first <= 1;
        pressButtons("Rand");
        double second = Double.parseDouble(view.getDisplay());
        assert second >= 0 && second <= 1;
        assert first != second;
    }

    @Test
    public void checkPercentage() {
        pressButtons("9", "%");
        assertDisplay("0.09");
    }

    @Test
    public void checkFactoral() {
        pressButtons("5", "x!");
        assertDisplay("120");
    }

    @Test
    public void checkReciprocal() {
        pressButtons("2", "1/x");
        assertDisplay("0.5");
    }

    @Test
    public void checkSquare() {
        pressButtons("3", "x^2");
        assertDisplay("9");
        pressButtons("x^2");
        assertDisplay("81");
    }

    @Test
    public void checkCube() {
        pressButtons("2", "x^3");
        assertDisplay("8");
    }

    @Test
    public void checkSquareRoot() {
        pressButtons("4", "x^1/2");
        assertDisplay("2");
    }

    @Test
    public void checkCubeRoot() {
        pressButtons("2", "7", "x^1/3");
        assertDisplay("3");
    }

    @Test
    public void checkNaturalLog() {
        pressButtons("e", "ln");
        assertDisplay("1");
        pressButtons("AC", "e", "x^2", "ln");
        assertDisplay("2");
    }

    @Test
    public void checkLog10() {
        pressButtons("1", "0", "log");
        assertDisplay("1");
        pressButtons("AC", "1", "0", "0", "0", "log");
        assertDisplay("3");
    }

    @Test
    public void checkSin() {
        pressButtons("0", "sin");
        assertDisplay("0");
        pressButtons("AC", "9", "0", "sin");
        assertDisplay("1");
    }

    @Test
    public void checkCos() {
        pressButtons("0", "cos");
        assertDisplay("1");
        pressButtons("AC", "9", "0", "cos");
        assertDisplay("6.123233995736766E-17"); // Should be 0, but degree to radians!
    }

    @Test
    public void checkTan() {
        pressButtons("4", "5", "tan");
        assertDisplay("0.9999999999999999");    // Should be 1, but degrees to radians!
    }

    @Test
    public void checkSinh() {
        pressButtons("1", "sinh");
        assertDisplay("1.1752011936438014");
    }

    @Test
    public void checkCosh() {
        pressButtons("1", "cosh");
        assertDisplay("1.543080634815244");
    }

    @Test
    public void checkTanh() {
        pressButtons("1", "tanh");
        assertDisplay("0.7615941559557649");
    }

    @Test
    public void checkMemory() {
        pressButtons("5", "M+", "AC", "MR");
        assertDisplay("5");
        pressButtons("3");
        assertDisplay("3");
        pressButtons("M-", "MR");
        assertDisplay("2");
    }

    private void assertDisplay(String expected) {
        assert expected.equals(view.getDisplay()) : String.format("Expected: %1$s Actual: %2$s", expected, view.getDisplay());
    }

    private void pressButtons(String ...buttons) {
        Map<String, JButton> swingButtons = view.getButtons();
        for (String button : buttons) {
            JButton swingButton = swingButtons.get(button);
            swingButton.doClick();
        }
    }
}
