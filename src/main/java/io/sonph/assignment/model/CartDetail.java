/**
 * 
 */
package io.sonph.assignment.model;



import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author Hoai Son
 *
 */
@Entity(name = "CartDetail")
@Table(name = "cartdetail")
public class CartDetail {
	 private Integer cartDetailId;
	    private Integer cartId;
	    private Integer productId;
	    private String detailName;
	    private Integer detailPrice;
	    private Integer detailQuantity;

	    private Cart cart;
	    private Product product;
		/**
		 * 
		 */
		public CartDetail() {
			super();
		}
		/**
		 * @param cartDetailId
		 * @param cartId
		 * @param productId
		 * @param detailName
		 * @param detailPrice
		 * @param detailQuantity
		 * @param cart
		 * @param product
		 */
		public CartDetail(Integer cartDetailId, Integer cartId, Integer productId, String detailName,
				Integer detailPrice, Integer detailQuantity, Cart cart, Product product) {
			super();
			this.cartDetailId = cartDetailId;
			this.cartId = cartId;
			this.productId = productId;
			this.detailName = detailName;
			this.detailPrice = detailPrice;
			this.detailQuantity = detailQuantity;
			this.cart = cart;
			this.product = product;
		}
	
		 @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    @Column(name = "cartDetailID", nullable = false)
		    public Integer getCartDetailId() {
		        return cartDetailId;
		    }

		    public void setCartDetailId(Integer cartDetailId) {
		        this.cartDetailId = cartDetailId;
		    }

		    @Basic
		    @Column(name = "cartID", nullable = false)
		    public Integer getCartId() {
		        return cartId;
		    }

		    public void setCartId(Integer userId) {
		        this.cartId = userId;
		    }

		    @Basic
		    @Column(name = "productID", nullable = false)
		    public Integer getProductId() {
		        return productId;
		    }

		    public void setProductId(Integer productId) {
		        this.productId = productId;
		    }

		    @Basic
		    @Column(name = "detailName", nullable = true, length = 255)
		    public String getDetailName() {
		        return detailName;
		    }

		    public void setDetailName(String detailName) {
		        this.detailName = detailName;
		    }

		    @Basic
		    @Column(name = "detailPrice", nullable = false)
		    public Integer getDetailPrice() {
		        return detailPrice;
		    }

		    public void setDetailPrice(Integer detailPrice) {
		        this.detailPrice = detailPrice;
		    }

		    @Basic
		    @Column(name = "detailQuantity", nullable = false)
		    public Integer getDetailQuantity() {
		        return detailQuantity;
		    }

		    public void setDetailQuantity(Integer detailQuantity) {
		        this.detailQuantity = detailQuantity;
		    }

		    @ManyToOne(fetch = FetchType.LAZY)
		    @JoinColumn(name = "cartID", insertable = false, updatable = false)
		    public Cart getCart() {
		        return cart;
		    }

		    public void setCart(Cart cart) {
		        this.cart = cart;
		    }

		    @ManyToOne(fetch = FetchType.LAZY)
		    @JoinColumn(name = "productID", insertable = false, updatable = false)
		    public Product getProduct() {
		        return product;
		    }

		    public void setProduct(Product product) {
		        this.product = product;
		    }
		    @Override
		    public boolean equals(Object o) {
		        if (this == o) return true;
		        if (o == null || getClass() != o.getClass()) return false;

		        CartDetail that = (CartDetail) o;

		        if (cartDetailId != null ? !cartDetailId.equals(that.cartDetailId) : that.cartDetailId != null) return false;
		        if (detailName != null ? !detailName.equals(that.detailName) : that.detailName != null) return false;
		        if (detailPrice != null ? !detailPrice.equals(that.detailPrice) : that.detailPrice != null) return false;
		        if (detailQuantity != null ? !detailQuantity.equals(that.detailQuantity) : that.detailQuantity != null)
		            return false;

		        return true;
		    }

		    @Override
		    public int hashCode() {
		        int result = cartDetailId != null ? cartDetailId.hashCode() : 0;
		        result = 31 * result + (detailName != null ? detailName.hashCode() : 0);
		        result = 31 * result + (detailPrice != null ? detailPrice.hashCode() : 0);
		        result = 31 * result + (detailQuantity != null ? detailQuantity.hashCode() : 0);
		        return result;
		    }

}
