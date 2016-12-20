package by.htp.library.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	private final static Validation INSTANCE = new Validation();
	private final static String NAME_REG_EX = "\\w{2,20}";
	private final static String LOGIN_REG_EX = "\\w{4,20}";
	private final static String PASSWORD_REG_EX = "\\w{4,20}";
	private final static String EMAIL_REG_EX = ".+@.+";

	private Validation() {

	}

	public static Validation getInstance() {
		return INSTANCE;
	}
	public boolean validateName(String name) {
		Pattern pattern = Pattern.compile(NAME_REG_EX);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}

	public boolean validateLogin(String login) {
		Pattern pattern = Pattern.compile(LOGIN_REG_EX);
		Matcher matcher = pattern.matcher(login);
		return matcher.matches();
	}

	public boolean validatePassword(String pass) {
		Pattern pattern = Pattern.compile(PASSWORD_REG_EX);
		Matcher matcher = pattern.matcher(pass);
		return matcher.matches();
	}

	public boolean validatePassEqals(String pass, String repeatPass) {
		return pass.equals(repeatPass);
	}

	public boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REG_EX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
