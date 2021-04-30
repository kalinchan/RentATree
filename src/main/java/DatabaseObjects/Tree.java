package DatabaseObjects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Tree")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int TreeID;
    @NotNull
    private int MaterialID;
    @NotNull
    private int TypeID;
    private int Height;
    private String description;
    private String Supplier;
    private int Stock;
    @Enumerated(EnumType.STRING)
    private TreeStock Available;
    private int Deposit;

    @ManyToOne(targetEntity = Material.class)
    @JoinColumn(name = "MaterialID", insertable = false, updatable = false)
    @NotNull
    private Material material;

    @ManyToOne(targetEntity = Type.class)
    @JoinColumn(name = "TypeID", insertable = false, updatable = false)
    @NotNull
    private Type type;

    public Tree(int treeID, int typeID, int materialID, int height, String description, String supplier, int stock, TreeStock available, int deposit) {
        TreeID = treeID;
        Height = height;
        this.description = description;
        Supplier = supplier;
        Stock = stock;
        Available = available;
        Deposit = deposit;
    }

    // Hibernate needs an no args constructor
    public Tree(){

    }

    public int getTreeID() {
        return TreeID;
    }

    public void setTreeID(int treeID) {
        TreeID = treeID;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public int getMaterialID() {
        return MaterialID;
    }

    public void setMaterialID(int materialID) {
        MaterialID = materialID;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public TreeStock getAvailable() {
        return Available;
    }

    public void setAvailable(TreeStock available) {
        Available = available;
    }

    public int getDeposit() {
        return Deposit;
    }

    public void setDeposit(int deposit) {
        Deposit = deposit;
    }

    public String getTypeName(){
        return type.getName();
    }

    public String getMaterialName(){
        return material.getName();
    }

    public Double getMaterialDailyPrice(){
        return material.getDailyPrice();
    }

    @Override
    public String toString() {
        return "Tree{" +
                "TreeID=" + TreeID +
                ", MaterialID=" + MaterialID +
                ", TypeID=" + TypeID +
                ", Height=" + Height +
                ", description='" + description + '\'' +
                ", Supplier='" + Supplier + '\'' +
                ", Stock=" + Stock +
                ", Available=" + Available +
                ", Deposit=" + Deposit +
                ", material=" + material +
                ", type=" + type +
                '}';
    }
}
