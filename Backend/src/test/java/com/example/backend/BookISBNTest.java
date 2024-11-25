package com.example.backend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class BookISBNTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver
//        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust wait time as needed
    }

    @AfterMethod
    public void pauseBetweenTests() throws InterruptedException {
        // Adding a gap of 2 seconds between tests
        Thread.sleep(5000); // Time is in milliseconds
    }

    @Test
    public void adminLoginAndAddBook() throws InterruptedException {
        driver.get("http://localhost:5173/");

        // Admin login
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-btn"));

        emailField.sendKeys("admin@gmail.com");
        passwordField.sendKeys("123");
        loginButton.click();

        pauseBetweenTests();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "http://localhost:5173/dashboard", "Admin should be redirected to the dashboard");

        // Navigate to Add Book page
        driver.get("http://localhost:5173/addbook");

        pauseBetweenTests();

        // Fill in the Add Book form
        WebElement titleField = driver.findElement(By.id("title"));
        WebElement authorField = driver.findElement(By.id("author"));
        WebElement isbnField = driver.findElement(By.id("isbn"));
        WebElement categoryField = driver.findElement(By.id("category"));
        WebElement stockField = driver.findElement(By.id("stock"));
        WebElement availableField = driver.findElement(By.id("available"));
//        WebElement imageUpload = driver.findElement(By.id("file-upload"));
        WebElement addBookButton = driver.findElement(By.className("book-btn"));

        titleField.sendKeys("Sample Book Title");
        authorField.sendKeys("Author Name");
        isbnField.sendKeys("ISBN146779");
        categoryField.sendKeys("Fiction");
        stockField.sendKeys("5");
        availableField.sendKeys("5");

        // Upload image (example: book.jpg should be in the project directory)
//        imageUpload.sendKeys("/path/to/your/book.jpg");

        pauseBetweenTests();

        // Submit the form
        addBookButton.click();

        // Verify success (example toast or confirmation message)
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("custom-toast")));
        String successMessage = successToast.getText();
        System.out.println(successMessage);
        Assert.assertTrue(successMessage.contains("Book sucessfully added"), "Success message should be displayed for adding the book");


        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("logout-btn")));
        logoutButton.click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

