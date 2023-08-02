/**
 * The Item class represents an item with its name, price, and calories.
 */
public class Item {
    private String itemName;
    private double itemPrice;
    private double itemCalories;

    /**
     * Constructs an Item object with the given name, price, and calories.
     *
     * @param itemName The name of the item.
     * @param itemPrice The price of the item.
     * @param itemCalories The number of calories in the item.
     */
    public Item(String itemName, double itemPrice, double itemCalories){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCalories = itemCalories;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getItemName(){
        return itemName;
    }

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
    public double getItemPrice(){
        return itemPrice;
    }

    /**
     * Gets the number of calories in the item.
     *
     * @return The number of calories in the item.
     */
    public double getItemCalories(){
        return itemCalories;
    }

    /**
     * Sets a new price for the item.
     *
     * @param newPrice The new price to set for the item.
     */
    public void setItemPrice(double newPrice) {
        this.itemPrice = newPrice;
    }
}
