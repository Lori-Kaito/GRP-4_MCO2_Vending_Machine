/**
 * The VendingMachineController class is responsible for setting up and running the vending machine application.
 * It creates a VendingMachine instance and a VendingMachineView instance to manage the vending machine's data and user interface.
 */
public class VendingMachineController {

    /**
     * Constructs a VendingMachineController object.
     * Initializes a new VendingMachine and a VendingMachineView, setting up the vending machine application.
     */
    public VendingMachineController() {
        VendingMachine vendingMachine = new VendingMachine();
        VendingMachineView view = new VendingMachineView(vendingMachine);
    }

    /**
     * The main method to start the vending machine application.
     * Creates a new instance of VendingMachineController, which sets up and runs the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new VendingMachineController();
    }
}
