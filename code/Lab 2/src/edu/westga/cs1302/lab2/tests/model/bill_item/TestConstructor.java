package edu.westga.cs1302.lab2.tests.model.bill_item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
//import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;
/**
 * Unit tests for the Bill constructor
 * 
 * This class verifies that the Bill object is created correctly
 * and handles initial values as expected
 * 
 * @author motoo1
 * @version 1.0
 */

public class TestConstructor {
/**
* Tests that the BillItem constructor correctly initializes a new BillItem object
* with non-null values for name and amount.
 */
@Test
public void  testConstructorNotNull() {
	BillItem item = new BillItem("Water", 1.50);
	assertNotNull(item);
	assertEquals("Water", item.getName());
	assertEquals(1.50, item.getAmount());
}
 /**
 * Tests that the BillItem constructor throws an IllegalArgumentEception
 * when a null name is provided
 */	

 @Test
 public void tesConstructorNameNullThrowsException() {
	 assertThrows(IllegalArgumentException.class, () -> {
		 new BillItem(null, 2.00);
		  
	 });
 }
	 /**
	  * Test the BillItem constructor with a low boundary value for the amount.
	  */
 
	 @Test
	 public void testConstructorLowBoundary() {
		 BillItem item = new BillItem("Sample Item", 10.0);
		 assertEquals("Sample Item", item.getName());
		 assertEquals(10.0, item.getAmount());
	 }
	 /**
	  * Test the BillItem constructor with a high boundary value for the amount.
	  */

	 @Test
	 public void testConstructorHighBoundary() {
		 BillItem item = new BillItem("Luxury Item", 1000.0);
		  assertEquals("Luxury Item", item.getName());
		 assertEquals(1000.0, item.getAmount()); 
	 }
 
}
