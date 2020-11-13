package dev.matt.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.commons.lang3.StringUtils;
import java.util.*;


public class CalculatorController {

	private boolean firstAccess = true;
	private Deque<Character> expStack = new ArrayDeque<>();

//	String collect = lista.stream().collect(Collectors.joining());

	@FXML
	Label result;
	@FXML
	Label expression;

	@FXML
	private void equalSign(final ActionEvent event) {
		//TODO
		//SOLVE CALLEE OPERATES
		if (!(expression.getText().equals(""))) {
			expression.setText("");
			result.setText("");
		}
	}
	@FXML
	private void add(final ActionEvent event) {
		expHandlerForOps("+");
	}
	@FXML
	private void min(final ActionEvent event) {
		expHandlerForOps("-");
	}
	@FXML
	private void mul(final ActionEvent event) {
		expHandlerForOps("x");
	}
	@FXML
	private void div(final ActionEvent event) {
		expHandlerForOps("÷");
	}
	@FXML
	private void percent(final ActionEvent event) {
		expHandlerForOps("%");
	}

	@FXML
	private void dot(final ActionEvent event) {
		Character value = '.';
		String tempString = expression.getText();
		if (firstAccess || (expression.getText().equals(""))) {
			expression.setText("0.");
			result.setText("");
			this.firstAccess = false;
			this.expStack.addLast('0');
			this.expStack.addLast(value);
		} else {
			Character check = this.expStack.getLast();
			if (Character.isDigit(check)){
				expression.setText(tempString.concat(value.toString()));
				this.expStack.addLast(value);
			}else{
				if (!check.toString().equals(value.toString())){
					expression.setText(tempString.concat("0."));
					this.expStack.addLast('0');
					this.expStack.addLast(value);
				}

			}
		}
	}

	@FXML
	private void zero(final ActionEvent event) {
		expHandlerForNum("0");
	}

	@FXML
	private void one(final ActionEvent event) {
		expHandlerForNum("1");
	}

	@FXML
	private void two(final ActionEvent event) {
		expHandlerForNum("2");
	}

	@FXML
	private void thr(final ActionEvent event) {
		expHandlerForNum("3");
	}

	@FXML
	private void fou(final ActionEvent event) {
		expHandlerForNum("4");
	}

	@FXML
	private void fiv(final ActionEvent event) {
		expHandlerForNum("5");
	}

	@FXML
	private void six(final ActionEvent event) {
		expHandlerForNum("6");
	}

	@FXML
	private void sev(final ActionEvent event) {
		expHandlerForNum("7");
	}

	@FXML
	private void eig(final ActionEvent event) {
		expHandlerForNum("8");
	}

	@FXML
	private void nin(final ActionEvent event) {
		expHandlerForNum("9");
	}

	@FXML
	private void reset(final ActionEvent event){
			expression.setText("Calculator by mattdalla v0.0.1");

			result.setText("");
			this.firstAccess = true;
			this.expStack.clear();
	}
	@FXML
	private void del(final ActionEvent event) throws NoSuchElementException {
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

	private void expHandlerForNum(String value) {
		//se expression è vuota o una stringa non vuota ("Calculator by..")
		if (firstAccess || (expression.getText().isEmpty())) {
			expression.setText(value);
			result.setText("");
			this.firstAccess = false;
			this.expStack.add(value.charAt(0));
		} else {
			String temp = expression.getText();
			expression.setText(temp.concat(value));
			this.expStack.add(value.charAt(0));
		}
	}

	private void expHandlerForOps(String value) {
		String temp = expression.getText();

		if (firstAccess || (expression.getText().isEmpty())) {
			if (value.equals("+") || value.equals("-")) {
				expression.setText(value);
				result.setText("");
				this.firstAccess = false;
			}
		}else{
			Character check = this.expStack.getLast();
			if (check.equals(value.charAt(0)) || !Character.isDigit(check)) {
				if (check.toString().equals(".")) {
					expression.setText(temp.concat("0".concat(value)));
					this.expStack.addLast('0');
					this.expStack.addLast(value.charAt(0));
				}
			}else {
				expression.setText(temp.concat(value));
				this.expStack.addLast(value.charAt(0));
			}
		}
	}
}
	

        
 