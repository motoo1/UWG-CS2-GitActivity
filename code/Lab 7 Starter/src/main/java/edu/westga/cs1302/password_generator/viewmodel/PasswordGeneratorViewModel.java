package edu.westga.cs1302.password_generator.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableObjectValue;
import edu.westga.cs1302.password_generator.model.PasswordGenerator;

/**
 * ViewModel class for the Password Generator application
 * <p>
 * This class connects the user interface to the PasswordGenerator logic using
 * JavaFX property bindings It provides property objects for the password and
 * length fields and methods to generate passwords
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

	/** The property representing the desired password length. */
	private StringProperty lengthProperty;

	/**
	 * Creates a new PasswordGeneratorViewModel and initializes the properties A
	 * default seed value is used to create the PasswordGenerator instance
	 */
	public PasswordGeneratorViewModel() {
		this.generator = new PasswordGenerator(12345L);
		this.passwordProperty = new SimpleStringProperty("");
		this.lengthProperty = new SimpleStringProperty("8");
	}

	/**
	 * Gets the password property for data binding with the view
	 * 
	 * @return the password property
	 */
	public StringProperty lengthProperty() {
		return this.lengthProperty;
	}
	
	/**
	 * Gets the password property for data  binding with the view
	 * <p>
	 * This property holds the most recently generated password so it can be automatically displayed or updated in user interface
	 * </p>
	 * 
	 * @return the password property
	 */
	public StringProperty passwordProperty() {
	    return this.passwordProperty;
	}

	/**
	 * Generates a new password using the PasswordGenerator model The generated
	 * password is stored in the password property
	 */
	public void generatePassword() {
		String password = this.generator.generatePassword();
		this.passwordProperty.set(password);
	}
}
