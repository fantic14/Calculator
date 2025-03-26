package app.controller;

import app.view.CalculatorWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EqualsAction implements EventHandler<ActionEvent> {

    private final CalculatorWindow cw;
    private String tfResult;
    private final Map<String, ArrayList<Integer>> map = new HashMap<>();
    private double inBracketsResult = 0;
    private double result;
    private char operatorOne = '!';
    private char operatorTwo = '!';
    private boolean flagOne = true;
    private boolean flagTwo = false;
    private boolean flagThree = false;
    private double numberOne = 0;
    private double numberTwo = 0;
    private double numberThree = 0;
    private int jumpFrom = -1;
    private int jumpTo = -1;

    public EqualsAction(CalculatorWindow calculatorWindow) {
        this.cw = calculatorWindow;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.tfResult = cw.getTfResult().getText();
        map.put("(", new ArrayList<>());
        map.put(")", new ArrayList<>());
        for (int i = 0; i < tfResult.length(); i++) {
            if (tfResult.charAt(i) == '(')
                map.get("(").add(i);
            if (tfResult.charAt(i) == ')')
                map.get(")").add(i);
        }
        while (!map.get("(").isEmpty()){
            calculate(true);
        }
        calculate(false);
        DecimalFormat df = new DecimalFormat("#.##");
        cw.getTfResult().setText(df.format(result));
    }

    private void calculate(boolean inBrackets){
        int j;
        if (inBrackets) {

            j = map.get("(").getLast() + 1;

            if (tfResult.charAt(j) == '-') {
                flagOne = false;
                flagTwo = true;
                j = firstPositiveToNegative(++j);
            }

            calcForLoop(j, map.get(")").getFirst());

            jumpFrom = map.get("(").removeLast();
            jumpTo = map.get(")").removeFirst();

            if (operatorTwo != '!'){
                switch (operatorTwo){
                    case '*':
                        numberTwo *= numberThree;
                        break;
                    case '/':
                        numberTwo /= numberThree;
                        break;
                }
                numberThree = 0;
                operatorTwo = '!';
                flagThree = false;
            }
            switch (operatorOne) {
                case '+':
                    numberOne += numberTwo;
                    break;
                case '-':
                    numberOne -= numberTwo;
                    break;
                case '*':
                    numberOne *= numberTwo;
                    break;
                case '/':
                    numberOne /= numberTwo;
                    break;
            }
            numberTwo = 0;
            inBracketsResult = numberOne;
            numberOne = 0;
            operatorOne = '!';
            flagTwo = false;
            flagOne = true;
        } else {
            j = 0;
            if (tfResult.charAt(0) == '-') {
                flagOne = false;
                flagTwo = true;
                j = firstPositiveToNegative(++j);
            }

            calcForLoop(j, tfResult.length());

            jumpFrom = -1;
            jumpTo = -1;
            switch (operatorOne) {
                case '+':
                    numberOne += numberTwo;
                    break;
                case '-':
                    numberOne -= numberTwo;
                    break;
                case '*':
                    numberOne *= numberTwo;
                    break;
                case '/':
                    numberOne /= numberTwo;
                    break;
            }
            numberTwo = 0;
            result = numberOne;
            numberOne = 0;
            operatorOne = '!';
            flagTwo = false;
            flagOne = true;
        }
    }

    private void calcForLoop(int j, int last){
        for (int i = j; i < last; i++) {
            if (i == jumpFrom) {
                if (flagOne && operatorOne == '!')
                    numberOne = inBracketsResult;
                else if (flagTwo && operatorTwo == '!') {
                    switch (operatorOne) {
                        case '*':
                            numberOne *= inBracketsResult;
                            operatorOne = '!';
                            break;
                        case '/':
                            numberOne /= inBracketsResult;
                            operatorOne = '!';
                            break;
                        case '+':
                        case '-':
                            numberTwo = inBracketsResult;
                            break;
                        default:
                            System.err.println("Invalid operator");
                            break;
                    }
                } else {
                    switch (operatorTwo) {
                        case '*':
                            numberTwo *= inBracketsResult;
                            operatorTwo = '!';
                            break;
                        case '/':
                            numberTwo /= inBracketsResult;
                            operatorTwo = '!';
                            break;
                    }
                }
                i = jumpTo;
                continue;
            }

            if ((tfResult.charAt(i) >= '0' && tfResult.charAt(i) <= '9') && flagOne) {
                numberOne = numberOne * 10 + Double.parseDouble(String.valueOf(tfResult.charAt(i)));
            } else if ((tfResult.charAt(i) >= '0' && tfResult.charAt(i) <= '9') && flagTwo) {
                numberTwo = numberTwo * 10 + Double.parseDouble(String.valueOf(tfResult.charAt(i)));
            } else if ((tfResult.charAt(i) >= '0' && tfResult.charAt(i) <= '9') && flagThree) {
                numberThree = numberThree * 10 + Double.parseDouble(String.valueOf(tfResult.charAt(i)));
            } else if (tfResult.charAt(i) == '+' || tfResult.charAt(i) == '-') {
                if (operatorTwo != '!') {
                    if (operatorTwo == '*')
                        numberTwo *= numberThree;
                    else if (operatorTwo == '/')
                        numberTwo /= numberThree;
                    numberThree = 0;
                    if (operatorOne == '+')
                        numberOne += numberTwo;
                    else if (operatorOne == '-')
                        numberOne -= numberTwo;
                    numberTwo = 0;
                    operatorTwo = '!';
                } else if (operatorOne != '!') {
                    if (operatorOne == '+')
                        numberOne += numberTwo;
                    else if (operatorOne == '-')
                        numberOne -= numberTwo;
                    else if (operatorOne == '*')
                        numberOne *= numberTwo;
                    else if (operatorOne == '/')
                        numberOne /= numberTwo;
                    numberTwo = 0;
                }
                operatorOne = tfResult.charAt(i);
                flagOne = false;
                flagTwo = true;
                flagThree = false;
            } else if (tfResult.charAt(i) == '*' || tfResult.charAt(i) == '/') {
                if (flagThree) {
                    if (operatorTwo == '*')
                        numberTwo *= numberThree;
                    else if (operatorTwo == '/')
                        numberTwo /= numberThree;
                    numberThree = 0;
                    operatorTwo = tfResult.charAt(i);
                } else if (operatorOne != '!') {
                    if (operatorOne == '*') {
                        numberOne *= numberTwo;
                        numberTwo = 0;
                        operatorOne = tfResult.charAt(i);
                    } else if (operatorOne == '/') {
                        numberOne /= numberTwo;
                        numberTwo = 0;
                        operatorOne = tfResult.charAt(i);
                    } else {
                        operatorTwo = tfResult.charAt(i);
                        flagTwo = false;
                        flagThree = true;
                    }
                } else {
                    operatorOne = tfResult.charAt(i);
                    flagOne = false;
                    flagTwo = true;
                }
            }
        }
    }

    private int firstPositiveToNegative(int j){
        while (tfResult.charAt(j) >= '0' && tfResult.charAt(j) <= '9') {
            numberOne = numberOne * 10 + Double.parseDouble(String.valueOf(tfResult.charAt(j++)));
        }
        numberOne = -numberOne;
        return j;
    }
}
