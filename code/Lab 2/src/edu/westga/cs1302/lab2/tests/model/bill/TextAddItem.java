package edu.westga.cs1302.lab2.tests.model.bill;

import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;

/**
 * Unit tests for adding items to a Bill.
 * 
 * @author motoo1
 * @version 1.0
 */
public class TextAddItem {
	
	/**
	 * Tests that adds a null items and throws an IllegalArgumentEception. 
	 */
	@Test
	public void testAddNullItemThrowException() {
		assertThrows(IllegalArgumentException.class, () -> {
		Bill bill = new Bill();
			bill.addItem(null);
		});
	}
	/**
	 * Tests add a single item to the bill.	
	 */
	
	@Test
	public void testAddSingleItem() {
		Bill bill = new Bill();
		BillItem item = new BillItem("Burger", 10.0); 
		
		bill.addItem(item);
		assertEquals(1, bill.getItems().size());
		assertEquals("Burger", bill.getItems().get(0).getName());
		assertEquals(10.0, bill.getItems().get(0).getAmount());
	}
	/**
	 * Tests add multiple items to the bill.
	 */
	
	@Test
	public void testAddMultipleItems() {
		Bill bill = new Bill();
		bill.addItem(new BillItem("Pizza", 150.0));
		bill.addItem(new BillItem("Soda", 2.50));
		
		assertEquals("Pizza", bill.getItems().get(0).getName());
		assertEquals("Soda", bill.getItems().get(1).getName());
	}

	}
			
