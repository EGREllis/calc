package net.calc;

import net.calc.controller.Controller;
import net.calc.controller.ControllerImpl;
import net.calc.model.*;
import net.calc.model.Mathematic;
import net.calc.view.SwingView;
import net.calc.view.View;

public class Main {
    public static void main(String[] args) {
        Mathematic mathematic = new MathematicImpl();
        Memory memory = new MemoryImpl();
        Display display = new DisplayImpl();
        Controller controller = new ControllerImpl(mathematic, memory, display);
        View view = new SwingView(controller);

        view.start();
    }
}
