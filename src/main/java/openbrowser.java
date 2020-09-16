import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class openbrowser {


    public static void main(String[] args) {



        System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Desktop\\QaAssignment_Iswarya\\src\\main\\lib\\chromedriver.exe");

        WebDriver driver =new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
 // commnt
        //comment

    }
}
