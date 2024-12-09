import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterTest {

    static WebDriver driver;

    public static void main(String[] args) {
        // Set up Edge WebDriver
        System.setProperty("webdriver.edge.driver", "lib/msedgedriver.exe");
        DesiredCapabilities caps = DesiredCapabilities.edge();
        caps.setCapability("nativeEvents", false);
        caps.setCapability("ignoreProtectedNodeSettings", true);
        driver = new EdgeDriver(caps);

        try {
            // Open the nopCommerce registration page
            driver.get("https://demo.nopcommerce.com/register");

            // Maximize the browser window
            driver.manage().window().maximize();

            // Define the registration elements
            By genderMale = By.id("gender-male");
            By firstNameField = By.id("FirstName");
            By lastNameField = By.id("LastName");
            By dayDropdown = By.name("DateOfBirthDay");
            By monthDropdown = By.name("DateOfBirthMonth");
            By yearDropdown = By.name("DateOfBirthYear");
            By emailField = By.id("Email");
            By passwordField = By.id("Password");
            By confirmPasswordField = By.id("ConfirmPassword");
            By registerButton = By.id("register-button");


            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(genderMale));
            driver.findElement(genderMale).click();


            driver.findElement(firstNameField).sendKeys("Gautam");
            driver.findElement(lastNameField).sendKeys("Garg");


            new Select(driver.findElement(dayDropdown)).selectByVisibleText("10");
            new Select(driver.findElement(monthDropdown)).selectByVisibleText("May");
            new Select(driver.findElement(yearDropdown)).selectByVisibleText("2000");


            driver.findElement(emailField).sendKeys("gautam@example.com");
            driver.findElement(passwordField).sendKeys("Password123");
            driver.findElement(confirmPasswordField).sendKeys("Password123");


            driver.findElement(registerButton).click();


            By registrationResult = By.className("result");
            wait.until(ExpectedConditions.visibilityOfElementLocated(registrationResult));


            String resultText = driver.findElement(registrationResult).getText();
            Assert.assertTrue("Registration failed!", resultText.contains("Your registration completed"));

            System.out.println("Registration successful!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
