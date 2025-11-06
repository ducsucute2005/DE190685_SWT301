package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By genderMale = By.xpath("//label[text()='Male']");
    private By mobile = By.id("userNumber");
    private By submitBtn = By.id("submit");
    private By successModal = By.id("example-modal-sizes-title-lg");

    public void open() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void fillForm(String fName, String lName, String mail, String phone) {
        type(firstName, fName);
        type(lastName, lName);
        type(email, mail);
        click(genderMale);
        type(mobile, phone);
    }

    public void submit() {
        // Ẩn footer/banner che nút Submit
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('footer').style.display='none';" +
                        "document.querySelector('#fixedban').style.display='none';"
        );

        // Cuộn tới nút Submit
        WebElement submitElement = waitForVisibility(submitBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitElement);

        // Click bằng JavaScript (chắc chắn không lỗi)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitElement);
    }

    public boolean isSubmitted() {
        return isElementVisible(successModal);
    }
}
