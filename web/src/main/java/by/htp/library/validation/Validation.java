package by.htp.library.validation;

import by.htp.library.util.CreateErrorMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Validates all data with corresponding pattern, which was entered by users.
 *
 * @author Sergei Levkovskii
 *
 */
public class Validation {
	/**
	 * Instance of the singlton Validation.
	 */
	private final static Validation INSTANCE = new Validation();
	/**
	 * Regular expression to the correct user name
	 */
	private final static String NAME_REG_EX = "[a-zA-Zа-яёА-ЯЁ\\s]{2,20}";
	/**
	 * Regular expression to the correct user login
	 */
	private final static String LOGIN_REG_EX = "\\w{4,20}";
	/**
	 * Regular expression to the correct user password
	 */
	private final static String PASSWORD_REG_EX = "\\w{4,20}";
	/**
	 * Regular expression to the correct user email
	 */
	private final static String EMAIL_REG_EX = ".+@.+";
	/**
	 * Regular expression to the correct author name
	 */
	private final static String AUTHOR_ADD_EX = "[\\s,.\\wа-яА-Я]{2,50}";
	/**
	 * Regular expression to the correct book title
	 */
	private final static String TITLE_ADD_EX = "[\\s,.\\wа-яА-Я]{4,50}";
	/**
	 * Regular expression to the correct date of publication
	 */
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
	/**
	 * Validates name with the corresponding regular expression
	 *
	 * @param name
	 * @return
	 */
	public boolean validateName(String name) {
		Pattern pattern = Pattern.compile(NAME_REG_EX);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}
	/**
	 * Validates login with the corresponding regular expression
	 *
	 * @param login
	 * @return
	 */
	public boolean validateLogin(String login) {
		Pattern pattern = Pattern.compile(LOGIN_REG_EX);
		Matcher matcher = pattern.matcher(login);
		return matcher.matches();
	}
	/**
	 * Validates pass with the corresponding regular expression
	 *
	 * @param pass
	 * @return
	 */
	public boolean validatePassword(String pass) {
		Pattern pattern = Pattern.compile(PASSWORD_REG_EX);
		Matcher matcher = pattern.matcher(pass);
		return matcher.matches();
	}
	/**
	 * Validates password for equality to the repeated password
	 *
	 * @param pass
	 * @param repeatPass
	 * @return
	 */
	public boolean validatePassEqals(String pass, String repeatPass) {
		return pass.equals(repeatPass);
	}
	/**
	 * Validates email with the corresponding regular expression
	 *
	 * @param email
	 * @return
	 */
	public boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REG_EX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	/**
	 * Validates author with the corresponding regular expression
	 *
	 * @param author
	 * @return
	 */
	public boolean validateAuthor(String author) {
		Pattern pattern = Pattern.compile(AUTHOR_ADD_EX);
		Matcher matcher = pattern.matcher(author);
		return matcher.matches();
	}
	/**
	 * Validates title with the corresponding regular expression
	 *
	 * @param title
	 * @return
	 */
	public boolean validateTitle(String title) {
		Pattern pattern = Pattern.compile(TITLE_ADD_EX);
		Matcher matcher = pattern.matcher(title);
		return matcher.matches();
	}
	/**
	 * Validates date with the corresponding regular expression
	 *
	 * @param date
	 * @return
	 */
	public boolean validateDate(String date) {
		Pattern pattern = Pattern.compile(DATE_ADD_EX);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}

}
