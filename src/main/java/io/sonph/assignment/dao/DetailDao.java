/**
 * 
 */
package io.sonph.assignment.dao;

import io.sonph.assignment.model.Product;

/**
 * @author Hoai Son
 *
 */
public class DetailDao {
	private Product product;
    private int quantity;
	/**
	 * 
	 */
	public DetailDao() {
		super();
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
 
   
    
}
