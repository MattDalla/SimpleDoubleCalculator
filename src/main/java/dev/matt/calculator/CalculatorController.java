package dev.matt.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.commons.lang3.StringUtils;
import dev.matt.calculator.Brackets.*;
import java.util.*;

import static dev.matt.calculator.Number.*;
import static dev.matt.calculator.Operator.*;


public class CalculatorController {

    private boolean firstAccess = true;
    private Deque<Character> expStack = new ArrayDeque<>();
    private Brackets brackets = new Brackets();
//	String collect = lista.stream().collect(Collectors.joining());

    @FXML
    Label result;
    @FXML
    Label expression;

    @FXML
    private void equalSym(final ActionEvent event) {
        //TODO
        //SOLVE CALLEE OPERATES
        if (!(expression.getText().isEmpty())) {
            expression.setText("");
            result.setText("");
        }
    }

    @FXML
    private void add(final ActionEvent event) {
        plus_minusHandler(PLUS);
    }

    @FXML
    private void min(final ActionEvent event) {
        plus_minusHandler(MINUS);
    }

    @FXML
    private void mul(final ActionEvent event) {
        mult_div_percentHandler(MULT);
    }

    @FXML
    private void div(final ActionEvent event) {
        mult_div_percentHandler(DIV);
    }

    @FXML
    private void percentage(final ActionEvent event) { plus_minusHandler(PERCENT); }

    @FXML
    private void dot(final ActionEvent event) { dotHandler(); }

    @FXML
    private void zero(final ActionEvent event) {
        expHandlerForNum(ZERO);
    }

    @FXML
    private void one(final ActionEvent event) { expHandlerForNum(ONE); }

    @FXML
    private void two(final ActionEvent event) {
        expHandlerForNum(TWO);
    }

    @FXML
    private void three(final ActionEvent event) {
        expHandlerForNum(THREE);
    }

    @FXML
    private void four(final ActionEvent event) { expHandlerForNum(FOUR); }

    @FXML
    private void five(final ActionEvent event) {
        expHandlerForNum(FIVE);
    }

    @FXML
    private void six(final ActionEvent event) {
        expHandlerForNum(SIX);
    }

    @FXML
    private void seven(final ActionEvent event) {
        expHandlerForNum(SEVEN);
    }

    @FXML
    private void eight(final ActionEvent event) { expHandlerForNum(EIGHT); }

    @FXML
    private void nine(final ActionEvent event) {
        expHandlerForNum(NINE);
    }

    @FXML
    private void brackets(final ActionEvent event) {
        bracketsHandler();
    }

    @FXML
    private void reset(final ActionEvent event) {
        expression.setText("Calculator by mattdalla v0.0.1");

        result.setText("");
        this.firstAccess = true;
        this.expStack.clear();
        brackets.setDefault();
    }

    @FXML
    private void del(final ActionEvent event) {
        if (!(expression.getText().equals(""))) {
            expression.setText(StringUtils.chop(expression.getText()));
            result.setText("");
            this.expStack.pollLast();
        }
    }

    @FXML
    private void mabout(final ActionEvent event) {
        //TODO
    }

    private void expHandlerForNum(Number value) {
        //se expression è vuota o una stringa non vuota ("Calculator by..")
        if (isFirstOrEmpty()) {
            expression.setText(value.getText());
            result.setText("");
            this.firstAccess = false;
            this.expStack.add(value.getValue());
        } else {
            String temp = expression.getText();
            expression.setText(temp.concat(value.getText()));
            this.expStack.add(value.getValue());
        }
    }


//        Map<Operator, Function<String, String>> m = new HashMap<>();
//        m.put(plus, s -> s+1);
//        m.put(minus, s -> s+2);
//        m.put(mult, s -> s+3);
//        m.put(div, s -> s+4);
//        String ciao = m.get(value).apply("ciao");

    private void bracketsHandler() {
        String temp = expression.getText();
        if (isFirstOrEmpty()) {
            expression.setText(("("));
            result.setText("");
            this.expStack.addLast('(');
            this.firstAccess = false;
            brackets.increaseOpened();
        }
        else {
            Character lastChar = this.expStack.getLast();
            if (Character.isDigit(lastChar)) {
                if (brackets.getOpened() != 0){
                    expression.setText(temp.concat(")"));
                    this.expStack.addLast(')');
                    brackets.decreaseOpened();
                } else {
                    String piv = "x"+"(";
                    expression.setText(temp.concat(piv));
                    this.expStack.addLast('x');
                    this.expStack.addLast(('('));
                    brackets.increaseOpened();
                }
            }else if (lastChar.equals(')')) {
                if (brackets.getOpened() != 0){
                    expression.setText(temp.concat(")"));
                    brackets.decreaseOpened();
                    this.expStack.addLast(')');
                }else {
                    String piv = "x"+"(";
                    expression.setText(temp.concat(piv));
                    brackets.increaseOpened();
                    this.expStack.addLast(('x'));
                    this.expStack.addLast((')'));
                }
            }
            else {
                expression.setText(temp.concat("("));
                this.expStack.addLast('(');
                brackets.increaseOpened();
            }

        }
    }



    private void plus_minusHandler(Operator value) {
        String temp = expression.getText();

        if (isFirstOrEmpty()) {
            expression.setText(value.getText());
            result.setText("");
            this.firstAccess = false;
        } else {
            Character checkChar = this.expStack.getLast();
            if (checkChar.toString().equals(".")) {
                expression.setText(temp.concat("0".concat(value.getText())));
                this.expStack.addLast('0');
                this.expStack.addLast(value.getValue());
            } else if (checkChar.equals(MULT.getValue()) || checkChar.equals(DIV.getValue())) {
                String pivot = "(" + value.getText();
                expression.setText(temp.concat(pivot));
                this.expStack.addLast('(');
                this.expStack.addLast((value.getValue()));
            } else {
                expression.setText(temp.concat(value.getText()));
                this.expStack.addLast(value.getValue());
            }

        }
    }

    private void mult_div_percentHandler(Operator value){
        String temp = expression.getText();

        if (!firstAccess || (expression.getText().isEmpty())) {
            Character check = this.expStack.getLast();
            //se l'operatore inserito è uguale all'ultimo inserito,
            //o se l'ultimo elemento non è un numero, non fare nulla
            //vale solo per mult e div
            if (check.equals(value.getValue()) || !Character.isDigit(check)) {
                if (check.toString().equals(".")) {
                    expression.setText(temp.concat("0".concat(value.getText())));
                    this.expStack.addLast('0');
                    this.expStack.addLast(value.getValue());
                }
            } else {
                expression.setText(temp.concat(value.getText()));
                this.expStack.addLast(value.getValue());
            }

        }

    }

    private void dotHandler() {
        Character value = '.';
        String tempString = expression.getText();
        if (isFirstOrEmpty()) {
            expression.setText("0.");
            result.setText("");
            this.firstAccess = false;
            this.expStack.addLast('0');
            this.expStack.addLast(value);
        } else {
            Character check = this.expStack.getLast();
            if (Character.isDigit(check)) {
                expression.setText(tempString.concat(value.toString()));
                this.expStack.addLast(value);
            } else {
                if (!check.toString().equals(value.toString())) {
                    expression.setText(tempString.concat("0."));
                    this.expStack.addLast('0');
                    this.expStack.addLast(value);
                }

            }
        }

    }

        private boolean isFirstOrEmpty(){
            if (firstAccess || (expression.getText().isEmpty())) {
                return true;
            }else return false;
        }
}






        
 