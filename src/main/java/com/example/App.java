package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType; // Required for new tabs
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class App {
    public static void main(String[] args) {
        FirefoxOptions options = new FirefoxOptions();
        
        // Ensure we are NOT in headless mode so you can see the new tab
        // options.addArguments("-headless"); 

        options.setBinary("/home/vboxuser/Desktop/lab1/firefox/firefox");
        options.addArguments("--headless=new");       
        options.addArguments("--no-sandbox");         
        options.addArguments("--disable-dev-shm-usage"); 
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new FirefoxDriver(options);

        try {
            // 1. Login Process
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            System.out.println("Login successful. Opening new tab...");

            // 2. Open a NEW TAB and switch focus to it
            driver.switchTo().newWindow(WindowType.TAB);

            // 3. Navigate to a different site in the new tab
            driver.get("https://www.google.com");
            
            System.out.println("New tab opened. Script finished, but browser will stay open.");

        } catch (Exception e) {
            e.printStackTrace();
            // Even if it errors, we want to keep it open for debugging
        }

        // 4. REMOVED driver.quit() 
        // Note: The Java program will finish executing, 
        // but the Firefox process will remain active on your desktop.
    }
}
