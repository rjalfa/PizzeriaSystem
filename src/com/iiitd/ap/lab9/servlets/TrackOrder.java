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
 * Servlet implementation class TrackOrder
 */
@WebServlet("/TrackOrder")
public class TrackOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrackOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response, Order order) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println(order);
		response.getWriter().println("ORDER STATUS: "+Database.stateVerbose(order.getStatus()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Find Order
		Order order = new Order();
		String orderId = request.getParameter("OrderID");
		System.out.printf("[MESSAGE] Order ID %d track status POST\n",orderId);
		order = Database.trackOrder(orderId);
		doGet(request, response,order);
	}
}
