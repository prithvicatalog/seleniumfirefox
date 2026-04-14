package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class App {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
        

        // Headless Firefox for Jenkins
        FirefoxOptions options = new FirefoxOptions();

 
options.addArguments("--headless");

//  VERY IMPORTANT for VM/Jenkins
options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage");

// disable GPU (important sometimes)
options.addArguments("--disable-gpu");

WebDriver driver = new FirefoxDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions actions = new Actions(driver);

        try {
            //  1. SauceDemo Login
            driver.get("https://www.saucedemo.com/");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                    .sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            System.out.println("SauceDemo login successful ");

            //  2. Automation Exercise (new tab)
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://automationexercise.com/products");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")))
                    .sendKeys("Men Tshirt");
            driver.findElement(By.id("submit_search")).click();

            WebElement product = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-product-id='2']"))
            );

            product.click();

            WebElement viewCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("#cartModal a[href='/view_cart']"))
            );
            viewCart.click();

            System.out.println("Automation Exercise product added to cart ");

            // 3. Practice Test Automation (new tab)
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://practicetestautomation.com/practice-test-login/");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                    .sendKeys("student");
            driver.findElement(By.id("password")).sendKeys("Password123");
            driver.findElement(By.id("submit")).click();

            System.out.println("Practice Test Automation login successful ");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit(); 
        }
    }
}
