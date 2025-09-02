package edu.westga.cs1302.lab2.tests.view.bill_view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;

class TestGetTest {

	@Test
	public void testEmptyBill() {
		String result = "ITEMS" + System.lineSeparator()
		                + System.lineSeparator()
		                + "SUBTOTAL $0.0" + System.lineSeparator()
		                + "TAX $0.0" + System.lineSeparator()
		                + "TIP $0.0" + System.lineSeparator()
		                + "TOTAL $0.0";
		assertEquals(true, result.startsWith("ITEMS"));
	}

	@Test
	public void testBillWithOneItem() {
		Bill bill = new Bill();
		bill.addItem(new BillItem("Burger", 10.00));
	}
  
		@Test
		public void testBillWithMultipleItems() {
			Bill bill = new Bill();
			bill.addItem(new BillItem("Pizza", 15.0));
			bill.addItem(new BillItem("Soda", 2.5));
	
}
}
		
		
	

