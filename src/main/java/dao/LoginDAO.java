package dao;

import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Dependent
public class LoginDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(LoginDAO.class.getName());

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	public boolean validate(String email, String password) {
		try {
			List resultSet = entityManager.createQuery("SELECT c.Email, c.Password FROM Customer c WHERE c.Email=:email AND c.Password=:password")
					.setParameter("email", email)
					.setParameter("password", password)
					.getResultList();

			if (resultSet.size()>0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.info("SQLException: " + e);
			return false;
		}
	}

	public boolean isAdmin(String email, String password) {
		try {
			List resultSet = entityManager.createQuery("SELECT c.Email, c.Password FROM Customer c WHERE c.Email=:email AND c.Password=:password AND c.IsAdmin=1")
					.setParameter("email", email)
					.setParameter("password", password)
					.getResultList();
			if (resultSet.size()>0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.info("SQLException: " + e);
			return false;
		}
	}
}
