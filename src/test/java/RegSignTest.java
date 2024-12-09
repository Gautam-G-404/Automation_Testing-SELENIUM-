import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegSignTest {

    static WebDriver driver;

    public static void main(String[] args) {

        System.setProperty("webdriver.edge.driver", "lib/msedgedriver.exe");
        DesiredCapabilities caps = DesiredCapabilities.edge();
        caps.setCapability("nativeEvents", false);
        caps.setCapability("ignoreProtectedNodeSettings", true);
        driver = new EdgeDriver(caps);

        try {
            driver.get("https://demo.nopcommerce.com/register");
            driver.manage().window().maximize();

            By genderMale = By.id("gender-female");
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
            driver.findElement(lastNameField).sendKeys("garg");

            new Select(driver.findElement(dayDropdown)).selectByVisibleText("10");
            new Select(driver.findElement(monthDropdown)).selectByVisibleText("May");
            new Select(driver.findElement(yearDropdown)).selectByVisibleText("2000");

            String email = "Gautam@gmail.com";
            String password = "Password123";
            driver.findElement(emailField).sendKeys(email);
            driver.findElement(passwordField).sendKeys(password);
            driver.findElement(confirmPasswordField).sendKeys(password);

            driver.findElement(registerButton).click();

            By registrationResult = By.className("result");
            wait.until(ExpectedConditions.visibilityOfElementLocated(registrationResult));

            String resultText = driver.findElement(registrationResult).getText();
            Assert.assertTrue("Registration failed!", resultText.contains("Your registration completed"));

            System.out.println("Registration successful!");

            By logoutLink = By.xpath("//a[contains(text(),'Log out')]");
            driver.findElement(logoutLink).click();

            driver.get("https://demo.nopcommerce.com/login");

            By loginEmailField = By.id("Email");
            By loginPasswordField = By.id("Password");
            By loginButton = By.xpath("//button[contains(text(),'Log in')]");

            wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailField));
            driver.findElement(loginEmailField).sendKeys(email);

            driver.findElement(loginPasswordField).sendKeys(password);

            driver.findElement(loginButton).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));

            Assert.assertTrue("Login failed!", driver.findElement(logoutLink).isDisplayed());

            System.out.println("Login successful!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            driver.quit();
        }
    }
}
