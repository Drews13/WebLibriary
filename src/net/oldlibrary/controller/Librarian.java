package net.weblibrary.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import net.weblibrary.dao.BookDAO;
import net.weblibrary.dao.OrderDAO;
import net.weblibrary.model.Book;
import net.weblibrary.model.Order;


@WebServlet("/Librarian")
public class Librarian extends HttpServlet {
	private static final Logger log = Logger.getLogger(Librarian.class);
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO = new BookDAO();
	private OrderDAO orderDAO = new OrderDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		log.info(action);
			switch (action) {
			case "/list_orders":
				try {
					listOrders(request, response);
				}catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/confirm_order":
				try {
					confirmOrder(request, response);
				}catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

	private void listOrders(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Order> orders = orderDAO.getOrderList();
		request.setAttribute("orders", orders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/librarianMain.jsp");
		dispatcher.forward(request, response);
	}

	private void confirmOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		if (bookDAO.giveBook(Integer.parseInt(request.getParameter("book_id")))) {
			orderDAO.confirmOrder(Integer.parseInt(request.getParameter("order_id")));
			request.setAttribute("ok", true);
		}else {
			request.setAttribute("ok", false);
		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/librarianMain.jsp");
		dispatcher.forward(request, response);
	}
}
