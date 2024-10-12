package com.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SauceDemoTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void test1() {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("locked_out_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String expectedError = "Epic sadface: Sorry, this user has been locked out.";
        Assertions.assertEquals(expectedError, errorMessage.getText(), "Test Case 1 Failed!");
        System.out.println("Test Case 1 Passed!");
    }

    @Test
    public void test2() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link"))).click();

        
        List<WebElement> addButtons = driver.findElements(By.className("btn_inventory"));
        for (int i = 0; i < 3; i++) {
            addButtons.get(i).click();
        }

        
        driver.findElement(By.className("shopping_cart_link")).click();
        List<WebElement> cartItems = driver.findElements(By.className("inventory_item_name"));
        Assertions.assertEquals(3, cartItems.size(), "Error: Expected 3 items in the cart.");

        
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        
        WebElement totalPrice = driver.findElement(By.className("summary_total_label"));
        System.out.println("Total Price: " + totalPrice.getText());

        
        driver.findElement(By.id("finish")).click();
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        String expectedSuccess = "THANK YOU FOR YOUR ORDER!";
        Assertions.assertEquals(expectedSuccess.toLowerCase(), successMessage.getText().toLowerCase(), "Test Case 2 Failed!");
        System.out.println("Test Case 2 Passed!");
    }

    @Test
    public void test3() {
        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link"))).click();

        
        WebElement sortingDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.className("product_sort_container")));
        sortingDropdown.click();
        WebElement zToADropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='za']"))); // Z to A
        zToADropdown.click();

        
        List<WebElement> addButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("btn_inventory")));
        addButtons.get(0).click(); 

        
        driver.findElement(By.className("shopping_cart_link")).click();
        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));
        String productName = cartItem.getText();
        System.out.println("Product in the cart: " + productName);

        
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        
        WebElement checkoutProductName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));
        Assertions.assertEquals(productName, checkoutProductName.getText(), "Error: Product name mismatch!");

       
        WebElement totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_total_label")));
        System.out.println("Total Price: " + totalPrice.getText());

        
        driver.findElement(By.id("finish")).click();
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        String expectedSuccess = "THANK YOU FOR YOUR ORDER!";
        Assertions.assertEquals(expectedSuccess.toLowerCase(), successMessage.getText().toLowerCase(), "Test Case 3 Failed!");
        System.out.println("Test Case 3 Passed!");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}