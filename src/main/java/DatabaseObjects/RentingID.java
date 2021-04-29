package DatabaseObjects;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

public class RentingID implements Serializable {
    private int RentID;
    private int CustomerID;
    private int TreeID;

    public RentingID(int rentID, int customerID, int treeID) {
        RentID = rentID;
        CustomerID = customerID;
        TreeID = treeID;
    }

    public RentingID() {

    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentingID rentingID = (RentingID) o;
        if (RentID != rentingID.RentID) return false;
        return RentID == rentingID.RentID;
    }

    @Override
    public int hashCode(){
        return Objects.hash(RentID, CustomerID, TreeID);
    }
}
