package app.controller;

import app.view.CalculatorWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class KeyboardEntryAction implements EventHandler<KeyEvent> {

    private final CalculatorWindow cw;
    private final AdditionAction additionAction;
    private final DivisionAction divisionAction;
    private final MultiplicationAction multiplicationAction;
    private final SubstractionAction substractionAction;

    public KeyboardEntryAction(CalculatorWindow calculatorWindow) {
        this.cw = calculatorWindow;
        this.additionAction = new AdditionAction(cw);
        this.divisionAction = new DivisionAction(cw);
        this.multiplicationAction = new MultiplicationAction(cw);
        this.substractionAction = new SubstractionAction(cw);
    }

    @Override
    public void handle(KeyEvent e) {
        if (e.getCode() == KeyCode.BACK_SPACE){
            char[] s = cw.getTfResult().getText().toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length-1; i++){
                sb.append(s[i]);
            }
            cw.getTfResult().setText(sb.toString());
        }

        switch (e.getCode()){
            case PLUS: case ADD:
                cw.getOpPlus().fire();
                break;
            case EQUALS:
                if (e.isShiftDown()) cw.getOpPlus().fire();
                break;
            case MINUS: case SUBTRACT:
                cw.getOpMinus().fire();
                break;
            case MULTIPLY:
                cw.getOpMultiplication().fire();
                break;
            case DIGIT8:
                if (e.isShiftDown()) cw.getOpMultiplication().fire();
                break;
            case DIVIDE:
                cw.getOpDivision().fire();
                break;
        }

        if (e.getCode() == KeyCode.DECIMAL || e.getCode() == KeyCode.PERIOD)
            cw.getTfResult().setText(cw.getTfResult().getText() + ".");

        for (int i = 0; i < 10; i++) {
            if (Objects.equals(e.getText(), String.valueOf(i)))
                cw.getTfResult().setText(cw.getTfResult().getText() + i);
        }
    }
}
