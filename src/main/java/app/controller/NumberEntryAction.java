package app.controller;

import app.view.CalculatorWindow;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class NumberEntryAction implements EventHandler<KeyEvent> {

    private final CalculatorWindow cw;

    public NumberEntryAction(CalculatorWindow calculatorWindow) {
        this.cw = calculatorWindow;
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

        if ((e.getCode() == KeyCode.DECIMAL || e.getCode() == KeyCode.PERIOD)&& !cw.getTfResult().getText().contains("."))
            cw.getTfResult().setText(cw.getTfResult().getText() + ".");

        for (int i = 0; i < 10; i++) {
            if (Objects.equals(e.getText(), String.valueOf(i)))
                cw.getTfResult().setText(cw.getTfResult().getText() + i);
        }
    }
}
