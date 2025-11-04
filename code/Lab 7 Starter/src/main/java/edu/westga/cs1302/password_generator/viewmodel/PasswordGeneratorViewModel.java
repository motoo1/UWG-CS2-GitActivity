package edu.westga.cs1302.password_generator.viewmodel;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel class for the Password Generator application.
 * <p>
 * This class connects the user interface (View) to the PasswordGenerator logic
 * (Model) using JavaFX property bindings. It provides property objects for the
 * password and minimum length fields, and methods to generate passwords based
 * on user preferences.
 * </p>
 * 
 * @version Fall 2025
 * @author motoo1
 */
public class PasswordGeneratorViewModel {

	/** The model used to generate passwords. */
	private PasswordGenerator generator;

	/** The property representing the generated password. */
	private StringProperty passwordProperty;

	/** The property representing the desired minimum password length. */
	private StringProperty lengthProperty;

	/**
	 * Creates a new {@code PasswordGeneratorViewModel} and initializes the
	 * properties. A default seed value is used to create the
	 * {@link PasswordGenerator} instance.
	 * 
	 * @precondition none
	 * @postcondition passwordProperty().get() == "" && lengthProperty().get() ==
	 *                "8"
	 */
	public PasswordGeneratorViewModel() {
		this.generator = new PasswordGenerator(12345L);
		this.passwordProperty = new SimpleStringProperty("");
		this.lengthProperty = new SimpleStringProperty("8");
	}

	/**
	 * Gets the property for the desired minimum password length.
	 * <p>
	 * This property allows two-way binding between the text field in the view and
	 * the internal length value used by the generator.
	 * </p>
	 * 
	 * @return the length property
	 */
	public StringProperty lengthProperty() {
		return this.lengthProperty;
	}

	/**
	 * Gets the property for the generated password.
	 * <p>
	 * This property allows one-way binding from the generator to the output text
	 * area so that the generated password automatically appears in the UI.
	 * </p>
	 * 
	 * @return the password property
	 */
	public StringProperty passwordProperty() {
		return this.passwordProperty;
	}

	/**
	 * Generates a new password using the {@link PasswordGenerator} model based on
	 * the user’s selected options and input length.
	 * <p>
	 * This method updates the generator’s configuration and stores the generated
	 * password in {@code passwordProperty}, making it automatically visible in the
	 * view.
	 * </p>
	 * 
	 * @param mustHaveDigits true if the password must include at least one digit
	 * @param mustHaveLower  true if the password must include at least one
	 *                       lowercase letter
	 * @param mustHaveUpper  true if the password must include at least one
	 *                       uppercase letter
	 * 
	 * @precondition lengthProperty().get() must represent an integer ≥ 1
	 * @postcondition passwordProperty().get() contains the newly generated password
	 */
	public void generatePassword(boolean mustHaveDigits, boolean mustHaveLower, boolean mustHaveUpper) {
		int length;

		try {
			length = Integer.parseInt(this.lengthProperty.get());
		} catch (NumberFormatException numberFormatException) {
			length = 1;
		}

		this.generator.setMinimumLength(length);
		this.generator.setMustHaveAtLeastOneDigit(mustHaveDigits);
		this.generator.setMustHaveAtLeastOneLowerCaseLetter(mustHaveLower);
		this.generator.setMustHaveAtLeastOneUpperCaseLetter(mustHaveUpper);

		String password = this.generator.generatePassword();
		this.passwordProperty.set(password);
	}
}