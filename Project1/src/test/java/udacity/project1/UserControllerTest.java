package udacity.project1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeEach
    public void beforeEach() {
        // Set up WebDriver
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

    @Test
    public void testUnauthorizedAccess() {
        driver.get("http://localhost:"+port+"/home");
        WebElement loginForm = driver.findElement(By.id("login-button"));
        assertEquals("Login", loginForm.getText());

        driver.get("http://localhost:"+port+"/signup");
        String signup = driver.getTitle();
        assertEquals("Sign Up", signup);
    }

    @Test
    public void testSignupAndLogin() {
    	
        driver.get("http://localhost:"+port+"/signup");
        WebElement firstnameInput = driver.findElement(By.id("inputFirstName"));
        WebElement lastnameInput = driver.findElement(By.id("inputLastName"));
        WebElement usernameInput = driver.findElement(By.id("inputUsername"));
        WebElement passwordInput = driver.findElement(By.id("inputPassword"));
        firstnameInput.sendKeys("user");
        lastnameInput.sendKeys("test");
        usernameInput.sendKeys("userTest");
        passwordInput.sendKeys("123");

        WebElement signupButton = driver.findElement(By.id("buttonSignUp"));
        signupButton.click();


        driver.get("http://localhost:"+port+"/login");

        usernameInput = driver.findElement(By.id("inputUsername"));
        passwordInput = driver.findElement(By.id("inputPassword"));
        usernameInput.sendKeys("userTest");
        passwordInput.sendKeys("123");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        String homePageTitle = driver.getTitle();
        assertEquals("Home", homePageTitle);

        WebElement logoutButton = driver.findElement(By.id("logout-button"));
        logoutButton.click();

        String loginForm = driver.getTitle();
        assertEquals("Login", loginForm);

        driver.get("http://localhost:"+port+"/home");
        String loginTitle = driver.getTitle();
        assertEquals("Login", loginTitle);
    }
}