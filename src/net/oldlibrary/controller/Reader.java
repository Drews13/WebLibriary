package net.weblibrary.controller;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/student")
public class Reader extends HttpServlet {
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
			case "/list_books":
				try{
					listBooks(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/search_book":
				try{
					searchBook(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/order_book":
				try{
					orderBook(request, response);
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			case "/close_order":
				try {
					closeOrder(request, response);
				}catch(SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private void listOrders(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Order> orders = orderDAO.getOrderListReader(Integer.parseInt(request.getParameter("reader_id")));
		request.setAttribute("orders", orders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/readerMain.jsp");
		dispatcher.forward(request, response);
	}
	
	private void orderBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		orderDAO.createOrder(Integer.parseInt(request.getParameter("reader_id")),Integer.parseInt(request.getParameter("book_id")));
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/books.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listBooks(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Book> books = bookDAO.getBookList();
		request.setAttribute("books", books);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/books.jsp");
		dispatcher.forward(request, response);
	}
	
	private void searchBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Book> books = bookDAO.getBookSearch(request.getParameter("search"));
		request.setAttribute("books", books);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/books.jsp");
		dispatcher.forward(request, response);
	}
	
	private void closeOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		orderDAO.closeOrder(Integer.parseInt(request.getParameter("order_id")));
		bookDAO.gotBook(Integer.parseInt(request.getParameter("book_id")));;
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/librarianMain.jsp");
		dispatcher.forward(request, response);
	}
}
