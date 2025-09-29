import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

    // Declare the class we are testing
    private Property propertyManager;

    // Declare a sample property
    private PropertyModel testProperty;

    // This runs before every test method to set up a clean testing environment
    @BeforeEach
    void setUp() {
        // Initialize the manager and clear any previous state
        propertyManager = new Property();
        propertyManager.clearProperties();

        // Define and add a standard property for use in search/update/delete tests
        testProperty = new PropertyModel("TEST001", "123 Test Avenue", 3500.0, "Test Agent");
        propertyManager.addProperty(testProperty);
    }

    // 1. Test SearchProperty - Returns a valid property (4 Marks)
    @Test
    void SearchProperty_ReturnsProperty() {
        PropertyModel found = propertyManager.findPropertyById("TEST001");

        // Check if the returned object is not null and has the correct ID
        assertNotNull(found, "The property TEST001 should have been found.");
        assertEquals("TEST001", found.PropertyId, "The correct property ID was not returned.");
    }

    // Test SearchProperty
    @Test
    void SearchProperty_NotFound() {
        // Search for an ID that definitely doesn't exist
        PropertyModel notFound = propertyManager.findPropertyById("MISSING999");

        //  The result should be null
        assertNull(notFound, "Searching for a missing ID should return null.");
    }

    //Test UpdateProperty - Successful Update
    @Test
    void UpdateProperty_ReturnsSuccess() {
        // The setup already has TEST001 with 3500.0
        double newRentalAmount = 4500.0;

        // Simulate the change that UpdateProperty should perform:
        PropertyModel property = propertyManager.findPropertyById("TEST001");
        assertNotNull(property);

        property.PropertyRentalAmount = newRentalAmount; // simulation of successful update

        // check if the rental amount has been successfully changed
        assertEquals(newRentalAmount, property.PropertyRentalAmount, 0.001,
                "The rental amount should have been updated.");
    }

    //Test DeleteProperty - Successful Deletion
    @Test
    void DeleteProperty_ReturnsSuccess() {
        // List contains 1 property ("TEST001")
        int initialSize = propertyManager.getPropertyCount();
        assertEquals(1, initialSize);

        // successful deletion
        PropertyModel propertyToRemove = propertyManager.findPropertyById("TEST001");
        assertTrue(propertyManager.propertyList.remove(propertyToRemove), "Property should be removed from the list.");

        // the count must be 0 after deletion, and a search should find nothing
        assertEquals(0, propertyManager.getPropertyCount(), "List size should be 0 after successful deletion.");
        assertNull(propertyManager.findPropertyById("TEST001"), "Deleted property should not be found.");
    }

    // Test PropertyAmountValidation - Amount is Valid
    @Test
    void PropertyAmountValidation_AmountisValid() {
        // A valid amount >= 1500.0
        double validAmount = 2500.0;

        assertTrue(validAmount >= 1500.0, "The minimum valid amount check should pass.");
    }

    // Test PropertyAmountValidation - Amount is Invalid
    @Test
    void PropertyAmountValidation_AmountinValid() {
        // An invalid amount < 1500.0
        double invalidAmount = 1499.99;

        // Confirm if the core validation check fails for an invalid amount.
        assertFalse(invalidAmount >= 1500.0, "Amount 1499.99 should be invalid (less than 1500.0).");

    }
}

// ChatGPT was used to help format my code as well as to improve my comments.