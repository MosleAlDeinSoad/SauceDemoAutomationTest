package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SauceDemoPurchaseTest2 {
    public static void main(String[] args) {
        

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        
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
        if (cartItems.size() == 3) {
            System.out.println("Added 3 items to the cart.");
        } else {
            System.out.println("Error: Expected 3 items in the cart, but found " + cartItems.size());
        }

       
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
        
        if (successMessage.getText().toLowerCase().equals(expectedSuccess.toLowerCase())) {
            System.out.println("Testcase2: Purchase successful!");
        } else {
            System.out.println("Purchase failed. Expected success message not found.");
        }

        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();

        driver.quit();
    }
}
