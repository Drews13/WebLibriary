package net.weblibrary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.weblibrary.model.Order;

public class OrderDAO {
	private ConnectionPool pool=ConnectionPool.getInstance();
	
	private static final String SELECT_ORDERS_SQL = "SELECT * FROM order";
	private static final String UPDATE_CONIRM_ORDER_SQL = "UPDATE order SET status = true WHERE id = ?";
	private static final String SELECT_ORDERS_READER_SQL = "SELECT * FROM order WHERE reader_id = ?";
	private static final String INSERT_ORDER_SQL = "INSERT INTO order (id, reader_id, book_id, status) VALUES (NULL, ?, ?, false)";
	private static final String DELETE_ORDER_SQL = "DELETE FROM order WHERE id = ?";
	
	public List<Order> getOrderList() throws SQLException {
		ArrayList<Order> orders = new ArrayList<Order>();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_SQL)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				orders.add(new Order(rs.getInt("id"),rs.getInt("reader_id"),rs.getInt("book_id"),rs.getBoolean("status")));
			}
		} catch (SQLException e) {
			printSQLException(e);
			return null;
		}
		return orders;
	}
	
	public void confirmOrder(int order_id) throws SQLException {
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONIRM_ORDER_SQL)) {
			preparedStatement.setInt(1, order_id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public ArrayList<Order> getOrderListReader(int reader_id) throws SQLException {
		ArrayList<Order> orders = new ArrayList<Order>();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_READER_SQL)) {
			preparedStatement.setInt(1, reader_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				orders.add(new Order(rs.getInt("id"),rs.getInt("reader_id"),rs.getInt("book_id"),rs.getBoolean("status")));
			}
		} catch (SQLException e) {
			printSQLException(e);
			return null;
		}
		return orders;
	}
	
	public void createOrder(int reader_id, int book_id) throws SQLException {
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SQL)) {
			preparedStatement.setInt(1, reader_id);
			preparedStatement.setInt(2, book_id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public void closeOrder(int order_id) throws SQLException {
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_SQL)) {
			preparedStatement.setInt(1, order_id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
