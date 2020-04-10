/*
 * Created On : 10/04/2020
 * Created By : Gangadharan P
 * Scenarios  : please select the Drag me to my target and drop on the target and verify the text.
 * Browsers   : Chrome / Firefox
 */


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

public class CaseStudyAssignment1Q3 {
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
        driver.get("https://demoqa.com/droppable/");
        Thread.sleep(3000);
        driver.manage().window().maximize();
    }

    @Test
    public void assignment1Q3() throws InterruptedException {
        WebElement draggable = driver.findElement(By.xpath("//*[@id='draggable']"));
        WebElement droppable = driver.findElement(By.xpath("//*[@id='droppable']"));
        Actions action = new Actions(driver);
        action.dragAndDrop(draggable, droppable).build().perform();
        System.out.println("Confirmation Message : " + droppable.getText());
        Thread.sleep(2000);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}