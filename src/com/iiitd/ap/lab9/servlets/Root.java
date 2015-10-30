package com.iiitd.ap.lab9.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiitd.ap.lab9.Database;
import com.iiitd.ap.lab9.model.*;

/**
 * Servlet implementation class Root
 */
@WebServlet("/root")
public class Root extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Root() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Order> orders = Database.getIncompleteOrders();
		response.getWriter().println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Pizzeria</title><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootswatch/3.3.5/united/bootstrap.min.css'>	</head>	<body style='background-image:url(\"http://www.zastavki.com/pictures/1600x1200/2009/Food_Pizza_Filling_Pizza_011932_.jpg\");background-size: 100% 100%;background-repeat:no-repeat;background-position:right bottom;'><div class='container'>	<div class='row'><div class='col-lg-12'><h1>Pizzeria Management System</h1></div></div><div class='row'><div class='col-lg-12' style='height:768px;'><h2>All incomplete orders</h2>");
		response.getWriter().println("<table class='table'><thead><tr><th>Order ID</th><th>Pizzas</th><th>State</th><th>Update</th></tr></thead>");
		for(Order order: orders)
		{
			response.getWriter().println("<tr class='info'>");
			response.getWriter().println("<td>"+order.getId()+"</td>");
			response.getWriter().println("<td>"+order+"</td>");
			response.getWriter().println("<td>"+Database.stateVerbose(order.getStatus())+"</td>");
			response.getWriter().println("<td>");
			response.getWriter().println("<form action='./root' method='post'>");
			response.getWriter().println("<input type='hidden' name='orderId' value="+ order.getId()+ ">");
			response.getWriter().println("<button type=\"submit\" class=\"btn btn-sm\">UPDATE</button>");
			response.getWriter().println("</form>");
			response.getWriter().println("</td>");
			response.getWriter().println("</tr>");
		}
		response.getWriter().println("</table>");
		response.getWriter().println("<a href='./' style='color:blue;'>Return to home page</a> 	</div></div></div> <script src='http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js' type='text/javascript'></script>		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'>");
		response.getWriter().println("</body></html>");

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Database.updateOrderState(request.getParameter("orderId"));
		doGet(request, response);
	}

}
