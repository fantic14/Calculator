package app.controller;

import app.view.CalculatorWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AdditionAction implements EventHandler<ActionEvent> {

    private final CalculatorWindow cw;

    public AdditionAction(CalculatorWindow calculatorWindow) {
        this.cw = calculatorWindow;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!cw.getTfResult().getText().isEmpty()) {
            if ((cw.getTfResult().getText().charAt(cw.getTfResult().getLength() - 1) >= '0' && cw.getTfResult().getText().charAt(cw.getTfResult().getLength() - 1) <= '9') ||
                    cw.getTfResult().getText().charAt(cw.getTfResult().getText().length() - 1) == ')') {
                cw.getTfResult().setText(cw.getTfResult().getText() + "+");
            }
        }
    }
}
