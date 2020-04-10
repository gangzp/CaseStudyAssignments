/*
 * Created On : 10/04/2020
 * Created By : Gangadharan P
 * Scenarios  : Please fill this contact form using CSS and Xpath.
                a) Enter First Name
                b) Enter Last Name
                c) Enter Country
                d) Enter Subject
                e) Click on Submit
                f) Try to find these links with partial text
                Google Link
                Google Link is here
                g) Open this above links with new Tab
 * Browsers   : Chrome / Firefox
 */


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

public class CaseStudyAssignment1Q2 {
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
        driver.get("https://demoqa.com/html-contact-form/");
        Thread.sleep(3000);
        driver.manage().window().maximize();
    }

    @Test
            public void assignment1Q2() throws InterruptedException {
        String firstName = "Gangadharan", lastName = "Panguzhiyil", country = "India";
        String subject = "Submitting the form with my personal details";
        driver.findElement(By.xpath("//input[@class='firstname']")).sendKeys(firstName);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='lname']")).sendKeys(lastName);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='country']")).sendKeys(country);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='subject']")).sendKeys(subject);
        Thread.sleep(1000);
        WebElement link = driver.findElement(By.partialLinkText("Google"));
        String keyString = Keys.CONTROL + Keys.SHIFT.toString() + Keys.ENTER.toString();
        link.sendKeys(keyString);
        Thread.sleep(3000);
        String currentHandle = driver.getWindowHandle();
        Set<String> a = driver.getWindowHandles();
        for (String actual : a) {
            if (!actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(actual);
                System.out.println("First link found : " + driver.getTitle());
                driver.close();
                driver.switchTo().window(currentHandle);
            }
        }
        Thread.sleep(3000);
        WebElement link1 = driver.findElement(By.partialLinkText("Link is here"));
        String keyString1 = Keys.CONTROL + Keys.SHIFT.toString() + Keys.ENTER.toString();
        link1.sendKeys(keyString);
        Thread.sleep(3000);
        String currentHandle1 = driver.getWindowHandle();
        Set<String> b = driver.getWindowHandles();
        for (String actual : b) {
            if (!actual.equalsIgnoreCase(currentHandle1)) {
                driver.switchTo().window(actual);
                System.out.println("Second link found : " + driver.getTitle());
                driver.close();
                driver.switchTo().window(currentHandle1);
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(3000);
        System.out.println("Form submission message" + driver.findElement(By.xpath("//h1[@class='page-title']")).getText());
        Thread.sleep(3000);
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
    }
}