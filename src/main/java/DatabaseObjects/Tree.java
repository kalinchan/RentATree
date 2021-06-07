package DatabaseObjects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

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

    @Transient
    private List<Date> range;
    @Transient
    private int numberOfDaysBooked;
    @Transient
    private int quantityOrdered = 1;

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

    public String formattedMailToString(){
        return Height+"ft "+material.getName()+" "+type.getName()+" Tree x"+quantityOrdered+"\n"+description;
    }

    public List<Date> getRange() {
        return range;
    }

    public void setRange(List<Date> range) {
        this.range = range;
        //When the range is set the number of days booked should be updated
        numberOfDaysBooked = calculateDaysBooked(range);

    }

    public int getNumberOfDaysBooked() {
        return numberOfDaysBooked;
    }

    public void setNumberOfDaysBooked(int numberOfDaysBooked) {
        this.numberOfDaysBooked = numberOfDaysBooked;
    }

    public int calculateDaysBooked(List<Date> range){
        LocalDate start = range.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = range.get(1).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int daysBooked = (int) ChronoUnit.DAYS.between(start,end);
        if (daysBooked!=0){
            //+1 To offset the 0 index
            return daysBooked+1;
        }
        return daysBooked;
    }

    public double getTotalDailyPrice(Tree selectedTree){
        return (getMaterialDailyPrice()*getNumberOfDaysBooked());
    }

    public double getTotalPrice(Tree selectedTree){
        return (getTotalDailyPrice(selectedTree)+getDeposit())*selectedTree.getQuantityOrdered();
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
}
