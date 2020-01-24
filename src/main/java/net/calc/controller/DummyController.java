package net.calc.controller;

public class DummyController implements Controller {
    @Override
    public void buttonPressed(String label) {
        System.out.println(label);
        System.out.flush();
    }
}
