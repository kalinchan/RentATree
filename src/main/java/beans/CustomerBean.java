package beans;

import DatabaseObjects.Customer;
import dao.CustomerDAO;
import utils.ConvertDate;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Stream;

@Named
@SessionScoped
public class CustomerBean implements Serializable {

    private static final long serialVersionUID = 6998508471291131930L;

    private String forename;
    private String surname;
    private int houseNo;
    private String streetName;
    private String city;
    private String phoneNo;
    private Date DoB;
    private String email;
    private String password;

    @Inject
    CustomerDAO customerDAO;

    @Inject
    ConvertDate convertDate;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getDoB() {
        return DoB;
    }

    public void setDoB(Date doB) {
        DoB = doB;
    }

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

    public void validateEntry() throws IOException {
        boolean checkNull = Stream.of(forename, surname, houseNo, streetName, city, phoneNo, DoB, email, password).anyMatch(e -> ((Comparable) e).equals(""));

        if(!checkNull){
            Customer customer = new Customer(forename, surname, houseNo, streetName, city, phoneNo, convertDate.utilDateToSqlDate(DoB), email, password);

            Exception result = customerDAO.saveCustomer(customer);
            if(DoB.after(Date.from(Instant.now()))){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter a valid Date of Birth", "Try again"));

            }
            if(result!=null && result.toString().contains("Duplicate entry")){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "You are already registered", "Try again"));
            }
            if(result == null){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you for registering with RentATree!", "Try again"));

                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                context.redirect("http://localhost:8080/carddetails.jsf");

            }
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Please fill in the form.", "Try again"));
        }
    }
}
