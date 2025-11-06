
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
public class TestPasswordGeneratorviewModel {

	private PasswordGeneratorViewModel viewModel;

	@BeforeEach
	public void setUp() {
		this.viewModel = new PasswordGeneratorViewModel();
	}

	/**
	 * Test that a password is generated when valid inputs are provided.
	 */
	@Test
	public void testGeneratePasswordWithValidSettings() {
		this.viewModel.lengthProperty().set("10");
		this.viewModel.generatePassword(true, true, true);

		String password = this.viewModel.passwordProperty().get();

		assertNotNull(password, "Password should not be null");
		assertTrue(password.length() >= 1, "Password should not be empty");
	}

	/**
	 * Test that invalid length input defaults gracefully (e.g., to 1).
	 */
	@Test
	public void testGeneratePasswordWithInvalidLengthInput() {
		this.viewModel.lengthProperty().set("abc"); // invalid input
		this.viewModel.generatePassword(true, false, false);

		String password = this.viewModel.passwordProperty().get();

		assertNotNull(password);
		assertTrue(password.length() >= 1, "Should still generate a valid password");
	}

	/**
	 * Test that the password updates each time generatePassword() is called.
	 */
	@Test
	public void testPasswordChangesOnRegeneration() {
		this.viewModel.lengthProperty().set("8");
		this.viewModel.generatePassword(true, true, false);
		String firstPassword = this.viewModel.passwordProperty().get();

		this.viewModel.generatePassword(true, true, false);
		String secondPassword = this.viewModel.passwordProperty().get();

		assertNotEquals(firstPassword, secondPassword, "Passwords should be different across generations");
	}
}
