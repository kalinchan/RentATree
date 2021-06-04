package beans;

import DatabaseObjects.CardDetails;
import email.MailHandler;
import enums.Country;
import utils.ConvertDate;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Named
@ViewScoped
public class CardDetailsBean  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String postcode;
	private String city;
	private Country country;
	private boolean firstPage;
	private int cardNum;
	private int ccv;
	private Date expiry;

	private LocalDate minDate;

	@Inject
	MailHandler mailHandler;

	@PostConstruct
	public void init() {
		firstPage = true;
		minDate = LocalDate.now();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	
	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	public int getCcv() {
		return ccv;
	}

	public void setCcv(int ccv) {
		this.ccv = ccv;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public LocalDate getMinDate() {
		return minDate;
	}

	public void setMinDate(LocalDate minDate) {
		this.minDate = minDate;
	}

	public String toString() {
		return String.format("Email: %s FName: %s LName: %s Address Line 1: %s Address Line 2: %s Postcode: %s City: %s Country: %s", email, firstName, lastName, addressLine1, addressLine2, postcode, city, country.getText());
	}

	public void submit() throws IOException {
		//The specification never said to do anything with the card details.
		//CardDetails cardDetails = new CardDetails(email, firstName, lastName, addressLine1, addressLine2, postcode,
		//		city, country, cardNum, ccv, ConvertDate.utilDateToSqlDate(expiry));

		//cardDetailsDAO.saveCardDetails(cardDetails);
		mailHandler.sendMail(email);

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.redirect("http://localhost:8080/index.jsf");
	}
}
