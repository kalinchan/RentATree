package DatabaseObjects;

import javax.persistence.*;

@Entity
@Table(name = "Type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TypeID;
    private String name;

    public Type(int typeID, String name) {
        TypeID = typeID;
        this.name = name;
    }

    public Type() {

    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "TypeID=" + TypeID +
                ", name='" + name + '\'' +
                '}';
    }
}
