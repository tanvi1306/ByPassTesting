package com.example.backend;



import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
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

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust wait time as needed
    }

    @AfterMethod
    public void pauseBetweenTests() throws InterruptedException {
        // Adding a gap of 2 seconds between tests
        Thread.sleep(2000); // Time is in milliseconds
    }

    @Test
    public void validAdminLogin() throws InterruptedException {
        driver.get("http://localhost:5173/");

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-btn"));

        emailField.sendKeys("admin@gmail.com");
        passwordField.sendKeys("123");
        loginButton.click();

        pauseBetweenTests();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "http://localhost:5173/dashboard", "Admin should be redirected to the dashboard");

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("logout-btn")));
        logoutButton.click();
    }

    @Test
    public void validUserLogin() {
        driver.get("http://localhost:5173/");

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-btn"));

        emailField.sendKeys("saloni@gmail.com");
        passwordField.sendKeys("12345678");
        loginButton.click();

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "http://localhost:5173/", "User should be redirected to the dashboard");

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("logout-btn")));
        logoutButton.click();
    }

    @Test
    public void invalidLogin() {
        driver.get("http://localhost:5173/");

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-btn"));

        emailField.sendKeys("invalid@example.com");
        passwordField.sendKeys("WrongPassword");
        loginButton.click();

        // Wait for potential error message
        WebElement errorToast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("custom-toast")));
        String errorMessage = errorToast.getText();
        Assert.assertTrue(errorMessage.contains("Error in Login"), "Error message should be displayed for invalid login");

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "http://localhost:5173/", "User should remain on the login page");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
