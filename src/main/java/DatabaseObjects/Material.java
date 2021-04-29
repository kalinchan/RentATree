package DatabaseObjects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int MaterialID;
    private String name;
    private Double DailyPrice;


    public Material(int materialID, String name, Double dailyPrice) {
        this.MaterialID = materialID;
        this.name = name;
        this.DailyPrice = dailyPrice;
    }

    public Material() {
    }

    public int getMaterialID() {
        return MaterialID;
    }

    public void setMaterialID(int materialID) {
        MaterialID = materialID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDailyPrice() {
        return DailyPrice;
    }

    public void setDailyPrice(Double dailyPrice) {
        DailyPrice = dailyPrice;
    }

    @Override
    public String toString() {
        return "Material{" +
                "MaterialID=" + MaterialID +
                ", name='" + name + '\'' +
                ", DailyPrice=" + DailyPrice +
                '}';
    }
}
