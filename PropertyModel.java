// Author and ST number: Viyashna Mathapursad - ST10497709


// Section A of Assignment 2




public class PropertyModel {


    // Variables declared

    public String PropertyId;
    public String PropertyAddress;
    public double PropertyRentalAmount;
    public String AgentName;

    // Constructor

    public PropertyModel(String id, String address, double rentalAmount, String agentName) {
        this.PropertyId = id;
        this.PropertyAddress = address;
        this.PropertyRentalAmount = rentalAmount;
        this.AgentName = agentName;
    }



    public PropertyModel() {
        // Default constructor
    }

    @Override
    public String toString() {
        return "PROPERTY ID: " + PropertyId +
                "\nPROPERTY ADDRESS: " + PropertyAddress +
                "\nPROPERTY RENTAL AMOUNT: R" + PropertyRentalAmount +
                "\nPROPERTY AGENT: " + AgentName;
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Property {
    // Use an ArrayList to store property models in memory
    private ArrayList<PropertyModel> propertyList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Method to validate and get a valid rental amount
    public double PropertyAmountValidation() {
        double rentalAmount = 0.0;
        boolean isValid = false;
        String input;

        while (!isValid) {
            System.out.print("Enter the property rental price per month: ");
            input = scanner.nextLine();

            try {
                // 1. Check if input is a valid number
                rentalAmount = Double.parseDouble(input);

                // 2. Check if amount is greater than or equal to R 1 500
                if (rentalAmount >= 1500.0) {
                    isValid = true;
                } else {
                    System.out.println("You have entered an invalid amount!!!"); // Invalid amount < R 1 500
                    System.out.print("Please re-enter the property rental amount >> ");
                }
            } catch (NumberFormatException e) {
                System.out.println("You have entered an invalid amount!!!"); // Invalid non-number input
                System.out.print("Please re-enter the property rental amount >> ");
            }
        }
        return rentalAmount;
    }

    // Method to enter new property details
    public void EnterProperty() {
        System.out.println("\nENTER A NEW PROPERTY FOR RENTAL");
        System.out.print("Enter the property id: ");
        String id = scanner.nextLine();

        System.out.print("Enter the property address: ");
        String address = scanner.nextLine();

        // Validate rental amount using the dedicated method
        double rentalAmount = PropertyAmountValidation();

        System.out.print("Enter the property agent: ");
        String agent = scanner.nextLine();

        // Save the new property model to memory
        PropertyModel newProperty = new PropertyModel(id, address, rentalAmount, agent);
        propertyList.add(newProperty);

        System.out.println("New property processed successfully!!!\n");
    }

    // Utility method to find a property by ID (useful for other methods)
    private PropertyModel findPropertyById(String id) {
        for (PropertyModel property : propertyList) {
            if (property.PropertyId.equals(id)) {
                return property;
            }
        }
        return null;
    }

    // Method to search for a property
    public PropertyModel SearchProperty() {
        System.out.print("\nEnter the property id to search: ");
        String searchId = scanner.nextLine();
        PropertyModel foundProperty = findPropertyById(searchId);

        if (foundProperty != null) {
            System.out.println(foundProperty.toString());
            System.out.println("\nEnter (1) to launch menu or any other key to exit");
        } else {
            System.out.println("Rental property with property Id: " + searchId + " was not found!");
            System.out.println("\nEnter (1) to launch menu or any other key to exit");
        }
        return foundProperty; // Used for unit testing
    }

    // Method to update any property
    public void UpdateProperty() {
        System.out.print("\nEnter the property id to update: ");
        String updateId = scanner.nextLine();
        PropertyModel propertyToUpdate = findPropertyById(updateId);

        if (propertyToUpdate != null) {
            System.out.print("Enter the property address: ");
            propertyToUpdate.PropertyAddress = scanner.nextLine();

            // Re-validate rental amount
            propertyToUpdate.PropertyRentalAmount = PropertyAmountValidation();

            System.out.print("Enter the property agent: ");
            propertyToUpdate.AgentName = scanner.nextLine();

            System.out.println("\nProperty " + updateId + " updated successfully!");
        } else {
            System.out.println("Property with ID " + updateId + " not found for update.");
        }
    }

    // Method to delete a property
    public boolean DeleteProperty() {
        System.out.print("\nEnter the property id to delete: ");
        String deleteId = scanner.nextLine();
        PropertyModel propertyToDelete = findPropertyById(deleteId);

        if (propertyToDelete != null) {
            System.out.println("Are you sure you want to delete property " + deleteId + " from the system? Yes (y) to delete.");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("y")) {
                propertyList.remove(propertyToDelete);
                System.out.println("Property with Property Id: " + deleteId + " WAS deleted!");
                return true;
            }
            System.out.println("Deletion cancelled.");
        } else {
            System.out.println("Property with ID " + deleteId + " not found.");
        }
        return false;
    }

    // Method to print the property rental report
    public void PropertyRentalReport() {
        System.out.println("\n--- PROPERTY RENTAL REPORT ---");
        if (propertyList.isEmpty()) {
            System.out.println("No properties currently in the system.");
            return;
        }

        for (int i = 0; i < propertyList.size(); i++) {
            System.out.println("\nProperty " + (i + 1));
            System.out.println("------------------------------------");
            System.out.println(propertyList.get(i).toString());
        }
        System.out.println("\nEnter (1) to launch menu or any other key to exit");
    }


    public void ExitPropertyApplication() {
        System.out.println("Exiting Application. Goodbye!");
        System.exit(0);
    }
}

import java.util.Scanner;

public class RunApplication {

    public static void main(String[] args) {
        Scanner mainScanner = new Scanner(System.in);
        Property propertyManager = new Property();
        String choice;

        // Launch screen / home page
        System.out.println("PROPERTY RENTALS - 2025");
        System.out.println("***************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        choice = mainScanner.nextLine();

        if (!choice.equals("1")) {
            propertyManager.ExitPropertyApplication();
        }

        // Main menu loop function
        while (true) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Enter new property.");
            System.out.println("(2) Search for property.");
            System.out.println("(3) Update property");
            System.out.println("(4) Delete a property.");
            System.out.println("(5) Print property report 2025");
            System.out.println("(6) Exit Application.");

            System.out.print("Select an option (1-6): ");
            choice = mainScanner.nextLine();

            switch (choice) {
                case "1":
                    propertyManager.EnterProperty();
                    break;
                case "2":
                    propertyManager.SearchProperty();
                    break;
                case "3":
                    propertyManager.UpdateProperty();
                    break;
                case "4":
                    propertyManager.DeleteProperty();
                    break;
                case "5":
                    propertyManager.PropertyRentalReport();
                    break;
                case "6":
                    propertyManager.ExitPropertyApplication();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            // Re-prompt to continue or exit after each operation
            System.out.print("Enter (1) to launch menu or any other key to exit: ");
            choice = mainScanner.nextLine();
            if (!choice.equals("1")) {
                propertyManager.ExitPropertyApplication();
            }
        }
    }
}

// ChatGPT was used to help format my code as well as to improve my comments.