package app.controller;

import app.view.CalculatorWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SubstractionAction implements EventHandler<ActionEvent> {

    private final CalculatorWindow cw;

    public SubstractionAction(CalculatorWindow calculatorWindow) {
        this.cw = calculatorWindow;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (cw.getTfResult().getText().isEmpty()){
            cw.getTfResult().setText("-");
            return;
        }
        if ((cw.getTfResult().getText().charAt(cw.getTfResult().getLength()-1) >= '0' && cw.getTfResult().getText().charAt(cw.getTfResult().getLength()-1) <= '9') ||
            cw.getTfResult().getText().charAt(cw.getTfResult().getText().length()-1) == '(' || cw.getTfResult().getText().charAt(cw.getTfResult().getText().length()-1) == ')') {
            cw.getTfResult().setText(cw.getTfResult().getText() + "-");
        }
    }
}
