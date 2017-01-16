package by.htp.library.validation;

import by.htp.library.util.CreateErrorMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	private final static Validation INSTANCE = new Validation();
	private final static String NAME_REG_EX = "[a-zA-Zа-яёА-ЯЁ\\s]{2,20}";
	private final static String LOGIN_REG_EX = "\\w{4,20}";
	private final static String PASSWORD_REG_EX = "\\w{4,20}";
	private final static String EMAIL_REG_EX = ".+@.+";
	private final static String AUTHOR_ADD_EX = "[\\s,.\\wа-яА-Я]{2,50}";
	private final static String TITLE_ADD_EX = "[\\s,.\\wа-яА-Я]{4,50}";
	private final static String DATE_ADD_EX = "\\d{4}";
	private final static String INCORRECT_NAME = "locale.error.incorrect.name";
	private final static String INCORRECT_LOGIN = "locale.error.incorrect.login";
	private final static String INCORRECT_PASSWORD = "locale.error.incorrect.password";
	private final static String PASSWORDS_DONT_MATCH = "locale.error.pass.dont.match";
	private final static String INCORRECT_IMAIL = "locale.error.incorrect.email";

	private Validation() {

	}

	public static Validation getInstance() {
		return INSTANCE;
	}

	public boolean validateData(String login, String password) {
		Validation validation = Validation.getInstance();
		return validation.validateLogin(login) && validation.validatePassword(password);
	}

	public boolean validateData(String addAuthor, String addTitle, String addDate) {
		Validation validation = Validation.getInstance();
		return validation.validateAuthor(addAuthor) && validation.validateTitle(addTitle) && validation.validateDate(addDate);
	}

	public String validateData(String registrName, String registrLogin, String registrPass, String repeatedPass,
								String registrEmail, String userLocale) {
		Validation validation = Validation.getInstance();
		if (!validation.validateName(registrName)) {
			return CreateErrorMessage.createErrorMessage(INCORRECT_NAME, userLocale);
		}
		if (!validation.validateLogin(registrLogin)) {
			return CreateErrorMessage.createErrorMessage(INCORRECT_LOGIN, userLocale);
		}
		if (!validation.validatePassword(registrPass)) {
			return CreateErrorMessage.createErrorMessage(INCORRECT_PASSWORD, userLocale);
		}
		if (!validation.validatePassEqals(registrPass, repeatedPass)) {
			return CreateErrorMessage.createErrorMessage(PASSWORDS_DONT_MATCH, userLocale);
		}
		if (!validation.validateEmail(registrEmail)) {
			return CreateErrorMessage.createErrorMessage(INCORRECT_IMAIL, userLocale);
		} else {
			return null;
		}
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

	public boolean validateAuthor(String author) {
		Pattern pattern = Pattern.compile(AUTHOR_ADD_EX);
		Matcher matcher = pattern.matcher(author);
		return matcher.matches();
	}

	public boolean validateTitle(String title) {
		Pattern pattern = Pattern.compile(TITLE_ADD_EX);
		Matcher matcher = pattern.matcher(title);
		return matcher.matches();
	}

	public boolean validateDate(String date) {
		Pattern pattern = Pattern.compile(DATE_ADD_EX);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}

}
