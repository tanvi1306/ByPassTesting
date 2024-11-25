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

public class UserPasswordTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust wait time as needed
    }

    @AfterMethod
    public void pauseBetweenTests() throws InterruptedException {
        // Adding a gap of 2 seconds between tests
        Thread.sleep(10000); // Time is in milliseconds
    }

    @Test
    public void adminLoginAndRegisterUser() throws InterruptedException {
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

        driver.get("http://localhost:5173/userregister");

        pauseBetweenTests();

        // Fill in user registration form
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement userEmailField = driver.findElement(By.id("email"));
        WebElement userPasswordField = driver.findElement(By.id("password"));
        WebElement registerButton = driver.findElement(By.id("reg-btn"));

        usernameField.sendKeys("tanviii");
        userEmailField.sendKeys("tanvi23@example.com");
        userPasswordField.sendKeys("12345678");
        registerButton.click();

        // Verify successful registration
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("custom-toast")));
        String successMessage = successToast.getText();
        System.out.println(successMessage);
        Assert.assertTrue(successMessage.contains("User register successfully"), "Success message should be displayed for user registration");

        // Verify that the user is redirected to the login page
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "http://localhost:5173/userregister", "After registration, user should be redirected to the login page");

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


