package edu.westga.cs1302.contact_manager.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuplicateContactsTest {

	private MainWindowViewModel vm;

	@BeforeEach
	void setUp() {
		this.vm = new MainWindowViewModel();
	}

	@Test
	void testAddContactSuccessfully() {
		this.vm.getName().set("Alice");
		this.vm.getPhoneNumber().set("123-4567");

		this.vm.addContact();

		assertEquals(1, this.vm.getContacts().size());
		assertEquals("Alice", this.vm.getContacts().get(0).getName());
		assertEquals("123-4567", this.vm.getContacts().get(0).getPhoneNumber());
	}

	@Test
	void testAddDuplicateNameThrowsException() {
		this.vm.getName().set("Alice");
		this.vm.getPhoneNumber().set("123-4567");
		this.vm.addContact();

		this.vm.getName().set("Alice");
		this.vm.getPhoneNumber().set("765-4321");

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			this.vm.addContact();
		});

		assertEquals("A contact with this name already exists", ex.getMessage());
	}

	@Test
	void testAddDuplicatePhoneThrowsException() {
		this.vm.getName().set("Alice");
		this.vm.getPhoneNumber().set("1234567");

		this.vm.addContact();

		this.vm.getName().set("Bob");
		this.vm.getPhoneNumber().set("1234567");

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			this.vm.addContact();
		});

		assertEquals("A contact with this phone number already exists", ex.getMessage());
	}

	@Test
	void testAddDuplicateNameAndPhoneThrowsNameExceptionFirst() {

		this.vm.getName().set("Alice");
		this.vm.getPhoneNumber().set("1234567");
		this.vm.addContact();

		this.vm.getName().set("Alice");
		this.vm.getPhoneNumber().set("1234567");

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			this.vm.addContact();
		});

		assertEquals("A contact with this name already exists", ex.getMessage());
	}

	@Test
	void testAddInvalidNameThrowsContactException() {
		this.vm.getName().set("Alice123"); 
		this.vm.getPhoneNumber().set("1234567");

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			this.vm.addContact();
		});

		assertEquals("name is not valid", ex.getMessage());
	}

	@Test
	void testAddInvalidPhoneNumberThrowsContactException() {
		this.vm.getName().set("Alice");
		this.vm.getPhoneNumber().set("12-34567"); 

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			this.vm.addContact();
		});

		assertEquals("phone number is not valid", ex.getMessage());
	}
}