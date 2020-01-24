package net.calc;

import net.calc.controller.Controller;
import net.calc.controller.ControllerImpl;
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
        Controller controller = new ControllerImpl();
        View view = new SwingView(controller);
        controller.setView(view);

        view.start();
    }
}
