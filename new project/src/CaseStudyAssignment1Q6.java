/*
 * Created On : 10/04/2020
 * Created By : Gangadharan P
 * Scenarios  : Please Automate Rental Car Block.
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

public class CaseStudyAssignment1Q6 {
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
        driver.get("https://demoqa.com/controlgroup/");
        Thread.sleep(3000);
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    @Test
    public void assignment1Q6() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='car-type-button']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='ui-id-4']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@for='transmission-standard']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@for='insurance']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//*[@class='ui-spinner-input'])[1]")).sendKeys("5");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[contains(@class,'ui-button')])[1]")).click();
        Thread.sleep(2000);
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
    }
}
