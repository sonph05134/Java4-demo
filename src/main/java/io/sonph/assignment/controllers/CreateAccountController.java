package io.sonph.assignment.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import io.sonph.assignment.common.CommonConst;
import io.sonph.assignment.dao.AccountDao;
import io.sonph.assignment.dao.CategoryDao;
import io.sonph.assignment.dao.ProductDao;
import io.sonph.assignment.model.Account;
import io.sonph.assignment.model.Category;
import io.sonph.assignment.model.Product;

/**
 * Servlet implementation class CreateAccountController
 */
public class CreateAccountController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/** Account Data Accessing Object */
	private AccountDao accountDao;   
	
	/** Category Data Accessing Object */
	private CategoryDao categoryDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {

		// Instance new DAO
		this.accountDao = new AccountDao();
		this.categoryDao = new CategoryDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set encoding
		request.setCharacterEncoding(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8);

		// Get data from database
		List<Category> listCategories = this.categoryDao.getListCategories();

		// Set data into request scope
		request.setAttribute("listCategories", listCategories);


		// Redirect to list accounts JSP
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/auth/createAccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set encoding
		request.setCharacterEncoding(CommonConst.REQEUST_CHARACTER_ENCODING_UTF8);

		// Invalid old session
		HttpSession session = request.getSession();
		session.removeAttribute(CommonConst.SESSION_ATTRIBUTE_ACCOUNT);

		// Get parameters
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = "customer";
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		// Validate parameters
		boolean hasInvalid = false;
	
		// Validate username
		if (StringUtils.isEmpty(username)||username.length() < 3) {
			request.setAttribute("errUsername", "Username is reuqired field and Username must  be greater than 3 characters.");
			hasInvalid = true;
		}

		// Validate password
		if (StringUtils.isBlank(password) || password.length() < 3) {
			request.setAttribute("errPassword", "Password is reuqired field and Password must be greater than 3 characters.");
			hasInvalid = true;
		} 

		// Validate fullName
		if (StringUtils.isEmpty(fullName)) {
			request.setAttribute("errFullName", "FullName must not be null.");
			hasInvalid = true;
		}
		if (StringUtils.isEmpty(email)) {
			request.setAttribute("errEmail", "Email must not be null.");
			hasInvalid = true;
		}
		if (StringUtils.isEmpty(address)) {
			request.setAttribute("errAddress", "Address must not be null.");
			hasInvalid = true;
		}
		// Check invalid flag
		if (hasInvalid) {

			// Set invalid flag
			request.setAttribute("hasInvalid", true);

			// Get data from database
			List<Account> listAccounts = this.accountDao.getListAccounts();

			// Set data into request scope
			request.setAttribute("listAccounts", listAccounts);

			// Forward to createAccount page with invalid message
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/auth/createAccount.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Create account model
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setType(type);
		account.setFullName(fullName);
		account.setEmail(email);
		account.setAddress(address);
		// Save account
		this.accountDao.createAccount(account);

		// Set session data
		session.setAttribute(CommonConst.SESSION_ATTRIBUTE_ACCOUNT, account);

		// Return to default path
		response.sendRedirect(request.getContextPath() + CommonConst.AUTHETICATE_RETURN_PATH);
		return;
	}
}
