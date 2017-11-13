/**
 * 
 */
package io.sonph.assignment.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;

import io.sonph.assignment.model.CartDetail;
import io.sonph.assignment.util.HibernateUtil;


/**
 * @author Hoai Son
 *
 */
public class CartDetailDao {
	private Session session;

    /**
     * Get list of CartDetails
     *s
     * @return List User
     */
    public List<CartDetail> getListCartDetails() {

        //Declare result variable
        List<CartDetail> listCartDetails;

        // Handler error with try - catch - finally
        try {

            // Open Hibernate session
            this.openSession();

            // Query data
            Query<CartDetail> query = this.session.createQuery("from CartDetail", CartDetail.class);
            listCartDetails = query.getResultList();

        } finally {
            // Close Hibernate session
            this.closeSession();
        }

        // Return result
        return listCartDetails;
    }

    /**
     * Get list of CartDetails by cartId
     *
     * @param id;
     * @return listCartDetails
     */
    public List<CartDetail> getListCartDetails(Integer id) {
        // Declare result variable
        List<CartDetail> listCartDetails = null;

        // Handler error with try - catch - finally
        try {

            // Open Hibernate session
            this.openSession();

            // Find cart
            Query<CartDetail> query = this.session.createQuery("from CartDetail where cartId = :id", CartDetail.class);

            query.setParameter("id", id);

            listCartDetails = query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Close Hibernate session
            this.closeSession();
        }

        // Return result
        return listCartDetails;
    }

    public List<CartDetail> getItemCart(Integer id) {
        // Declare result variable
        List<CartDetail> listCartDetails = null;

        // Handler error with try - catch - finally
        try {

            // Open Hibernate session
            this.openSession();

            // Find cart
            TypedQuery<CartDetail> query = this.session.createQuery("SELECT NEW io.sonph.assignment.model.CartDetail(c.cartId,c.productId,c.detailName,c.detailPrice,SUM(c.detailQuantity)) " +
                    "FROM CartDetail c" +
                    " WHERE c.cartId = :id GROUP BY c.cartId, c.productId", CartDetail.class);

            query.setParameter("id", id);

            listCartDetails = query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Close Hibernate session
            this.closeSession();
        }

        // Return result
        return listCartDetails;
    }

    /**
     * Create cart detail
     *
     * @param cartdetail
     */
    public void createCartDetail(CartDetail cartdetail) {


        // Handler error with try - catch - finally
        try {

            // Open Hibernate session
            this.openSession();

            // Save product
            this.session.beginTransaction();
            this.session.save(cartdetail);
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

    public void updateCartDetail(CartDetail cartdetail) {
        // Handler error with try - catch - finally
        try {

            // Open Hibernate session
            this.openSession();

            // Save product
            this.session.beginTransaction();
            this.session.saveOrUpdate(cartdetail);
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
     * Delete cart detail by Product ID
     *
     * @param id
     */
    public void deleteCartDetail(Integer id) {

        CartDetail cartdetail;
        try {

            // Open Hibernate session
            this.openSession();

            //Find exist Category
            Query<CartDetail> query = this.session.createQuery("FROM CartDetail where productId = :id",CartDetail.class);
            query.setParameter("id", id);
            cartdetail = query.uniqueResult();

            // Delete product
            this.session.beginTransaction();
            this.session.delete(cartdetail);
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
