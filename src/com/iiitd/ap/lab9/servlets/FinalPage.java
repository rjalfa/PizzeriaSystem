package com.iiitd.ap.lab9.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiitd.ap.lab9.Database;
import com.iiitd.ap.lab9.model.Order;

/**
 * Servlet implementation class FinalPage
 */
@WebServlet("/FinalPage")

public class FinalPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response, Order order) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Order order = (Order) request.getSession().getAttribute("order");
		String[] details = new String[5];
		details[0] = request.getParameter("name");
		details[1] = request.getParameter("phone");
		details[2] = request.getParameter("street");
		details[3] = request.getParameter("city");
		details[4] = request.getParameter("pin");
		order = Database.finaliseOrder(order,details);
		doGet(request, response,order);
	}

}
