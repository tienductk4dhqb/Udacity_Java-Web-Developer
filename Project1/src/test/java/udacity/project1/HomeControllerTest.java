package udacity.project1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HomeControllerTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private final String HOST = "http://localhost:";
    @BeforeEach
    public void beforeEach() {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(HOST + port+"/signup");

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

        driver.get(HOST + port+"/login");

        usernameInput = driver.findElement(By.id("inputUsername"));
        passwordInput = driver.findElement(By.id("inputPassword"));
        usernameInput.sendKeys("userTest");
        passwordInput.sendKeys("123");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        String homePageTitle = driver.getTitle();
        assertEquals("Home", homePageTitle);

    }

    @AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

    @Test
    @Order(1)
    public void testCreateNote() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.id("nav-notes-tab")).click();

        String textTitle = "Test Note";
        String textDescription = "Test Note Description";
        // Create note and submit
        WebElement btnAddNote = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("button-add-note"))));
        btnAddNote.click();
        driver.findElement(By.id("note-title")).sendKeys(textTitle);
//        WebElement inputTitle = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("note-title"))));
//        inputTitle.sendKeys(textTitle);
        driver.findElement(By.id("note-description")).sendKeys(textDescription);
        WebElement noteSubmit = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("btn-note-submit"))));
        noteSubmit.click();

        driver.findElement(By.id("nav-notes-tab")).click();
        WebElement table = driver.findElement(By.id("nav-notes"));
        List<WebElement> noteRows = table.findElements(By.xpath("//tbody/tr"));
        int endIndex = noteRows.size() - 2;
        WebElement noteTitleElement = noteRows.get(endIndex).findElement(By.xpath("th[1]"));
        WebElement noteDescriptionElement = noteRows.get(endIndex).findElement(By.xpath("td[2]"));

        Thread.sleep(1000);
        String noteTitle = noteTitleElement.getText();
        String noteDescription = noteDescriptionElement.getText();

        assertEquals(textTitle, noteTitle);
        assertEquals(textDescription, noteDescription);
    }

    @Test
    @Order(2)
    public void testEditNote() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.id("nav-notes-tab")).click();
        WebElement table = driver.findElement(By.id("nav-notes"));
        List<WebElement> noteRows = table.findElements(By.xpath("//tbody/tr"));
        noteRows.get(0).findElement(By.tagName("button")).click();
        String expectedTitle = "Edit Note";
        String expectedDescription = "Edit Note Description";
        WebElement inputTitle = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("note-title"))));
        inputTitle.clear();
        inputTitle.sendKeys(expectedTitle);
        WebElement inputDescription = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("note-description"))));
        inputDescription.clear();
        inputDescription.sendKeys(expectedDescription);
        WebElement noteSubmit = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("btn-note-submit"))));
        noteSubmit.click();

        driver.findElement(By.id("nav-notes-tab")).click();

        table = driver.findElement(By.id("nav-notes"));
        noteRows = table.findElements(By.xpath("//tbody/tr"));

        WebElement noteTitleElement = noteRows.get(0).findElement(By.xpath("th[1]"));
        WebElement noteDescriptionElement = noteRows.get(0).findElement(By.xpath("td[2]"));
        Thread.sleep(1000);

        String noteTitle = noteTitleElement.getText();
        String noteDescription = noteDescriptionElement.getText();
        assertEquals(expectedTitle, noteTitle);
        assertEquals(expectedDescription, noteDescription);
    }

    @Test
    @Order(3)
    public void testDeleteNote() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.id("nav-notes-tab")).click();

        WebElement table = driver.findElement(By.id("nav-notes"));

        List<WebElement> noteRows = table.findElements(By.xpath("//tbody/tr"));

        int currentSize = noteRows.size();
        noteRows.get(0).findElement(By.tagName("a")).click();

        driver.findElement(By.id("nav-notes-tab")).click();

        table = driver.findElement(By.id("nav-notes"));
        noteRows = table.findElements(By.xpath("//tbody/tr"));
        int deletedSize = noteRows.size();

        assertEquals(currentSize-1, deletedSize);
    }

    @Test
    @Order(4)
    public void testCreateCredential() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.id("nav-credentials-tab")).click();

        final String expectedUrl = "http://localhost";
        final String expectedUsername = "test";
        final String expectedPassword = "password";
        WebElement btnAddCredential = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("button-add-credential"))));
        btnAddCredential.click();
        WebElement inputUrl = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-url"))));
        inputUrl.sendKeys(expectedUrl);
        WebElement inputUsername = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-username"))));
        inputUsername.sendKeys(expectedUsername);
        WebElement inputPassword = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-password"))));
        inputPassword.sendKeys(expectedPassword);
        WebElement credentialSubmit = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("btn-credential-submit"))));
        credentialSubmit.click();

        driver.findElement(By.id("nav-credentials-tab")).click();
        WebElement table = driver.findElement(By.id("nav-credentials"));
        List<WebElement> credentialRows = table.findElements(By.xpath("//tbody/tr"));

        int endIndex = credentialRows.size()-1;
        WebElement credentialURLElement = credentialRows.get(endIndex).findElement(By.xpath("th[1]"));
        WebElement credentialUsernameElement = credentialRows.get(endIndex).findElement(By.xpath("td[2]"));
        WebElement credentialPasswordElement = credentialRows.get(endIndex).findElement(By.xpath("td[3]"));

        Thread.sleep(1000);
        String url = credentialURLElement.getText();
        String username = credentialUsernameElement.getText();
        String password = credentialPasswordElement.getText();

        assertEquals(expectedUrl, url);
        assertEquals(expectedUsername, username);
        assertNotEquals(expectedPassword, password);
    }

    @Test
    @Order(5)
    public void testEditCredential() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.id("nav-credentials-tab")).click();

        String expectedUrl = "http://localhost1";
        String expectedPassword = "password";

        WebElement table = driver.findElement(By.id("nav-credentials"));

        List<WebElement> credentialRows = table.findElements(By.xpath("//tbody/tr"));
        int size = credentialRows.size();

        WebElement btnEdit = wait.until(ExpectedConditions.elementToBeClickable(credentialRows.get(size-1).findElement(By.tagName("button"))));
        btnEdit.click();

        WebElement inputUrl = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-url"))));
        inputUrl.clear();
        inputUrl.sendKeys(expectedUrl);
        WebElement inputPassword = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("credential-password"))));
        Thread.sleep(1000);
        String actualPassword = inputPassword.getAttribute("value");

        assertEquals(expectedPassword, actualPassword);
        WebElement credentialSubmit = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("btn-credential-submit"))));
        credentialSubmit.click();

        driver.findElement(By.id("nav-credentials-tab")).click();
        table = driver.findElement(By.id("nav-credentials"));
        credentialRows = table.findElements(By.xpath("//tbody/tr"));
        WebElement credentialURLElement = credentialRows.get(size-1).findElement(By.xpath("th[1]"));

        Thread.sleep(1000);
        String url = credentialURLElement.getText();

        assertEquals(expectedUrl, url);
    }

    @Test
    @Order(6)
    public void testDeleteCredential() {
    	
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.id("nav-credentials-tab")).click();
        WebElement table = driver.findElement(By.id("nav-credentials"));
        List<WebElement> credentialRows = table.findElements(By.xpath("//tbody/tr"));
        int currentSize = credentialRows.size();
        WebElement deletedBtn = wait.until(ExpectedConditions.elementToBeClickable(credentialRows.get(currentSize-1).findElement(By.tagName("a"))));
        deletedBtn.click();

        driver.findElement(By.id("nav-credentials-tab")).click();

        table = driver.findElement(By.id("nav-credentials"));
        credentialRows = table.findElements(By.xpath("//tbody/tr"));
        int deletedSize = credentialRows.size();

        assertEquals(currentSize-1, deletedSize);
    }
}