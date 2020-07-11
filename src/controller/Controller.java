package controller;

import java.util.Random;

import alerts.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

	Message msg = new Message();
	String errorMessage = "";
	boolean validated = false;

	@FXML
	private TextField words;

	@FXML
	private TextField numbers;

	@FXML
	private TextField symbols;

	@FXML
	private Button generateBtn;

	@FXML
	private Label passwordLbl;

	@FXML
	private Button resetBtn;

	@FXML
	void generate(ActionEvent event) {
    	validateWord();
    	validateNumber();
		validateSymbol();

		if (!validated) {
			msg.setMessage(errorMessage);
		} else {
			generatePassword();
		}

	}

	@FXML
	void reset(ActionEvent event) {
		this.words.textProperty().setValue("");
		this.numbers.textProperty().setValue("");
		this.symbols.textProperty().setValue("");
	}

	public void validateWord() {
		String word = this.words.textProperty().getValue();

		if (word.matches("[a-zA-Z]*") && !word.isEmpty()) {
			System.out.println("Validated");
			validated = true;
		} else {
			System.out.println("Not Validated");
//    		msg.setMessage("Symbols,Numbers \n or Spaces in the word.");
			errorMessage = errorMessage + "Symbols,Numbers \n or Spaces in the word.";
			validated = false;
		}
	}

	public void validateNumber() {
		String number = this.numbers.textProperty().getValue();
		if (number.matches("[0-9]*")) {
			System.out.println("Validated");
			validated = true;
		} else {
			System.out.println("Not Validated");
//    		msg.setMessage("\nLetters and Symbols in number.");
			errorMessage = errorMessage + "\nLetters and Symbols in number.";
			validated = false;
		}
	}

	public void validateSymbol() {
		String symbol = this.symbols.textProperty().getValue();
		if (symbol.matches("[@#$%^&+=!]*")) {
			System.out.println("Validated Symbol");
			validated = true;
		} else {
			System.out.println("Not Validated");
			// msg.setMessage("\nSpecial symbols in symbol");
			errorMessage = errorMessage + "\nSpecial symbols in symbol";
			validated = false;
		}
	}

//	public void checkValidation() {
//		if (!validated) {
//			msg.setMessage(errorMessage);
//		}
//	}

	public void generatePassword() {
		String word = this.words.textProperty().getValue();
		String number = this.numbers.textProperty().getValue();
		String symbol = this.symbols.textProperty().getValue();
		
		char[] wordsChar = word.toCharArray();
		char[] rawPassword = new char[wordsChar.length];
		
		char[] numbersChar = number.toCharArray();
		char[] symbolsChar = symbol.toCharArray();

		for (int i = 0; i < wordsChar.length; i++) {
			if (i % 2 == 0) {
				char upper = Character.toUpperCase(wordsChar[i]);
				rawPassword[i] = upper;
			}else {
				char lower = Character.toLowerCase(wordsChar[i]);
				rawPassword[i] = lower;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(rawPassword);
		sb.append(numbersChar);
		sb.append(symbolsChar);
		
		String password = sb.toString();
		System.out.println(password);
		
		char[] passwordChar = password.toCharArray();
		
		Random rand = new Random();
		char[] mixedPassword = new char[passwordChar.length];
		
		for (int i = 0; i < passwordChar.length; i++) {
			int random = rand.nextInt(passwordChar.length);
			System.out.println("RANDOM : " + random);
			
			if(mixedPassword[random]=='\0') {
				System.out.println(random);
				mixedPassword[random] = passwordChar[random];
			}else {
				System.out.println("ELSE");
			}
			System.out.println("mixed : " + String.valueOf(mixedPassword));
		}
		
		passwordLbl.setText(String.valueOf(mixedPassword));
		
	}

}
