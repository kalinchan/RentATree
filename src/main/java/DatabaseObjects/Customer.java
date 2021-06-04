package DatabaseObjects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "Customer", uniqueConstraints = {@UniqueConstraint(columnNames = {"Email", "Password"})})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int CustomerID;
    private String Forename;
    private String Surname;
    private int HouseNo;
    private String StreetName;
    private String City;
    private String PhoneNo;
    private Date DoB;
    @NotNull
    private String Email;
    @NotNull
    private String Password;
    private Boolean IsAdmin;
    private int SuccessCount;
    private int FailCount;

    public Customer(int customerID, String forename, String surname, int houseNo, String streetName, String city, String phoneNo, Date doB, String email, String password, Boolean isAdmin, int successCount, int failCount) {
        CustomerID = customerID;
        Forename = forename;
        Surname = surname;
        HouseNo = houseNo;
        StreetName = streetName;
        City = city;
        PhoneNo = phoneNo;
        DoB = doB;
        Email = email;
        Password = password;
        IsAdmin = isAdmin;
        SuccessCount = successCount;
        FailCount = failCount;
    }

    public Customer() {

    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getForename() {
        return Forename;
    }

    public void setForename(String forename) {
        Forename = forename;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public int getHouseNo() {
        return HouseNo;
    }

    public void setHouseNo(int houseNo) {
        HouseNo = houseNo;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public Date getDoB() {
        return DoB;
    }

    public void setDoB(Date doB) {
        DoB = doB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean getAdmin() {
        return IsAdmin;
    }

    public void setAdmin(Boolean admin) {
        IsAdmin = admin;
    }

    public int getSuccessCount() {
        return SuccessCount;
    }

    public void setSuccessCount(int successCount) {
        SuccessCount = successCount;
    }

    public int getFailCount() {
        return FailCount;
    }

    public void setFailCount(int failCount) {
        FailCount = failCount;
    }
}
