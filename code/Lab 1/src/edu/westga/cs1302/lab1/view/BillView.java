package edu.westga.cs1302.lab1.view;

import edu.westga.cs1302.lab1.model.Bill;
import edu.westga.cs1302.lab1.model.BillItem;

/**
 * The class bill view 
 * 
 * @author Maame Ama
 * @version Fall 2025
 */
public class BillView {
	private Bill bill;
	
	/**
	 * Create the construct for bill view
	 * @param bill The bill object to be displayed in this view.
	 * @precondition none
	 * @postcondition none
	 */
	public BillView(Bill bill) {
		this.bill = bill;
		
	}
	
	/** Return a String containing the list of bill items and total for the bill.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a String containing the list of bill items and total for the bill
	 */
	public String getText() {
		String text = "ITEMS" + System.lineSeparator();
		double subTotal = Bill.SUB_TOTAL;
		for (BillItem item : this.bill.getItems()) {
			text += item.getName() + " - " + item.getAmount() + System.lineSeparator();
			subTotal += item.getAmount();
		}
		
		text += System.lineSeparator();
		text += "SUBTOTAL - $" + subTotal + System.lineSeparator();
		double tax = subTotal * Bill.TAX;
		double tip = subTotal * Bill.TIP;
		text += "TAX - $" + tax + System.lineSeparator();
		text += "TIP - $" + tip + System.lineSeparator();
		text += "TOTAL - $" + (subTotal + tip + tax);
		
		return text;
	}
}
