public class Item {
    private String itemName;
    private double itemPrice;
    private double itemCalories;

    public Item(String itemName, double itemPrice, double itemCalories){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCalories = itemCalories;
    }

    public String getItemName(){
        return itemName;
    }

    public double getItemPrice(){
        return itemPrice;
    }

    public double getItemCalories(){
        return itemCalories;
    }

}
