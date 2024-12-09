import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelTest {

    static EdgeDriver driver;
    private static By firstName = By.xpath("//*[@id=\"RESULT_TextField-1\"]");
    private static By lastName = By.xpath("//*[@id=\"RESULT_TextField-2\"]");
    private static By phone = By.xpath("//*[@id=\"RESULT_TextField-3\"]");
    private static By country = By.xpath("//*[@id=\"RESULT_TextField-4\"]");
    private static By city = By.xpath("//*[@id=\"RESULT_TextField-5\"]");
    private static By email = By.xpath("//*[@id=\"RESULT_TextField-6\"]");
    private static By gender = By.xpath("//*[@id=\"q26\"]/table/tbody/tr[1]/td/label");
    private static By work = By.xpath("//*[@id=\"q15\"]/table/tbody/tr[2]/td");
    private static By work1 = By.xpath("//*[@id=\"q15\"]/table/tbody/tr[6]/td");
    private static By timecon = By.id("RESULT_RadioButton-9");
    private static By img = By.xpath("//*[@id='RESULT_FileUpload-10']");
    private static By header = By.xpath("//*[@id=\"q19\"]/div/h1");
    private static By loginButton = By.xpath("//*[@id=\"FSsubmit\"]");


    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "lib/msedgedriver.exe");
        DesiredCapabilities caps = DesiredCapabilities.edge();
        caps.setCapability("nativeEvents", false);
        caps.setCapability("ignoreProtectedNodeSettings", true);
        driver = new EdgeDriver(caps);
        driver.get("https://fs2.formsite.com/meherpavan/form2/index.html?1537702596407");

        driver.findElement(firstName).sendKeys("Gautam");
        driver.findElement(lastName).sendKeys("Garg");
        driver.findElement(phone).sendKeys("6162636465");
        driver.findElement(country).sendKeys("BHARAT");
        driver.findElement(city).sendKeys("Bengaluru");
        driver.findElement(email).sendKeys("gautam@gmail.com");
        driver.findElement(gender).click();
        driver.findElement(work).click();
        driver.findElement(work1).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(timecon));
        Select drpTime = new Select(driver.findElement(timecon));
        drpTime.selectByVisibleText("Evening");
        driver.findElement(img).sendKeys("C:\\Users\\gauta\\Downloads\\Aug-2022 (1).pdf");
        driver.findElement(loginButton).click();

        String expected = "Volunteer Sign Up";
        String actual = driver.findElement(header).getText();
        Assert.assertEquals("Header text does not match!", expected, actual);
//        driver.quit();
    }
}
