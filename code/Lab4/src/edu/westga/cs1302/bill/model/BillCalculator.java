package edu.westga.cs1302.bill.model;

/**
 * Class for performing calculations on an array of BillItem objects.
 * <p>
 * Provides static methods to calculate the subtotal, tax, tip and total for a bill.
 *</p>
 *
 * @author motoo1
 * @version 1.0
 */
public class BillCalculator {
	/**
	 * Calculates the subtotal for all BillItem amounts.
	 * 
	 * @param items an array of BillItem objects(may contain null values)
	 * @return the subtotal of all non-null BillItem amounts
	 */
	public static double calculateSubtotal(BillItem[] items) {
		double subtotal = 0;
		for (BillItem item : items) {
			if (item != null) {
				subtotal += item.getAmount();
				
			}
		}
		return subtotal;
	}
	
	  /** 
	 * Calculates tax for the given BillItem array
	 * 
	 * @param items an array of BillItem objects
	 * @param taxRate the tax rate as a decimal 
	 * @return the tax amount based on the subtotal
	 */
	
    public static double calculateTax(BillItem[] items, double taxRate) {
    	return calculateSubtotal(items) * taxRate;
    }
    
    /** 
   	 * Calculates tip for the given BillItem array
   	 * 
   	 * @param items an array of BillItem objects
   	 * @param tipRate the tip rate as a decimal 
   	 * @return the tip amount based on the subtotal
   	 */
    public static double calculateTip(BillItem[] items, double tipRate) {
    	return calculateSubtotal(items) * tipRate;
    }
    
    /** 
   	 * Calculates tax for the given BillItem array
   	 * 
   	 * @param items an array of BillItem objects
   	 * @param taxRate the tax rate as a decimal
   	 * @param tipRate the tip rate as a decimal 
   	 * @return the total amount
   	 */
   public static double calculateTotal(BillItem[] items, double taxRate, double tipRate) {
	   return calculateSubtotal(items) + calculateTax(items, taxRate) + calculateTip(items, tipRate);
	   
   }
}
