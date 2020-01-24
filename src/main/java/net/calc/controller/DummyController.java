package net.calc.controller;

import net.calc.view.View;

public class DummyController implements Controller {
    @Override
    public void setView(View view) {

    }

    @Override
    public String getDisplay() {
        return null;
    }

    @Override
    public void buttonPressed(String label) {
        System.out.println(label);
        System.out.flush();
    }
}
