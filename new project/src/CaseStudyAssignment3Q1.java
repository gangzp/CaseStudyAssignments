/*
 * Created On : 10/04/2020
 * Created By : Gangadharan P
 * Scenarios  : Automate the below mentioned site for booking and reviewing it
                1)	https://www.makemytrip.com/
 * Browsers   : Chrome / Firefox
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CaseStudyAssignment3Q1 {
    public WebDriver driver;

    @BeforeTest
    public void setUp() throws InterruptedException {
        //To run in chrome browser
        String path = System.getProperty("user.dir");
        System.out.println(path);
        System.setProperty("webdriver.chrome.driver",path+"\\src\\resources\\chromedriver.exe");
        driver=new ChromeDriver();

        //To run in firefox browser
        /*System.setProperty("webdriver.chrome.driver",path+"\\src\\resources\\geckodriver.exe");
        driver=new FirefoxDriver();*/
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(3000);
        driver.manage().window().maximize();
    }

    @Test
    public void assignment3Q1() throws InterruptedException {
        String from = "COK";
        String to = "BLR";
        driver.findElement(By.xpath("//*[@id='fromCity']")).click();
        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys(from);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[contains(text(),'" + from + "')]/../div/p")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys(to);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[contains(text(),'" + to + "')]/../div/p[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//label[@for='departure']/span")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
        driver.findElement(By.xpath("(//div[@class='dateInnerCell']/p[contains(text(),'20')])[1]")).click();
        String depart = driver.findElement(By.xpath("(//input[contains(@id,'departure')])")).getText();
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//a[contains(@class,'SearchBtn')]")).click();
        Thread.sleep(3000);
        WebElement lowerPrice = driver.findElement(By.xpath("//span[@class='down sort-arrow']"));
        if (lowerPrice.isDisplayed() == false) {
            lowerPrice.click();
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//button[contains(@class,'ViewFareBtn')])[1]")).click();
        driver.findElement(By.xpath("(//button[contains(text(),'Book Now')])[1]")).click();
        String verifyFrom = driver.findElement(By.xpath("//span[contains(text(),'" + from + "')]")).getText();
        String verifyTo = driver.findElement(By.xpath("//span[contains(text(),'" + to + "')]")).getText();
        String departVerify = driver.findElement(By.xpath("(//div[contains(@class,'fli-time-section')])[1]/p[1]")).getText();
        if (departVerify.contains(depart) && verifyFrom.contains(from) && verifyTo.contains(to))
            System.out.println("Verification successful");
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
    }
}
