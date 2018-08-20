package quickmart;
// @author Maria Paz Cortez
//This class corresponds to the object Product, that will make up the quick mart's inventory

public class Product {

    private String item;
    private int quantity;
    private float memberprice, regularPrice;
    private String taxStatus;

    public Product(String item, int quantity, float memberprice, float regularPrice, String taxStatus) {
        this.item = item;
        this.quantity = quantity;
        this.memberprice = memberprice;
        this.regularPrice = regularPrice;
        this.taxStatus = taxStatus;
    }

    public Product() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getMemberprice() {
        return memberprice;
    }

    public void setMemberprice(float memberprice) {
        this.memberprice = memberprice;
    }

    public float getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(float regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    @Override
    public String toString() {
        return "PRODUCT: " + item + " STORAGE AVAILABLE= " + quantity + ", MEMBER PRICE= $" + memberprice + ", REGULAR PRICE= $" + regularPrice + ", TAX STATUS= " + taxStatus;
    }

}
