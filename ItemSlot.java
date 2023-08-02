/**
 * The ItemSlot class represents a slot that holds an item and its quantity.
 */
public class ItemSlot {
    private Item item;
    private int quantity;

    /**
     * Constructs an ItemSlot object with the given item and quantity.
     *
     * @param item The item to be placed in the slot.
     * @param quantity The quantity of the item in the slot.
     */
    public ItemSlot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Gets the item stored in the slot.
     *
     * @return The item stored in the slot.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Gets the quantity of the item in the slot.
     *
     * @return The quantity of the item in the slot.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item in the slot.
     *
     * @param quantity The new quantity of the item to set in the slot.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
