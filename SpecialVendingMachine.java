import java.util.ArrayList;

/**
 * The SpecialVendingMachine class represents a special vending machine that extends the functionality of the base VendingMachine class.
 * It inherits features and behavior from the VendingMachine class and has an additional feature of supporting add-ons.
 */
public class SpecialVendingMachine extends VendingMachine {
    private ArrayList<Item> addOns; // added feature - list of add-ons

    /**
     * Constructs a SpecialVendingMachine object.
     * This constructor calls the default constructor of the parent class, VendingMachine, to initialize its state.
     */
    public SpecialVendingMachine() {
        super();
    }

    /**
     * Gets the list of add-ons available in the special vending machine.
     *
     * @return An ArrayList of Item objects representing the available add-ons.
     */
    public ArrayList<Item> getAddOns() {
        return addOns;
    }
}
