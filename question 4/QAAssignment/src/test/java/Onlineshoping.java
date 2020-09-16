import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Onlineshoping {

static WebDriver driver;


    @BeforeMethod
    public void setUp() {


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\IdeaProjects\\QAAssignment\\src\\main\\lib\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
    }


    @Test
    public void testshoping() throws InterruptedException {



        driver.findElement(By.linkText("Women")).click();

        driver.findElement(By.id("layered_category_4")).click();
        driver.findElement(By.id("layered_id_attribute_group_3")).click();
        driver.findElement(By.id("layered_id_attribute_group_8")).click();
        String parent=driver.getWindowHandle();
        Actions action = new Actions(driver);

        WebElement we = driver.findElement(By.xpath("//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line first-item-of-tablet-line first-item-of-mobile-line']"));
        action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//div[@id='center_column']/ul/li/div/div[2]/div[2]/a[2]/span"))).click().build().perform();

        driver.findElement(By.xpath("//button[@name='Submit']")).click();


        String child=driver.getWindowHandle();
        driver.switchTo().window(child);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[contains(text(),'Proceed to checkout')]")).click();
        Thread.sleep(2000);

        // print price in console
        String price=driver.findElement(By.xpath("//span[@id='total_price']")).getText();
        System.out.println("Your price is "+price);

        List<WebElement> checkouts=driver.findElements(By.xpath("//*[contains(text(),'Proceed to checkout')]"));


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        checkouts.get(1).click();
        driver.findElement(By.id("email")).sendKeys("soysa08.t@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("soysa");
        driver.findElement(By.id("SubmitLogin")).click();



        String errormsg=driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();


        String verifymessage="There is 1 error\n" +
                "Authentication failed.";

        Assert.assertEquals(errormsg,verifymessage,"Your username password is wrong please check and re try ");

    }

    private static WebElement waitForElement(By locator,int timeout)
    {
        WebElement element=new WebDriverWait(driver,timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
        return element;
    }
}
