package com.example.backend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class IssueBookTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Adjust wait time as needed
    }

    @AfterMethod
    public void pauseBetweenTests() throws InterruptedException {
        // Adding a gap of 2 seconds between tests
        Thread.sleep(10000); // Time is in milliseconds
    }

    @Test
    public void adminLoginAndIssueBook() throws InterruptedException {
        driver.get("http://localhost:5173/");

        // Admin login
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-btn"));

        emailField.sendKeys("saloni@gmail.com");
        passwordField.sendKeys("12345678");
        loginButton.click();

        pauseBetweenTests();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "http://localhost:5173/dashboard", "Admin should be redirected to the dashboard");

        // Navigate to Issue Book page
        driver.get("http://localhost:5173/issuebook");

        pauseBetweenTests();

        // Fill in the Issue Book form
        WebElement mainBookIdField = driver.findElement(By.id("mainbookid"));
        WebElement bookIdField = driver.findElement(By.id("bookid"));
        WebElement userIdField = driver.findElement(By.id("username"));
        WebElement issueDateField = driver.findElement(By.id("issuedate"));
        WebElement dueDateField = driver.findElement(By.id("duedate"));
        WebElement issueBookButton = driver.findElement(By.className("issue-btn"));

        mainBookIdField.sendKeys("1"); // Example Main Book ID
        bookIdField.sendKeys("1");     // Example Book ID
        userIdField.sendKeys("saloni");     // Example User ID
        issueDateField.sendKeys("25-11-2024"); // Example Issue Date
        dueDateField.sendKeys("27-11-2024");   // Example Due Date

        pauseBetweenTests();

        // Submit the form
        issueBookButton.click();

        // Verify success (example toast or confirmation message)
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("custom-toast")));
        String successMessage = successToast.getText();
        System.out.println(successMessage);
        Assert.assertTrue(successMessage.contains("Book Successfully issue"), "Success message should be displayed for issuing the book");

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

