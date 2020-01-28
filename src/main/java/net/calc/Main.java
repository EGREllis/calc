package net.calc;

import net.calc.controller.Controller;
import net.calc.controller.ControllerImpl;
import net.calc.model.*;
import net.calc.view.SwingView;
import net.calc.view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new ModelImpl();
        Memory memory = new MemoryImpl();
        Display display = new DisplayImpl();
        Controller controller = new ControllerImpl(model, memory, display);
        model.setController(controller);
        View view = new SwingView(controller);
        controller.setView(view);

        view.start();
    }
}
