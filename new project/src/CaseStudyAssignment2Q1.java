/*
 * Created On : 10/04/2020
 * Created By : Gangadharan P
 * Scenarios  : Automate the below mentioned site for Registration and Login
                    1)	Olay website
 * Browsers   : Chrome / Firefox
 */


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileReader;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CaseStudyAssignment2Q1 {
    public static String username, password, emailGenerator, pswd;
    public static WebDriver driver;

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
        driver.get("https://www.olay.co.uk/en-gb");
        Thread.sleep(3000);
        driver.manage().window().maximize();
        Thread.sleep(1000);
        try {
            WebElement ele=driver.findElement(By.xpath("//a[@class='decline event_online_tool']"));
            if(ele.isDisplayed()) {
                ele.click();
                System.out.println("Online Tool Popup Closed");
            }
        }
        catch(Exception e)
        {
            System.out.println("Popup is not displayed");
        }
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        pswd = "Test12345";
        emailGenerator = "test" + randomInt + "@mail.com";
    }

      /*//register(emailGenerator, pswd);
        jsonParameter();
        //login(username,password);
        forgotPassword("test14@mail.com");*/

    @Test
    public void userGeneration() throws InterruptedException {
        register(emailGenerator,pswd);
    }
    public static void register(String userName, String pswd) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//a[@class='event_profile_register'])[1]")).click();
        Thread.sleep(1000);
        WebElement emailTextBx = driver.findElement(By.xpath("//input[@data-key='emailAddress']"));
        emailTextBx.sendKeys(userName);
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys(pswd);
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys(pswd);
        Select day = new Select(driver.findElement(By.xpath("//select[@data-key='birthdate[dateselect_day]']")));
        day.selectByVisibleText("15");
        Thread.sleep(1000);
        Select month = new Select(driver.findElement(By.xpath("//select[@data-key='birthdate[dateselect_month]']")));
        month.selectByVisibleText("5");
        Thread.sleep(1000);
        Select year = new Select(driver.findElement(By.xpath("//select[@data-key='birthdate[dateselect_year]']")));
        year.selectByVisibleText("1991");
        Thread.sleep(1000);
        WebElement commonError = driver.findElement(By.xpath("//span[@class='pc_error-message pc_error-message2']"));
        WebElement emailError = driver.findElement(By.xpath("(//span[@class='error-message'])[1]"));
        WebElement pswdError = driver.findElement(By.xpath("(//span[@class='error-message'])[2]"));
        WebElement cnfrmPswdError = driver.findElement(By.xpath("(//span[@class='error-message'])[3]"));
        WebElement dayError = driver.findElement(By.xpath("(//span[@class='error-message'])[4]"));
        WebElement monthError = driver.findElement(By.xpath("(//span[@class='error-message'])[5]"));
        WebElement yearError = driver.findElement(By.xpath("(//span[@class='error-message'])[6]"));
        if (commonError.isDisplayed()==true || emailError.isDisplayed()==true || pswdError.isDisplayed()==true || cnfrmPswdError.isDisplayed()==true || dayError.isDisplayed()==true || monthError.isDisplayed()==true || yearError.isDisplayed()==true)
        {
            System.out.println("Please enter the correct details to proceed with registration");
        }
        else
        {
            WebElement submitButton = driver.findElement(By.xpath("//div[@class='button-link']/input"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", submitButton);
            submitButton.click();
            Thread.sleep(5000);

            //If you want to fill the details in optional field page
            driver.findElement(By.xpath("//input[@data-key='firstName']")).sendKeys("Test");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@data-key='lastName']")).sendKeys("User");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@data-key='addressStreet1']")).sendKeys("9053 Albert Road");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@data-key='addressCity']")).sendKeys("CHELMSFORD");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@data-key='addressPostalCode']")).sendKeys("123456");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='pc_button']/input")).click();
            Thread.sleep(5000);
            WebElement accountCreation = driver.findElement(By.xpath("//h1[@id='phdesktopbody_0_Header']"));
            System.out.println(accountCreation.getText());

            //If you want to skip the optional field page
            /*driver.findElement(By.xpath("//a[@title='Skip This Step']")).click();
            Thread.sleep(3000);
            logout();*/
            Thread.sleep(2000);
        }
    }

    @Test(priority = 1)
    public void loginCheck() throws InterruptedException {
        login(emailGenerator,pswd);
    }
    public static void login(String emailGenerator, String pwd) throws InterruptedException {
        WebElement login = driver.findElement(By.xpath("//a[@class='event_profile_login']"));
        if(login.isDisplayed()==true)
        {
            login.click();
        }
        driver.findElement(By.xpath("//input[@data-key='signInEmailAddress']")).sendKeys(emailGenerator);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@data-key='currentPassword']")).sendKeys(pwd);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='SIGN IN']")).click();
        Thread.sleep(1000);
        if((driver.getCurrentUrl()).contains("loginpage"))
        {
            WebElement invalidPswd = driver.findElement(By.xpath("//span[@id='phdesktopbody_0_Message']"));
            Thread.sleep(5000);
            if(invalidPswd.isDisplayed()==true)
                System.out.println(invalidPswd.getText());
        }
        else
            System.out.println("Login Successful");
    }

    @Test(priority = 2)
    public static void logout() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='logoutbtn']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@title='Logout']")).click();
        Thread.sleep(1000);
    }
   /* @Test(priority = 4)
    public static void verifyLogin(String userName, String pswd) throws InterruptedException {
        login(userName, pswd);
        Thread.sleep(3000);
        logout();
        Thread.sleep(2000);
        driver.quit();
    }*/

    @Test(priority = 4)
    public void fgtPswd() throws InterruptedException {
        forgotPassword(emailGenerator);
    }
        public static void forgotPassword(String emailGenerator) throws InterruptedException {
        WebElement login = driver.findElement(By.xpath("//a[@class='event_profile_login']"));
        if(login.isDisplayed()==true)
        {
            login.click();
        }
        WebElement forgotPswd = driver.findElement(By.xpath("//a[@class='forgotpwd']"));
        forgotPswd.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@data-key='signInEmailAddress']")).sendKeys(emailGenerator);
        driver.findElement(By.xpath("//div[@class='pc_btn']/input")).click();
        WebElement confirmationText = driver.findElement(By.xpath("//div[@class='pc_reset-pwd']/div"));
        System.out.println("Confirmation Message : "+confirmationText.getText());
    }

    @Test(priority = 5)
    public static void jsonParameter() throws IOException, ParseException, InterruptedException {
        String path = "src/resources";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(absolutePath+"\\data.json");
        //Read JSON file
        Object obj = jsonParser.parse(reader);
        JSONArray usersList = (JSONArray) obj;
        System.out.println("Users List-> "+usersList); //This prints the entire json file
        for(int i=0;i<usersList.size();i++)
        {
            JSONObject users = (JSONObject) usersList.get(i);
            System.out.println("Users -> "+users);//This prints every block - one json object
            JSONObject user = (JSONObject) users.get("users");
            System.out.println("User -> "+user); //This prints each data in the block
            username = (String) user.get("username");
            password = (String) user.get("password");
            System.out.println("The username in JSON is: "+username);
            System.out.println("The password in JSON is: "+password);
        }
        register(username,password);
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
    }
}
    /*public void ReadData() throws IOException {
        File file = new File("D:/TestData.xlsx");
        FileInputStream iFile = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(iFile);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        int rowCount = sheet.getLastRowNum();
        System.out.println("the no of rows are : " + rowCount);
        for (int row=1; row<=rowCount; row++)
        {
            String emailId = sheet.getRow(row).getCell(0).getStringCellValue();
            String password= sheet.getRow(row).getCell(1).getStringCellValue();
            System.out.println(emailId + " , " + password);
        }
        iFile.close();
    }*/