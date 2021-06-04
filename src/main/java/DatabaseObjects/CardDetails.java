package DatabaseObjects;

import enums.Country;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CardDetails", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class CardDetails {
    //We need 'Duplicate Names/Addresses' as payment details may be different to personal details
    @Id
    @NotNull
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String addressLine1;
    private String addressLine2;
    @NotNull
    private String postcode;
    @NotNull
    private String city;
    @NotNull
    private Country country;
    @NotNull
    private int cardNum;
    @NotNull
    private int ccv;
    @NotNull
    private java.sql.Date expiry;

    public CardDetails() {
    }

    public CardDetails(String email, String firstName, String lastName, String addressLine1, String addressLine2, String postcode, String city, Country country, int cardNum, int ccv, java.sql.Date expiry) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
        this.cardNum = cardNum;
        this.ccv = ccv;
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "CardDetails{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                ", cardNum=" + cardNum +
                ", ccv=" + ccv +
                ", expiry=" + expiry +
                '}';
    }
}
