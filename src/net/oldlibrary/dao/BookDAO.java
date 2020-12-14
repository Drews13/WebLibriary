package net.weblibrary.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.weblibrary.model.Book;
import net.weblibrary.model.Order;

public class BookDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();
	
	private static final String SELECT_BOOK_COUNT_SQL = "SELECT count FROM book WHERE id = ?";
	private static final String SELECT_BOOKS_SQL = "SELECT * FROM book";
	private static final String UPDATE_BOOK_GIVE_SQL = "UPDATE book SET count = (count - 1) true WHERE id = ?";
	private static final String UPDATE_BOOK_GOT_SQL = "UPDATE book SET count = (count + 1) true WHERE id = ?";
	private static final String SELECT_BOOK_SEARCH_SQL = "SELECT * FROM book WHERE name LIKE ?";
	
	public boolean giveBook(int book_id) throws SQLException {
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_COUNT_SQL)) {
			preparedStatement.setInt(1, book_id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next() && rs.getInt("count") > 0) {
				PreparedStatement preparedStatement2 = connection.prepareStatement(UPDATE_BOOK_GIVE_SQL);
				preparedStatement2.setInt(1, book_id);
				preparedStatement2.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			printSQLException(e);
			return false;
		}
		return false;
	}
	
	public void gotBook(int book_id) throws SQLException {
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_GOT_SQL)) {
			preparedStatement.setInt(1, book_id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public ArrayList<Book> getBookList() throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_SQL)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				books.add(new Book(rs.getInt("id"),rs.getString("name"),rs.getString("author"),rs.getInt("count")));
			}
		} catch (SQLException e) {
			printSQLException(e);
			return null;
		}
		return books;
	}
	
	public ArrayList<Book> getBookSearch(String search) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		try (Connection connection = pool.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_SEARCH_SQL)) {
			preparedStatement.setString(1, search);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				books.add(new Book(rs.getInt("id"),rs.getString("name"),rs.getString("author"),rs.getInt("count")));
			}
		} catch (SQLException e) {
			printSQLException(e);
			return null;
		}
		return books;
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


