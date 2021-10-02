import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        //options.addArguments("headless");  //Не выкидывает окно браузера (экономит оперативку)
        driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().setSize(new Dimension(1280, 768));
        //driver.manage().addCookie(new Cookie("SSID", "dfbdbdbdfb"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void validZipCodeShouldBeAccepted(){
        //Open https://www.sharelane.com/cgi-bin/register.py
        //Enter 11111 into Zip Code
        //<input type="text" name="zip_code" value="">
        //<input type="submit" value="Continue">
        //Click continue
        //Check that 'Register' button exists

        //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value = Continue]")).click();
        boolean isPageOpened = driver.findElement(By.cssSelector("[value = Register]")).isDisplayed();
        assertTrue(isPageOpened,"Sign Up page was not opened");
    }

    @Test
    public void zipCodeShouldBeRequired() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.cssSelector("[value = Continue]")).click();
        driver.findElement(By.cssSelector("[class = error_message]")).getText();
        String error = driver.findElement(By.cssSelector("[class = error_massage]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "Error");
        //<span class="error_message">Oops, error on page. ZIP code should have 5 digits</span>
    }

    @Test
    public void validZipCodeShouldBeRegister(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value = Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class = error_massage]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "Error");
    }

    @Test
    public void FirstName(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value = Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("alex");
        driver.findElement(By.name("last_name")).sendKeys("alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = Register]")).click();
    }

    @Test
    public void Password1(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value = Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("alex");
        driver.findElement(By.name("last_name")).sendKeys("alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.com");
        driver.findElement(By.name("password1")).sendKeys("123");
        driver.findElement(By.name("password2")).sendKeys("123");
        driver.findElement(By.cssSelector("[value = Register]")).click();
        boolean isPageOpened = driver.findElement(By.cssSelector("[value = Register]")).isDisplayed();
        assertTrue(isPageOpened,"Oops, error on page. Some of your fields have invalid data or email was previously used");
    }

    @Test
    public void Password2(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value = Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("alex");
        driver.findElement(By.name("last_name")).sendKeys("alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("54321");
        driver.findElement(By.cssSelector("[value = Register]")).click();
    }

    @Test
    public void Search(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value = Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("alex");
        driver.findElement(By.name("last_name")).sendKeys("alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = Register]")).click();
        driver.findElement(By.name("keyword")).sendKeys("Mark Twain");
        driver.findElement(By.cssSelector("[value = Search]")).click();
        boolean isPageOpened = driver.findElement(By.cssSelector("[value = Register]")).isDisplayed();
        assertTrue(isPageOpened,"Nothing is found :(");
    }

    @Test
    public void BookPageImages(){
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.get("https://www.sharelane.com/images/product_10_small.jpg\n");
    }

    @Test
    public void BookPageWWW(){
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=10\n");
    }

    @Test
    public void ShoppingCart(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value = Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("alex");
        driver.findElement(By.name("last_name")).sendKeys("alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = Register]")).click();
        driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
        boolean isPageOpened = driver.findElement(By.cssSelector("[value = Register]")).isDisplayed();
        assertTrue(isPageOpened,"Oops, error. You must log in");
    }

    @Test
    public void HomePage(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value = Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("alex");
        driver.findElement(By.name("last_name")).sendKeys("alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value = Register]")).click();
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
    }
}
