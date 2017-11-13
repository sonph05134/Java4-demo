package io.sonph.assignment.controllers;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.StringUtils;

import io.sonph.assignment.dao.CartDao;
import io.sonph.assignment.dao.CartDetailDao;
import io.sonph.assignment.dao.ProductDao;
import io.sonph.assignment.model.Account;
import io.sonph.assignment.model.Cart;
import io.sonph.assignment.model.CartDetail;
import io.sonph.assignment.model.Product;

@WebServlet(name = "AddCartController")
public class AddCartController extends HttpServlet {
    private CartDao cartDao;
    private CartDetailDao cartDetailDao;
    private ProductDao productDao;

    public AddCartController() {

    }

    @Override
    public void init() throws ServletException {
        this.cartDao = new CartDao();
        this.cartDetailDao = new CartDetailDao();
        this.productDao = new ProductDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set encoding
        request.setCharacterEncoding("UTF-8");

        // Get logged in account from session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Check logged in account
        if (account == null) {
            // Return to default path
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        // Check logged in account
        if (account != null) {
            Cart cart = new Cart();
            if (this.cartDao.getCartByUser(account.getId()) != null) {
                //get cart if exist
                cart = this.cartDao.getCartByUser(account.getId());

                session.setAttribute("cart", cart);
            } else if (this.cartDao.getCartByUser(account.getId()) == null) {
                cart.setUserId(account.getId());

                this.cartDao.createCart(cart);

                session.setAttribute("cart", cart);
            }
        }

        // Get parameters
        String id = request.getParameter("id");

        // Validate parameter
        if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {

            // Set exception
            request.setAttribute("exception", new JspException("Invalid input parameter: ID"));

            // Redirect to error page
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/common/error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Find product
        Product product = this.productDao.getProduct(Integer.parseInt(id));

        // Set product into request
        request.setAttribute("product", product);


        // Get user cart
        Cart cart = (Cart) session.getAttribute("cart");


        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
//        String productId = request.getParameter("id");
        Integer cartId = cart.getCartId();

        //Test add-cart function
        System.out.println("################################################");
        System.out.println(name + "/ " + price + "/ " + quantity + "/ " + id + "/ " + cartId + "/" + account.getId());

        CartDetail cartdetail = new CartDetail();

        cartdetail.setDetailName(name);
        cartdetail.setDetailPrice(Integer.valueOf(Integer.parseInt(price)));
        cartdetail.setDetailQuantity(Integer.parseInt(quantity));
        cartdetail.setProductId(Integer.parseInt(id));
        cartdetail.setCartId(cartId);

        this.cartDetailDao.createCartDetail(cartdetail);

        // Redirect to home
        response.sendRedirect(request.getContextPath() + "/list-products");
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

