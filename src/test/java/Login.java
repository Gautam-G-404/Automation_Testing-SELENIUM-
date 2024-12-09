
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login{

    static EdgeDriver driver;

    public static void main(String[] args) {
        // Set up Edge WebDriver
        System.setProperty("webdriver.edge.driver", "lib/msedgedriver.exe");
        DesiredCapabilities caps = DesiredCapabilities.edge();
        caps.setCapability("nativeEvents", false);
        caps.setCapability("ignoreProtectedNodeSettings", true);
        driver = new EdgeDriver(caps);

        try {
            driver.get("https://demo.nopcommerce.com/login");

            driver.manage().window().maximize();
            By emailField = By.id("Email");
            By passwordField = By.id("Password");//P6a@eWJgpeexFR
            By loginButton = By.xpath("//button[contains(text(),'Log in')]");

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            driver.findElement(emailField).sendKeys("gautam@example.com");

            driver.findElement(passwordField).sendKeys("Password123");

            driver.findElement(loginButton).click();

            By logoutLink = By.xpath("//a[contains(text(),'Log out')]");
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