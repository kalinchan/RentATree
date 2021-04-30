package DatabaseObjects;

public enum TreeStock {
    INSTOCK("In Stock"),
    OUTOFSTOCK("Out of Stock");

    private String text;

    TreeStock(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
