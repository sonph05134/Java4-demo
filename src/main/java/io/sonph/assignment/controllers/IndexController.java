/*
 *== File Name: IndexController.java
 *== Created at: Oct 20, 2017 1:57:28 AM
 *== Project: assignment-frontend
 *== Package: io.sonph.assignment.controllers
 */
package io.sonph.assignment.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.sonph.assignment.common.CommonConst;
import io.sonph.assignment.dao.CategoryDao;
import io.sonph.assignment.dao.ProductDao;
import io.sonph.assignment.model.Category;
import io.sonph.assignment.model.Product;



/**
 * Assignment -> IndexController
 *
 * @author sonph
 */
public class IndexController extends HttpServlet {

	/** Serial Version UID */
	private static final long serialVersionUID = -5121863561715102349L;

	/** Category Data Accessing Object */
	private CategoryDao categoryDao;

	/** Product Data Accessing Object */
	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexController() {
		super();
	}

	/**
	 * @see GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {

		// Instance new DAO
		this.categoryDao		= new CategoryDao();
		this.productDao		= new ProductDao();
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
		List<Product> listProducts = this.productDao.getListProduct();

		// Set data into request scope
		request.setAttribute("listCategories", listCategories);
		request.setAttribute("listProducts", listProducts);

		// Redirect to index JSP
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/index.jsp");
		dispatcher.forward(request, response);
	}
}
