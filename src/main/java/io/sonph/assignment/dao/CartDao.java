/**
 * 
 */

package io.sonph.assignment.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import io.sonph.assignment.model.Cart;
import io.sonph.assignment.util.HibernateUtil;

public class CartDao {

    /* Hibernate Session */
    private Session session;

    /**
     * Get cart by Cart ID
     * @param id
     * @return
     */
    public Cart getCart(Integer id) {
        // Declare result variable
        Cart cart = null;

        // Handler error with try - catch - finally
        try {

            // Open Hibernate session
            this.openSession();

            // Find cart
            cart = this.session.find(Cart.class, id);

        } finally {
            // Close Hibernate session
            this.closeSession();
        }

        // Return result
        return cart;
    }

    /**
     * Get Cart by User Id
     * @param id
     * @return
     */
    public Cart getCartByUser(Integer id) {
        // Declare result variable
        Cart cart = null;

        // Handler error with try - catch - finally
        try {

            // Open Hibernate session
            this.openSession();

            // Find cart
            Query<Cart> query = this.session.createQuery("from Cart where userId = :id", Cart.class);

            query.setParameter("id", id);
            cart = query.uniqueResult();

        } finally {
            // Close Hibernate session
            this.closeSession();
        }

        // Return result
        return cart;
    }
    

    public void createCart(Cart cart) {

        try{

            // Open Hibernate session
            this.openSession();

            // Update user
            this.session.beginTransaction();
            this.session.saveOrUpdate(cart);
            this.session.flush();
            this.session.getTransaction().commit();

        } catch (Exception ex) {
            this.session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            // Close Hibernate session
            this.closeSession();
        }
    }


    /**
     * Open Hibernate Session
     */
    private void openSession() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Close Hibernate Session
     */
    private void closeSession() {
        if (this.session != null && this.session.isOpen()) {
            this.session.close();
        }
    }
}
