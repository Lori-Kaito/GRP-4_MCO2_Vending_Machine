import java.util.ArrayList;

/**
 * The VendingMachine class represents a basic vending machine that holds a list of items and item slots.
 * It allows adding items and item slots, and also tracks the current balance.
 */
public class VendingMachine {
    private ArrayList<Item> itemList; // List of available items in the vending machine
    private ArrayList<ItemSlot> itemSlots; // List of item slots for storing items
    private double balance = 0.0; // Current balance in the vending machine

    /**
     * Constructs a VendingMachine object.
     * Initializes the itemList and itemSlots as empty ArrayLists.
     */
    public VendingMachine() {
        this.itemList = new ArrayList<Item>();
        this.itemSlots = new ArrayList<ItemSlot>();
    }

    /**
     * Adds an item to the vending machine's item list.
     *
     * @param item The item to be added to the vending machine.
     */
    public void addItem(Item item) {
        itemList.add(item);
    }

    /**
     * Adds an item slot to the vending machine's item slots list.
     *
     * @param itemSlot The item slot to be added to the vending machine.
     */
    public void addItemSlot(ItemSlot itemSlot) {
        itemSlots.add(itemSlot);
    }

    /**
     * Gets the list of item slots in the vending machine.
     *
     * @return An ArrayList of ItemSlot objects representing the item slots in the vending machine.
     */
    public ArrayList<ItemSlot> getItemSlots() {
        return itemSlots;
    }

    /**
     * Gets the current balance in the vending machine.
     *
     * @return The current balance in the vending machine.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance in the vending machine.
     *
     * @param balance The new balance to set in the vending machine.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
