package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import DatabaseObjects.Customer;
import dao.CustomerDAO;

@Named
@RequestScoped
public class CustomerBean {
	
	@Inject
	CustomerDAO customerDAO;

	private List<Customer> customers;
	
	@PostConstruct
	public void init() {
		customers = customerDAO.getAllCustomers();
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
