package app.view;

import app.controller.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CalculatorWindow extends GridPane {

    private Button numOne;
    private Button numTwo;
    private Button numThree;
    private Button numFour;
    private Button numFive;
    private Button numSix;
    private Button numSeven;
    private Button numEight;
    private Button numNine;
    private Button numZero;
    private Button opEquals;
    private Button opPlus;
    private Button opMinus;
    private Button opMultiplication;
    private Button opDivision;
    private Button btnBackspace;
    private Button btnClear;
    private Button btnComma;
    private Button btnOpenBracket;
    private Button btnCloseBracket;
    private int bracketCnt = 0;
    private TextField tfResult;

    public CalculatorWindow() {
        init();
        show();
        action();
    }

    private void action() {
        // upis preko tastature u textfield
        this.setOnKeyPressed(new KeyboardEntryAction(this));
        this.tfResult.setOnKeyPressed(new KeyboardEntryAction(this));

        // clear dugme
        this.btnClear.setOnAction(_ -> {
            this.tfResult.clear();
        });

        // upis preko dugmica u aplikaciji
        this.numZero.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + "0");
        });
        this.numOne.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + "1");
        });
        this.numTwo.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + "2");
        });
        this.numThree.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + "3");
        });
        this.numFour.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + "4");
        });
        this.numFive.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + "5");
        });
        this.numSix.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + "6");
        });
        this.numSeven.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + "7");
        });
        this.numEight.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + "8");
        });
        this.numNine.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + "9");
        });
        this.btnComma.setOnAction(_ ->{
            tfResult.setText(tfResult.getText() + ".");
        });
        this.btnOpenBracket.setOnAction(_ -> {
            tfResult.setText(tfResult.getText() + "(");
            bracketCnt++;
        });
        this.btnCloseBracket.setOnAction(_ -> {
            if (bracketCnt > 0){
                tfResult.setText(tfResult.getText() + ")");
                bracketCnt--;
            }
        });
        this.btnBackspace.setOnAction(_ -> {
            char[] s = this.getTfResult().getText().toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length-1; i++){
                sb.append(s[i]);
            }
            this.getTfResult().setText(sb.toString());
        });

        // racunska operacija
        this.opMultiplication.setOnAction(new MultiplicationAction(this));
        this.opDivision.setOnAction(new DivisionAction(this));
        this.opPlus.setOnAction(new AdditionAction(this));
        this.opMinus.setOnAction(new SubstractionAction(this));

        // rezultat
        this.opEquals.setOnAction(new EqualsAction(this));
    }

    private void show() {
        this.add(this.tfResult, 0,0, 4, 1);
        this.add(this.btnBackspace, 0, 1, 4, 1);
        this.addRow(2, this.btnClear, this.btnOpenBracket, this.btnCloseBracket, this.opDivision);
        this.addRow(3, this.numSeven, this.numEight, this.numNine, this.opMultiplication);
        this.addRow(4, this.numFour, this.numFive, this.numSix, this.opMinus);
        this.addRow(5, this.numOne, this.numTwo, this.numThree, this.opPlus);
        this.add(this.numZero, 0,6,2,1);
        this.add(this.btnComma, 2,6);
        this.add(this.opEquals, 3, 6);
        this.setVgap(4);
        this.setHgap(4);
        this.setPadding(new Insets(10,10,10,10));
    }

    private void init() {
        this.numOne = new Button("1");
        this.numOne.setPrefSize(40,40);
        this.numTwo = new Button("2");
        this.numTwo.setPrefSize(40,40);
        this.numThree = new Button("3");
        this.numThree.setPrefSize(40,40);
        this.numFour = new Button("4");
        this.numFour.setPrefSize(40,40);
        this.numFive = new Button("5");
        this.numFive.setPrefSize(40,40);
        this.numSix = new Button("6");
        this.numSix.setPrefSize(40,40);
        this.numSeven = new Button("7");
        this.numSeven.setPrefSize(40,40);
        this.numEight = new Button("8");
        this.numEight.setPrefSize(40,40);
        this.numNine = new Button("9");
        this.numNine.setPrefSize(40,40);
        this.numZero = new Button("0");
        this.numZero.setPrefSize(84,40);
        this.opEquals = new Button("=");
        this.opEquals.setPrefSize(40,40);
        this.opMinus = new Button("-");
        this.opMinus.setPrefSize(40,40);
        this.opMultiplication = new Button("*");
        this.opMultiplication.setPrefSize(40,40);
        this.opDivision = new Button("/");
        this.opDivision.setPrefSize(40,40);
        this.opPlus = new Button("+");
        this.opPlus.setPrefSize(40,40);
        this.btnClear = new Button("C");
        this.btnClear.setPrefSize(40,40);
        this.btnBackspace = new Button("⌫");
        this.btnBackspace.setPrefSize(172,40);
        this.btnComma = new Button(".");
        this.btnComma.setPrefSize(40,40);
        this.btnOpenBracket = new Button("(");
        this.btnOpenBracket.setPrefSize(40, 40);
        this.btnCloseBracket = new Button(")");
        this.btnCloseBracket.setPrefSize(40, 40);
        this.tfResult = new TextField();
        this.tfResult.setEditable(false);
    }

    public Button getNumThree() {
        return numThree;
    }

    public void setNumThree(Button numThree) {
        this.numThree = numThree;
    }

    public Button getNumOne() {
        return numOne;
    }

    public void setNumOne(Button numOne) {
        this.numOne = numOne;
    }

    public Button getNumTwo() {
        return numTwo;
    }

    public void setNumTwo(Button numTwo) {
        this.numTwo = numTwo;
    }

    public Button getNumFour() {
        return numFour;
    }

    public void setNumFour(Button numFour) {
        this.numFour = numFour;
    }

    public Button getNumFive() {
        return numFive;
    }

    public void setNumFive(Button numFive) {
        this.numFive = numFive;
    }

    public Button getNumSix() {
        return numSix;
    }

    public void setNumSix(Button numSix) {
        this.numSix = numSix;
    }

    public Button getNumSeven() {
        return numSeven;
    }

    public void setNumSeven(Button numSeven) {
        this.numSeven = numSeven;
    }

    public Button getNumEight() {
        return numEight;
    }

    public void setNumEight(Button numEight) {
        this.numEight = numEight;
    }

    public Button getNumNine() {
        return numNine;
    }

    public void setNumNine(Button numNine) {
        this.numNine = numNine;
    }

    public Button getNumZero() {
        return numZero;
    }

    public void setNumZero(Button numZero) {
        this.numZero = numZero;
    }

    public Button getOpEquals() {
        return opEquals;
    }

    public void setOpEquals(Button opEquals) {
        this.opEquals = opEquals;
    }

    public Button getOpPlus() {
        return opPlus;
    }

    public void setOpPlus(Button opPlus) {
        this.opPlus = opPlus;
    }

    public Button getOpMinus() {
        return opMinus;
    }

    public void setOpMinus(Button opMinus) {
        this.opMinus = opMinus;
    }

    public Button getOpMultiplication() {
        return opMultiplication;
    }

    public void setOpMultiplication(Button opMultiplication) {
        this.opMultiplication = opMultiplication;
    }

    public Button getOpDivision() {
        return opDivision;
    }

    public void setOpDivision(Button opDivision) {
        this.opDivision = opDivision;
    }

    public Button getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(Button btnClear) {
        this.btnClear = btnClear;
    }

    public Button getBtnComma() {
        return btnComma;
    }

    public void setBtnComma(Button btnComma) {
        this.btnComma = btnComma;
    }

    public TextField getTfResult() {
        return tfResult;
    }

    public void setTfResult(TextField tfResult) {
        this.tfResult = tfResult;
    }
}
