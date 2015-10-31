package com.iiitd.ap.lab9.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiitd.ap.lab9.Database;
import com.iiitd.ap.lab9.model.Order;
import com.iiitd.ap.lab9.model.Pizza;

/**
 * Servlet implementation class FinalPage
 */
/**
 * 
 * @author Rounaq jhunjhunu Wala | 2014089
 * @author Shrey Bagroy	| 2014099
 *
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
		response.getWriter().println("<!DOCTYPE html><html>	<head>		<meta charset='UTF-8'>		<link rel=\"shortcut icon\" href=\"http://www.google.co/s2/favicons?domain=www.iiitd.ac.in\" type=\"image/x-icon\"><title>Track your Order &#8226; PizzeriaSystem</title>		<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootswatch/3.3.5/united/bootstrap.min.css'>	</head>	<body style='background-image:url(\"http://www.zastavki.com/pictures/1600x1200/2009/Food_Pizza_Filling_Pizza_011932_.jpg\");background-size: 100% 100%;background-repeat:no-repeat;background-position:right bottom;' >	<div class='container'>	<div class='row'><div class='col-lg-8'><h1>Pizzeria System</h1></div>	<div class='col-lg-4'><h1 id='OrderIDHead'>Order ID:</h1></div></div>	<div class='row'><div class='col-lg-12' style='height:768px;'>		<h2>Your final order details</h2>		<table class='table'>		    <thead>		      <tr>		        <th>Pizza name</th>		        <th>Size</th>		        <th>Quantity</th>		      </tr>		    </thead>		    <tbody>		      ");
		for(Pizza p: order.getOrders().keySet())
		{
			response.getWriter().println("<tr class='info'>		        <td>" + p.getToppings() +"</td>		        <td>" + p.getSize()  +"</td>		        <td>" + order.getOrders().get(p) + "</td>		      </tr>");
		}
		response.getWriter().println("</tbody>		</table> <h5>Order Status: " + Database.stateVerbose(order.getStatus()) +"		<h2>Your final delivery details</h2>		<table class='table'>		    <thead>		      <tr>		        <th>Name</th>		        <th>Phone number</th>		        <th>Address</th>		      </tr>		    </thead>		    <tbody>		      ");
		response.getWriter().println("<tr class='info'>		        <td>" + order.getCustomer().getName() + "</td>		        <td>"+ order.getCustomer().getMobileNo() + "</td>		        <td>" +order.getCustomer().getAddress().getStreet() + "," + order.getCustomer().getAddress().getCity() +"," + order.getCustomer().getAddress().getPIN()  +" </td>		      </tr>");
		response.getWriter().println("		    </tbody>		</table>	<a href=\"./\" style=\"color:blue;\">Return to home page</a> 	</div></div></div> <script src='http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js' type='text/javascript'></script>		<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>		<script type='text/javascript'>			function readCookie(name) {			    var nameEQ = name + '=';			    var ca = document.cookie.split(';');			    for(var i=0;i < ca.length;i++) {			        var c = ca[i];			        while (c.charAt(0)==' ') c = c.substring(1,c.length);			        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);			    }			    return null;			}			document.getElementById('OrderIDHead').innerText += readCookie('orderID');		</script>	</body></html>");
		request.getSession().invalidate();
		
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
