package beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import dao.LoginDAO;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 6955508471291131930L;

	private String email;
	private String password;

	@Inject
	LoginDAO loginDAO;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String validateLogin() {
		boolean valid = loginDAO.validate(email, password);

		if (valid) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("email", email);
			return "index";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Email or Password", "Try again"));
		email=null;
		password=null;
		return "null";
	}

	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		email=null;
		password=null;
		return "login";
	}
}
