/*
 *== File Name: LogoutController.java
 *== Created at: Oct 18, 2017 11:03:09 PM
 *== Project: assignment-backend
 *== Package: io.sonph.assignment.controllers
 */
package io.sonph.assignment.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.sonph.assignment.common.CommonConst;


/**
 * Assignment Demo -> LogoutController
 *
 * @author sonph
 */
public class LogoutController extends HttpServlet {

	/** Serial Version UID */
	private static final long serialVersionUID = 1660684615330553268L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutController() {
		super();
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

		// Invalid session
		HttpSession session = request.getSession();
		session.invalidate();

		// Return to login page
		response.sendRedirect(request.getContextPath() + CommonConst.AUTHETICATE_RETURN_PATH);
		return;
	}
}
