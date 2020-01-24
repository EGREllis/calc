package net.calc;

import net.calc.controller.Controller;
import net.calc.controller.ControllerImpl;
import net.calc.model.Model;
import net.calc.model.ModelImpl;
import net.calc.view.SwingView;
import net.calc.view.View;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Model model = new ModelImpl();
        Controller controller = new ControllerImpl(model);
        View view = new SwingView(controller);
        controller.setView(view);
        model.setController(controller);

        view.start();
    }
}
