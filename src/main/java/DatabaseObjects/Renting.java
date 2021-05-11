package DatabaseObjects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "Renting")
@IdClass(RentingID.class)
@NamedQueries({
        @NamedQuery(name = "Renting.getAllTrees", query = "SELECT renting.tree FROM Renting renting")
})
public class Renting {
    @Id
    @NotNull
    private int RentID;
    @Id
    @NotNull
    private int CustomerID;
    @Id
    @NotNull
    private int TreeID;
    private Date StartDate;
    private Date EndDate;
    private String DeliveryAddress;
    private String ReturnAddress;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CustomerID", insertable = false, updatable = false)
    @NotNull
    private Customer customer;

    @ManyToOne(targetEntity = Tree.class)
    @JoinColumn(name = "TreeID", insertable = false, updatable = false)
    @NotNull
    private Tree tree;


    public Renting(int rentID, int customerID, int treeID, Date startDate, Date endDate, String deliveryAddress, String returnAddress) {
        RentID = rentID;
        CustomerID = customerID;
        TreeID = treeID;
        StartDate = startDate;
        EndDate = endDate;
        DeliveryAddress = deliveryAddress;
        ReturnAddress = returnAddress;
    }

    public Renting() {

    }

    public int getRentID() {
        return RentID;
    }

    public void setRentID(int rentID) {
        RentID = rentID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public int getTreeID() {
        return TreeID;
    }

    public void setTreeID(int treeID) {
        TreeID = treeID;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getDeliveryAddress() {
        return DeliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        DeliveryAddress = deliveryAddress;
    }

    public String getReturnAddress() {
        return ReturnAddress;
    }

    public void setReturnAddress(String returnAddress) {
        ReturnAddress = returnAddress;
    }
}
