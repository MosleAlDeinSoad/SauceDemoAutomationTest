package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SauceDemoTest1 {
    public static void main(String[] args) {
      

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("locked_out_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        
        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String expectedError = "Epic sadface: Sorry, this user has been locked out.";
        String actualError = errorMessage.getText();

        if (actualError.equals(expectedError)) {
            System.out.println("Test Case 1 Passed!");
        } else {
            System.out.println("Test Case 1 Failed!");
        }

        driver.quit();
    }
}
