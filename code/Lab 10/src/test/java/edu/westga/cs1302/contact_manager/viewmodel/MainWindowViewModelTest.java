package edu.westga.cs1302.contact_manager.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import edu.westga.cs1302.contact_manager.model.Contact;

class MainWindowViewModelTest {

	private MainWindowViewModel viewModel;

	@BeforeEach
	void setUp() {
		viewModel = new MainWindowViewModel();
	}

	@Test
	void testAddContactSuccessfully() {
		viewModel.getName().set("Alice");
		viewModel.getPhoneNumber().set("1234567");
		viewModel.addContact();

		assertEquals(1, viewModel.getContacts().size());
		Contact contact = viewModel.getContacts().get(0);
		assertEquals("Alice", contact.getName());
		assertEquals("1234567", contact.getPhoneNumber());
	}

	@Test
	void testFindContactByName() {
		viewModel.getName().set("Bob");
		viewModel.getPhoneNumber().set("5551234");
		viewModel.addContact();

		viewModel.getSearchCriteria().set("Bob");
		String result = viewModel.findContact();
		assertTrue(result.contains("Bob"));
		assertTrue(result.contains("5551234"));
	}

	@Test
	void testFindContactByPhoneNumber() {
		viewModel.getName().set("Charlie");
		viewModel.getPhoneNumber().set("9876543");
		viewModel.addContact();

		viewModel.getSearchCriteria().set("9876543");
		String result = viewModel.findContact();
		assertTrue(result.contains("Charlie"));
		assertTrue(result.contains("9876543"));
	}

	@Test
	void testFindContactWithInvalidCriteria() {
		viewModel.getSearchCriteria().set("!@#$%");
		assertThrows(IllegalArgumentException.class, () -> viewModel.findContact());
	}

	@Test
	void testFindContactNotFound() {
		viewModel.getSearchCriteria().set("NonExistent");
		assertEquals("No contact found.", viewModel.findContact());
	}
}
