package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

@Dependent
public class LoginDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(LoginDAO.class.getName());

	@Resource(lookup = "jdbc/mariadbpool")
	DataSource dataSource;

	public boolean validate(String email, String password) {
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT Email, Password FROM Customer WHERE Email=? AND Password=?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.info("SQLException: " + e);
			return false;
		}
	}

	public boolean isAdmin(String email, String password) {
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT Email, Password FROM Customer WHERE Email=? AND Password=? AND IsAdmin = 1");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.info("SQLException: " + e);
			return false;
		}
	}
}
