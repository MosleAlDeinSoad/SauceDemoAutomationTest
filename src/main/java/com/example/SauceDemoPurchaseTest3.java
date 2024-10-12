package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SauceDemoPurchaseTest3 {
    public static void main(String[] args) {
        

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        
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

        
        WebElement totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_total_label")));
        System.out.println("Total Price: " + totalPrice.getText());

        
        WebElement checkoutProductName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));
        if (checkoutProductName.getText().equals(productName)) {
            System.out.println("Product name verified in checkout: " + productName);
        } else {
            System.out.println("Error: Product name mismatch!");
        }

        
        driver.findElement(By.id("finish")).click();
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        String expectedSuccess = "THANK YOU FOR YOUR ORDER!";
        
        if (successMessage.getText().toLowerCase().equals(expectedSuccess.toLowerCase())) {
            System.out.println("Purchase successful!");
        } else {
            System.out.println("Purchase failed. Expected success message not found.");
        }

        
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();

        
        driver.quit();
    }
}
