package net.calc;

import net.calc.controller.Controller;
import net.calc.controller.ControllerImpl;
import net.calc.model.Memory;
import net.calc.model.MemoryImpl;
import net.calc.model.Model;
import net.calc.model.ModelImpl;
import net.calc.view.SwingView;
import net.calc.view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new ModelImpl();
        Memory memory = new MemoryImpl();
        Controller controller = new ControllerImpl(model, memory);
        model.setController(controller);
        View view = new SwingView(controller);
        controller.setView(view);

        view.start();
    }
}
