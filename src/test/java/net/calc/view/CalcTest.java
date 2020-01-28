package net.calc.view;

import net.calc.controller.Controller;
import net.calc.controller.ControllerImpl;
import net.calc.model.Model;
import net.calc.model.ModelImpl;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.Map;

public class CalcTest {
    private SwingView view;

    @Before
    public void setup() {
        Model model = new ModelImpl();
        Controller controller = new ControllerImpl(model);
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
        assert "123".equals(view.getDisplay());
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
