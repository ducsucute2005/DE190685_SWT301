package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    static RegistrationPage registrationPage;

    @BeforeAll
    static void setupPage() {
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("Register successfully with valid info")
    void testValidRegistration() {
        registrationPage.open();
        registrationPage.fillForm("John", "Doe", "john@example.com", "0987654321");
        registrationPage.submit();
        assertTrue(registrationPage.isSubmitted(), "Form not submitted!");
    }

    @Test
    @DisplayName("Submit failed when empty form")
    void testEmptyForm() {
        registrationPage.open();
        registrationPage.submit();
        assertTrue(!registrationPage.isSubmitted(), "Form should not submit!");
    }
}
