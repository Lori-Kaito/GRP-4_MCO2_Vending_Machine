import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.sound.sampled.*;
import javax.swing.Timer;

/**
 * The VendingMachineView class represents the graphical user interface (GUI) of the vending machine application.
 * It provides methods to display various screens and features of the vending machine, allowing users to interact with it.
 */
public class VendingMachineView {
    private VendingMachine vendingMachine; // Reference to the VendingMachine model
    private SpecialVendingMachine specialVendingMachine; // Reference to the SpecialVendingMachine model
    private JFrame frame1; // Main frame for the initial screen
    private JButton createBtn; // Button to create a new vending machine
    private JButton testBtn; // Button to test a vending machine
    private JButton exitBtn; // Button to exit the application
    private JFrame frame2; // Frame for the "Create A Vending Machine" screen
    private double amount = 0.0; // Keeps track of the amount inserted by the user


    /**
     * Constructs a VendingMachineView object and initializes the graphical user interface (GUI) for the vending machine application.
     *
     * @param vendingMachine The VendingMachine instance that the GUI interacts with.
     */
    public VendingMachineView(VendingMachine vendingMachine){
        playBackgroundMusic();
        this.vendingMachine = vendingMachine;
        this.frame1 = new JFrame("Vending Machine Factory");
        this.frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame1.setSize(400, 200);
        this.frame1.setLayout(new GridLayout(3, 1, 5, 5));

        this.createBtn = new JButton("Create A Vending Machine");
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCreateMain();
            }
        });

        this.testBtn = new JButton("Test A Vending Machine");
        testBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTestMain(vendingMachine);
            }
        });
        
        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.frame1.add(createBtn);
        this.frame1.add(testBtn);
        this.frame1.add(exitBtn);

        this.frame1.setVisible(true);
    }

    /**
     * Plays the background music for the vending machine application.
     * Adjusts the volume of the music and starts playing it in a loop.
     */
    private void playBackgroundMusic() {
        try {
            // Load the audio file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("Pokemon- Diamond and Pearl- Pokemon Center- Music.WAV"));

            // Get the audio format
            AudioFormat audioFormat = audioInputStream.getFormat();

            // Create a data line to play the audio
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            Clip clip = (Clip) AudioSystem.getLine(info);

            // Open the audio stream
            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Set the volume of background music
            float volume = -32.0f; // Adjust this value to set the desired volume level
            gainControl.setValue(volume);

            // Start playing the background music in a loop
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            // Handle any exceptions that may occur during audio playback
            e.printStackTrace();
        }
    }

    /**
     * Displays the main screen for creating a new vending machine.
     * Prompts the user for a password and provides options for creating a regular or special vending machine.
     */    
    public void displayCreateMain(){
        boolean isPasswordCorrect = askForPassword();
        if (isPasswordCorrect) {
            JOptionPane.showMessageDialog(frame1, "Welcome owner, you may proceed.", "Welcome", JOptionPane.PLAIN_MESSAGE);
            this.frame2 = new JFrame("Create A Vending Machine");
        } else {
            // Password is incorrect, show a message dialog
            JOptionPane.showMessageDialog(frame1, "Incorrect password. Cannot proceed.", "Password Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        this.frame2 = new JFrame("Create A Vending Machine");
        frame2.setSize(400, 200);
        frame2.setLayout(new GridLayout(3, 1, 5, 5));
                
        JLabel label2 = new JLabel("Choose the type of Vending Machine");
        label2.setHorizontalAlignment(JLabel.CENTER);
        
        JButton regularBtn = new JButton("Regular Vending Machine");      
        regularBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRegMain(new VendingMachine());
            }
        });

        JButton specialBtn = new JButton("Special Vending Machine");
         specialBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpecialVendingMachine vendingMachine = new SpecialVendingMachine();
                displayRegMain(vendingMachine);
            }
        });

        frame2.add(label2);
        frame2.add(regularBtn);
        frame2.add(specialBtn);

        frame2.setVisible(true);
        frame1.dispose();

    }
   
    /**
     * Displays the main screen for creating a regular vending machine.
     * This screen allows the user to input the initial balance and details for each item slot.
     * The user can then save the vending machine configuration.
     * 
     * @param vendingMachine The VendingMachine object to be populated with the user's input.
     */
    public void displayRegMain(VendingMachine vendingMachine) {    
        
        JFrame frame4 = new JFrame("Creating a Vending Machine");
        frame4.setSize(400, 400);
    
        JPanel mainPanel = new JPanel(new GridLayout(11, 4, 5, 5)); 

        JLabel balanceLabel = new JLabel("Balance: ");
        JTextField balanceTextField = new JTextField();
        mainPanel.add(balanceLabel); 
        mainPanel.add(balanceTextField);
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel()); 

        // Labels for the first row
        JLabel nameLabel = new JLabel("Name");
        JLabel priceLabel = new JLabel("Price");
        JLabel caloriesLabel = new JLabel("Calories");

        mainPanel.add(new JLabel()); 
        mainPanel.add(nameLabel);
        mainPanel.add(priceLabel);
        mainPanel.add(caloriesLabel);

        JLabel[] slotLabels = new JLabel[8];
        JTextField[][] slotFields = new JTextField[8][3]; 
    
        // Create labels and text fields for each slot
        for (int i = 0; i < 8; i++) {
            slotLabels[i] = new JLabel("Slot " + (i + 1) + ": ");
            slotFields[i][0] = new JTextField(); // Item name
            slotFields[i][1] = new JTextField(); // Item price
            slotFields[i][2] = new JTextField(); // Item calories
    
            // Add components to each row (slot details)
            mainPanel.add(slotLabels[i]);
            mainPanel.add(slotFields[i][0]);
            mainPanel.add(slotFields[i][1]);
            mainPanel.add(slotFields[i][2]);
        }
 
        JButton saveBtn = new JButton("Save");

        mainPanel.add(new JLabel()); 
        mainPanel.add(saveBtn); 
    
        frame4.add(mainPanel); 

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate the balance input
                try {
                    double balance = Double.parseDouble(balanceTextField.getText());
                    if (balance <= 0) {
                        JOptionPane.showMessageDialog(null, "Balance cannot be less than or equal to 0.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid balance input. Please enter a valid number.");
                    return;
                }
        
                // Validate the item prices and calories
                for (int i = 0; i < 8; i++) {
                try {
                    double price = Double.parseDouble(slotFields[i][1].getText());
                    int calories = Integer.parseInt(slotFields[i][2].getText());

                    if (price <= 0) {
                        JOptionPane.showMessageDialog(null, "Item price in Slot " + (i + 1) + " cannot be 0 or less.");
                        return;
                    }

                    if (calories < 0) {
                        JOptionPane.showMessageDialog(null, "Item calories in Slot " + (i + 1) + " cannot be negative.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input in Slot " + (i + 1) + ". Please enter a valid number.");
                    return;
                }
            }

                // If all validations pass, create and save the vending machine
                createVendingMachineFromInput(slotFields, balanceTextField, vendingMachine);
                frame4.dispose();
                new VendingMachineView(vendingMachine);
            }
        });
        
        
    
        frame4.setVisible(true);
        frame2.dispose();
    
    }

    /**
     * Creates a new VendingMachine instance based on the input provided by the user in the "Create A Vending Machine" screen.
     * The vending machine configuration includes the initial balance and details of each item slot.
     *
     * @param slotFields      A 2D array of JTextFields containing the item details entered by the user in each slot.
     * @param balanceTextField The JTextField containing the initial balance input by the user.
     */
    private void createVendingMachineFromInput(JTextField[][] slotFields, JTextField balanceTextField, VendingMachine vendingMachine) {
        double balance = Double.parseDouble(balanceTextField.getText());
        vendingMachine.setBalance(balance);
    
        for (int i = 0; i < 8; i++) {
            String itemName = slotFields[i][0].getText();
            double itemPrice = Double.parseDouble(slotFields[i][1].getText());
            double itemCalorie = Double.parseDouble(slotFields[i][2].getText());
            Item item = new Item(itemName, itemPrice, itemCalorie);
    
            int quantity = 10;
            ItemSlot itemSlot = new ItemSlot(item, quantity);
            vendingMachine.addItemSlot(itemSlot);
        }
    }
    

     /**
     * Displays a summary of the transactions for all item slots in the vending machine.
     * The summary includes the names, prices, calories, starting inventory, sold quantity, and remaining inventory for each item.
     * This information helps the owner track the sales and inventory status of the vending machine.
     */
    public void displaySummaryOfItems() {
        JFrame summaryFrame = new JFrame("Transaction Summary");
        summaryFrame.setSize(900, 400);
        summaryFrame.setLayout(new GridLayout(vendingMachine.getItemSlots().size() + 1, 5, 5, 5));
    
        JLabel nameLabel = new JLabel("Name");
        JLabel priceLabel = new JLabel("Price");
        JLabel caloriesLabel = new JLabel("Calories");
        JLabel startingInvLabel = new JLabel("Starting Inventory");
        JLabel soldLabel = new JLabel("Sold");
        JLabel remainingInvLabel = new JLabel("Remaining Inventory");
    
        summaryFrame.add(nameLabel);
        summaryFrame.add(priceLabel);
        summaryFrame.add(caloriesLabel);
        summaryFrame.add(startingInvLabel);
        summaryFrame.add(soldLabel);
        summaryFrame.add(remainingInvLabel);
    
        for (ItemSlot itemSlot : vendingMachine.getItemSlots()) {
            Item item = itemSlot.getItem();
            int startingInventory = 10;
            int soldQuantity = 10 - itemSlot.getQuantity();
            int remainingInventory = itemSlot.getQuantity();
    
            JLabel name = new JLabel(item.getItemName());
            JLabel price = new JLabel(String.valueOf(item.getItemPrice()));
            JLabel calories = new JLabel(String.valueOf(item.getItemCalories()));
            JLabel startingInv = new JLabel(String.valueOf(startingInventory));
            JLabel sold = new JLabel(String.valueOf(soldQuantity));
            JLabel remainingInv = new JLabel(String.valueOf(remainingInventory));
    
            summaryFrame.add(name);
            summaryFrame.add(price);
            summaryFrame.add(calories);
            summaryFrame.add(startingInv);
            summaryFrame.add(sold);
            summaryFrame.add(remainingInv);
        }
    
        summaryFrame.setVisible(true);
    }
    

    /**
     * Displays the main screen for testing the vending machine.
     * This screen allows the user to choose between vending machine features and maintenance features.
     * Additionally, the user can view a summary of the transactions for all item slots in the vending machine.
     * 
     * @param vendingMachine The VendingMachine object to be used for testing and accessing features.
     */
    public void displayTestMain(VendingMachine vendingMachine){
        JFrame frame3 = new JFrame("Test A Vending Machine");
        frame3.setSize(400, 200);
        frame3.setLayout(new GridLayout(5, 1, 5, 5));

        JLabel label3 = new JLabel("Choose a transaction of Vending Machine");
        label3.setHorizontalAlignment(JLabel.CENTER);
        
        JButton testFeatureBtn = new JButton("Vending Features");
        testFeatureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame3.dispose();
                displayTestFeatures(vendingMachine);
            }
        });


        JButton maintenanceBtn = new JButton("Maintenance Features");
        maintenanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame3.dispose();
                displayMaintenance();
            }
        });
        
        JButton summaryBtn = new JButton("Summary of Transactions");
        summaryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySummaryOfItems();
            }
        });
        
        JButton exitBtn = new JButton("Exit");
         exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VendingMachineView(vendingMachine);
                frame3.dispose();
            }
        });  

        frame3.add(label3);
        frame3.add(testFeatureBtn);
        frame3.add(maintenanceBtn);
        frame3.add(summaryBtn);
        frame3.add(exitBtn);

        frame3.setVisible(true);
        frame1.dispose();
    }


    /**
     * Displays the testing screen for the vending machine.
     * This screen allows the user to insert money and select items for purchase.
     * The user can view item names, prices, and quantities, and make a purchase by clicking on the item buttons.
     * 
     * @param vendingMachine The VendingMachine object to be used for testing features.
     */
    public void displayTestFeatures(VendingMachine vendingMachine) {
        JFrame frame5 = new JFrame("Testing Vending Features");
        frame5.setSize(400, 400);
        frame5.setLayout(new GridLayout(10, 2, 5, 5)); // 8 rows for items + 1 row for money input
    
        if (vendingMachine.getBalance() <= 0) {
            JOptionPane.showMessageDialog(null, "Vending Machine has no balance. Please try again later.");
            frame5.dispose();
            displayTestMain(vendingMachine);
            return;
        }
    
        JTextField moneyField = new JTextField();
        JButton insertMoneyBtn = new JButton("Insert Money");
    
        frame5.add(moneyField);
        frame5.add(insertMoneyBtn);
         ArrayList<Item> selectedItems = new ArrayList<>();

        // Add a new "Done" button for special vending machines
        if (vendingMachine instanceof SpecialVendingMachine) {
            JCheckBox[] itemCheckBoxes = new JCheckBox[8]; // Array to hold item checkboxes
            for (int i = 0; i < vendingMachine.getItemSlots().size(); i++) {
                ItemSlot itemSlot = vendingMachine.getItemSlots().get(i);
                Item item = itemSlot.getItem();
                JCheckBox itemCheckBox = new JCheckBox(item.getItemName() + " (P" + item.getItemPrice() + ") - Quantity: " + itemSlot.getQuantity());
                itemCheckBoxes[i] = itemCheckBox;
                frame5.add(itemCheckBox);
            }
        
            JButton doneButton = new JButton("Done");
            doneButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double totalAmount = 0.0;
                    double totalCalories = 0.0;
                    // Calculate the total amount of the selected items
                    for (int i = 0; i < itemCheckBoxes.length; i++) {
                        if (itemCheckBoxes[i].isSelected()) {
                            ItemSlot itemSlot = vendingMachine.getItemSlots().get(i);
                            Item item = itemSlot.getItem();
                            selectedItems.add(item);
                            totalAmount += item.getItemPrice();
                            totalCalories += item.getItemCalories();

                            // Update the quantity of the selected item
                    itemSlot.setQuantity(itemSlot.getQuantity() - 1);

                    // Update the vending machine's balance
                    vendingMachine.setBalance(vendingMachine.getBalance() - item.getItemPrice());
                
                        }
                    }
        
                    // Check if the total amount is covered by the balance
                    if (vendingMachine.getBalance() >= totalAmount) {
                        // Calculate the change
                            double change = amount - totalAmount;
                            JOptionPane.showMessageDialog(null, "Change: P" + String.format("%.2f", change));
                            JOptionPane.showMessageDialog(null, "Total Calories: " + totalCalories);
                               
                        // Perform the transaction for all selected items
                        for (Item selectedItem : selectedItems) {
                            //vendingMachine.setBalance(vendingMachine.getBalance() - selectedItem.getItemPrice()); // Deduct the item price from the vending machine's balance
                            JOptionPane.showMessageDialog(null, "Preparing" + " " + selectedItem.getItemName() + "!");
                        }
        
                        // Delay between steps
                        int delay = 3000; // 5 seconds
                        Timer timer = new Timer(delay, new ActionListener() {
                            int step = 0;
        
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (step == 0) {
                                    JOptionPane.showMessageDialog(null, "Cooking toppings...");
                                } else if (step == 1) {
                                    JOptionPane.showMessageDialog(null, "Cooking rice...");
                                } else if (step == 2) {
                                    JOptionPane.showMessageDialog(null, "Cooking egg...");
                                } else if (step == 3) {
                                    JOptionPane.showMessageDialog(null, "Putting them into a cup...");
                                } else if (step == 4) {
                                    JOptionPane.showMessageDialog(null, "Silog meal done...");
                                }
                                step++;
        
                                if (step > 4) {
                                    ((Timer) e.getSource()).stop();
                                    // Clear the selected items list and reset the amount
                                    selectedItems.clear();
                                    amount = 0.0;
                                    // Return to displayTestMain
                                    frame5.dispose();
                                    displayTestMain(vendingMachine);
                                }
                            }
                        });
                        timer.start();
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance for selected items. Please add more money or remove items.");
                    }
        
                    // Clear the selected items list and reset the amount
                    selectedItems.clear();
                    amount = 0.0;
                    // Return to displayTestMain
                    frame5.dispose();
                    displayTestMain(vendingMachine);
                }
            });
        
            frame5.add(doneButton);
        }
        else{ //Regular Vending Machine
    
        // Item buttons and quantity labels
        JButton[] itemButtons = new JButton[8]; // Array to hold item buttons
       
        for (int i = 0; i < vendingMachine.getItemSlots().size(); i++) {
            ItemSlot itemSlot = vendingMachine.getItemSlots().get(i);
            Item item = itemSlot.getItem();
            JButton itemButton = new JButton(item.getItemName() + " (P" + item.getItemPrice() + ")");
            JLabel quantityLabel = new JLabel("Quantity: " + itemSlot.getQuantity());
    
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (vendingMachine.getBalance() <= 0) {
                        JOptionPane.showMessageDialog(null, "No money inside the vending machine. Please insert money first.");
                        frame5.dispose();
                        displayTestMain(vendingMachine);
                        return;
                    }
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "Please insert money first.");
                    } else if (itemSlot.getQuantity() > 0) {
                        double price = item.getItemPrice();
                        double change = amount - price;
    
                        if (amount >= price && (vendingMachine.getBalance() >= change || vendingMachine.getBalance() == 0)) {
                            itemSlot.setQuantity(itemSlot.getQuantity() - 1);
                            quantityLabel.setText("Quantity: " + itemSlot.getQuantity());
    
                            // Calculate the change and update the balance
                            if (change > 0) {
                                // Check if the vending machine has sufficient change to give
                                if (vendingMachine.getBalance() >= change) {
                                    vendingMachine.setBalance(vendingMachine.getBalance() - change); // Deduct the change from the vending machine's balance
                                    JOptionPane.showMessageDialog(null, "Change: P" + String.format("%.2f", change));
                                } else {
                                    // Insufficient change in the vending machine, return the amount
                                    vendingMachine.setBalance(vendingMachine.getBalance() - amount); // Deduct the amount from the vending machine's balance
                                    JOptionPane.showMessageDialog(null, "Insufficient change. Returning P" + String.format("%.2f", amount));
                                    amount = 0.0;
                                    frame5.dispose();
                                    displayTestMain(vendingMachine);
                                    return;
                                }
                            }
    
                            // Add the item to the selectedItems list for a special vending machine
                            if (vendingMachine instanceof SpecialVendingMachine) {
                                selectedItems.add(item);
                            }
    
                            if (!(vendingMachine instanceof SpecialVendingMachine)) {
                                JOptionPane.showMessageDialog(null, "Dispensing " + item.getItemName() + "!");
                                displayTestMain(vendingMachine);
                                frame5.dispose();
                            }
    
                            // Reset the amount after each item selection
                            amount = 0.0;
                        } else {
                            // Insufficient money for the item, return the amount
                            JOptionPane.showMessageDialog(null, "Insufficient money. Returning P" + String.format("%.2f", amount));
                            vendingMachine.setBalance(vendingMachine.getBalance() - amount);
                            amount = 0.0;
                            frame5.dispose();
                            displayTestMain(vendingMachine);
                        }
                    } else {
                        // Item out of stock
                        JOptionPane.showMessageDialog(null, "Item out of stock. Please choose another item.");
                    }
                }
            });
    
            itemButtons[i] = itemButton;
            frame5.add(itemButton);
            frame5.add(quantityLabel);
        }
    }
    
        insertMoneyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double money = Double.parseDouble(moneyField.getText());
                    if (money > 0) {
                        // Check if the inserted denomination is supported
                        if (money == 20 || money == 50 || money == 100 || money == 500) {
                            double newBalance = vendingMachine.getBalance() + amount + money;
                            // Check if the vending machine can accept the money (to check for sufficient change)
                            if (newBalance >= amount + money) {
                                amount += money; // Add the money to the user's total
                                vendingMachine.setBalance(newBalance); // Update the vending machine's balance with the new total balance
                                JOptionPane.showMessageDialog(null, "Money inserted. Please choose an item.");
                                moneyField.setText(""); // Clear the money input field after adding money
    
                                
                               
                                insertMoneyBtn.setEnabled(false); // Disable the "Insert Money" button
                            } else {
                                JOptionPane.showMessageDialog(null, "The vending machine cannot accept this money due to insufficient change. Please try another denomination.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid denomination. Please insert P20, P50, P100, or P500 denominations only.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid amount of money.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid amount.");
                }
            }
        });
    
       
        frame5.setVisible(true);
}
    

    /**
     * Displays the maintenance screen for the vending machine.
     * This screen requires the owner to enter a password to access maintenance features.
     * The owner can perform restocking, set new item prices, collect payments, and replenish money.
     */
    public void displayMaintenance(){
         boolean isPasswordCorrect = askForPassword();
        if (isPasswordCorrect) {
            JOptionPane.showMessageDialog(frame1, "Welcome again owner, you may proceed.", "Maintenance", JOptionPane.PLAIN_MESSAGE);
            this.frame2 = new JFrame("Maintenance Features");
        } else {
            // Password is incorrect, show a message dialog
            JOptionPane.showMessageDialog(frame1, "Incorrect password. Cannot proceed.", "Password Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        JFrame frame6 = new JFrame("Maintenance Features");
        frame6.setSize(400, 400);
        frame6.setLayout(new GridLayout(6, 1, 5, 5)); 

        JLabel label6 = new JLabel("Choose a transaction of Vending Machine");
        label6.setHorizontalAlignment(JLabel.CENTER);
        
        JButton restockBtn = new JButton("Restock Items");
        restockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display a pop-up to get the restock quantities
                JTextField[] quantityFields = new JTextField[8];
                JPanel inputPanel = new JPanel(new GridLayout(0, 2));
    
                for (int i = 0; i < 8; i++) {
                    ItemSlot itemSlot = vendingMachine.getItemSlots().get(i);
                    Item item = itemSlot.getItem();
                    int currentQuantity = itemSlot.getQuantity();
                    int remainingCapacity = 10 - currentQuantity;
    
                    inputPanel.add(new JLabel(item.getItemName() + " Space: " + remainingCapacity ));
                    quantityFields[i] = new JTextField();
                    inputPanel.add(quantityFields[i]);
                }
    
                int result = JOptionPane.showConfirmDialog(frame6, inputPanel, "Restock Items",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
                // If the user clicks "OK," process the restock quantities
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        // Parse the restock quantities and update item quantities
                        for (int i = 0; i < 8; i++) {
                            int restockQuantity = Integer.parseInt(quantityFields[i].getText());
                            if (restockQuantity >= 0) {
                                ItemSlot itemSlot = vendingMachine.getItemSlots().get(i);
                                int currentQuantity = itemSlot.getQuantity();
                                int remainingCapacity = 10 - currentQuantity;
                                int restockedItems = Math.min(restockQuantity, remainingCapacity);
                                itemSlot.setQuantity(currentQuantity + restockedItems);
                            } else {
                                JOptionPane.showMessageDialog(frame6, "Invalid quantity. Restock quantity must be non-negative.");
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(frame6, "Items have been restocked successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame6, "Invalid input. Please enter a valid quantity for each item.");
                    }
                }
            }
        });


        JButton setPriceBtn = new JButton("Set New Prices");
        setPriceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a panel to collect the new prices
                JPanel pricePanel = new JPanel(new GridLayout(8, 2, 5, 5));
                JLabel[] itemLabels = new JLabel[8];
                JTextField[] priceFields = new JTextField[8];
    
                for (int i = 0; i < 8; i++) {
                    ItemSlot itemSlot = vendingMachine.getItemSlots().get(i);
                    Item item = itemSlot.getItem();
                    itemLabels[i] = new JLabel(item.getItemName() + ": ");
                    priceFields[i] = new JTextField(Double.toString(item.getItemPrice()));
                    pricePanel.add(itemLabels[i]);
                    pricePanel.add(priceFields[i]);
                }
    
                // Show the panel to the user
                int result = JOptionPane.showConfirmDialog(frame6, pricePanel, "Set New Prices", JOptionPane.OK_CANCEL_OPTION);
    
                if (result == JOptionPane.OK_OPTION) {
                    // Validate and update the prices
                    boolean allPricesValid = true;
                    for (int i = 0; i < 8; i++) {
                        try {
                            double newPrice = Double.parseDouble(priceFields[i].getText());
                            if (newPrice <= 0) {
                                allPricesValid = false;
                                JOptionPane.showMessageDialog(null, "Item price in Slot " + (i + 1) + " cannot be 0 or less.");
                                break;
                            }
                            // Update the item price
                            ItemSlot itemSlot = vendingMachine.getItemSlots().get(i);
                            Item item = itemSlot.getItem();
                            item.setItemPrice(newPrice);
                        } catch (NumberFormatException ex) {
                            allPricesValid = false;
                            JOptionPane.showMessageDialog(null, "Invalid input in Slot " + (i + 1) + ". Please enter a valid number.");
                            break;
                        }
                    }
                    if (allPricesValid) {
                        JOptionPane.showMessageDialog(frame6, "New prices updated successfully.");
                    }
                }
            }
        });


        JButton collectPayBtn = new JButton("Collect Payemnt");
        collectPayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalPayment = vendingMachine.getBalance();
                if (totalPayment > 0) {
                    vendingMachine.setBalance(0.0);
                    JOptionPane.showMessageDialog(frame6, "Collected payment: P" + String.format("%.2f", totalPayment));
                } else {
                    JOptionPane.showMessageDialog(frame6, "No payments to collect.");
                }
            }
        });


        JButton replenishBtn = new JButton("Replenish Money");
       replenishBtn.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
        JPanel replenishPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        JLabel[] denominationLabels = new JLabel[6];
        JTextField[] quantityFields = new JTextField[6];

        String[] denominations = {"1 Peso coins", "5 Peso coins", "10 Peso coins", "20 Peso bills", "50 Peso bills", "100 Peso bills"};

        for (int i = 0; i < 6; i++) {
            denominationLabels[i] = new JLabel(denominations[i]);
            quantityFields[i] = new JTextField();
            replenishPanel.add(denominationLabels[i]);
            replenishPanel.add(quantityFields[i]);
        }

        // Show the panel to the user
        int result = JOptionPane.showConfirmDialog(frame6, replenishPanel, "Replenish Money", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Parse the replenish quantities and update the balance
                double replenishAmount = 0.0;
                for (int i = 0; i < 6; i++) {
                    int quantity = Integer.parseInt(quantityFields[i].getText());
                    if (quantity < 0) {
                        JOptionPane.showMessageDialog(frame6, "Invalid quantity for " + denominations[i] + ". Quantity must be non-negative.");
                        return;
                    }
                    // Update replenishAmount based on the denomination
                    switch (i) {
                        case 0: // 1 Peso coins
                            replenishAmount += quantity * 1.0;
                            break;
                        case 1: // 5 Peso coins
                            replenishAmount += quantity * 5.0;
                            break;
                        case 2: // 10 Peso coins
                            replenishAmount += quantity * 10.0;
                            break;
                        case 3: // 20 Peso bills
                            replenishAmount += quantity * 20.0;
                            break;
                        case 4: // 50 Peso bills
                            replenishAmount += quantity * 50.0;
                            break;
                        case 5: // 100 Peso bills
                            replenishAmount += quantity * 100.0;
                            break;
                        default:
                            break;
                    }
                }

                double newBalance = vendingMachine.getBalance() + replenishAmount;
                vendingMachine.setBalance(newBalance);
                JOptionPane.showMessageDialog(frame6, "Money replenished successfully. New balance: P" + String.format("%.2f", newBalance));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame6, "Invalid input. Please enter valid quantities for each denomination.");
            }
        }
    }
});
        
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame6.dispose();
                displayTestMain(vendingMachine);
            }
        });
    
        frame6.add(label6);
        frame6.add(restockBtn);
        frame6.add(setPriceBtn);
        frame6.add(collectPayBtn);
        frame6.add(replenishBtn);
        frame6.add(exitBtn);

        frame6.setVisible(true);
    }
    
     /**
     * Asks the user to enter a password for certain privileged actions, such as accessing maintenance features.
     * @return {@code true} if the entered password matches the owner's password, {@code false} otherwise.
     */
    private boolean askForPassword() {
        String ownerPassword = "1"; // Replace this with the actual owner's password

        // Show an input dialog to get the user's input
        String userInput = JOptionPane.showInputDialog(frame1, "Enter the password:");

        // Compare the user input with the owner's password
        return userInput != null && userInput.equals(ownerPassword);
    }

}