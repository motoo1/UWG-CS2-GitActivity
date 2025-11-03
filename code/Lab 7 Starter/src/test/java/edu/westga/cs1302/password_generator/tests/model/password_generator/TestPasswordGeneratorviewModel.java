
package edu.westga.cs1302.password_generator.tests.model.password_generator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.PasswordGeneratorViewModel;

/**
 * Tests for PasswordGeneratorViewModel class.
 * 
 * @version Fall 2025
 * @author motoo1
 */
class TestPasswordGeneratorViewModel {

	private PasswordGeneratorViewModel viewModel;

	@BeforeEach
	void setUp() {
		this.viewModel = new PasswordGeneratorViewModel();
	}

	/**
	 * Tests that the default length property is initialized correctly.
	 */
	@Test
	void testDefaultLengthIsEight() {
		assertEquals("8", this.viewModel.lengthProperty().get(), "Default length should be '8'");
	}

	/**
	 * Tests that generatePassword sets a non-empty password.
	 */
	@Test
	void testGeneratePasswordSetsPasswordProperty() {
		// Make sure you added public StringProperty passwordProperty() in the ViewModel
		assertEquals("", this.viewModel.passwordProperty().get(), "Password should start empty");

		this.viewModel.generatePassword();

		String password = this.viewModel.passwordProperty().get();

		assertNotNull(password, "Password should not be null after generation");
		assertFalse(password.isEmpty(), "Password should not be empty after generation");
	}

	/**
	 * Tests that two generated passwords are different (shows randomness).
	 */
	@Test
	void testGeneratePasswordProducesDifferentPasswords() {
		this.viewModel.generatePassword();
		String firstPassword = this.viewModel.passwordProperty().get();

		this.viewModel.generatePassword();
		String secondPassword = this.viewModel.passwordProperty().get();

		assertNotEquals(firstPassword, secondPassword,
				"Two consecutive passwords should not be identical (unless fixed by seed)");
	}
}