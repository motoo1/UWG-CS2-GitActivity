package edu.westga.cs1302.bill.view;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillCalculator;

/** Supports displaying the information contained in a Bill.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class BillView {

	/** Return a String containing the list of bill, showing subtotal, tax, tip, and total
	 * 
	 * @param items the array of BillItems
	 * @param taxRate the tax rate
	 * @param tipRate the tip rate
	 * @return a String containing the list of bill items and total for the bill
	 */
	
	public  String getText(BillItem[] items, double taxRate, double tipRate) {
		double subtotal = BillCalculator.calculateSubtotal(items);
		double tax = BillCalculator.calculateTax(items, taxRate);
		double tip = BillCalculator.calculateTip(items, tipRate);
		double total = BillCalculator.calculateTotal(items, taxRate, tipRate);
		
		return "Subtotal: $" + subtotal + "\n" 
		+ "Tax: $" + tax + "\n" 
		+ "Tip: $" + tip + "\n" 
		+ "Total: $" + total;
	}
	
	/**
	 * Builds a formatted text string showing the bill details
	 * 
	 * @param bill the Bill object containing items, tax rate and tip rate.
	 * @return a formatted string representation of the bill
	 */
	
	public static String getText(Bill bill) {
		String text = "ITEMS" + System.lineSeparator();
		double subTotal = 0.0;
		for (BillItem item : bill.getItems()) {
			text += item.getName() + " - " + item.getAmount() + System.lineSeparator();
			subTotal += item.getAmount();
		}
		
		text += System.lineSeparator();
		text += "SUBTOTAL - $" + subTotal + System.lineSeparator();
		double tax = subTotal * Bill.TAX_RATE;
		double tip = subTotal * Bill.TIP_RATE;
		text += "TAX - $" + BillView.roundToNearestHundredth(tax) + System.lineSeparator();
		text += "TIP - $" + BillView.roundToNearestHundredth(tip) + System.lineSeparator();
		text += "TOTAL - $" + BillView.roundToNearestHundredth(subTotal + tip + tax);
		
		return text;
	}
	
	private static double roundToNearestHundredth(double value) {
		return (int) (value * 100) / 100.0;
	}
}
