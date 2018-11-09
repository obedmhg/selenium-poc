package com.oracle.selenium.poc;

import com.oracle.poc.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestBrowse {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "D:\\shared\\selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void browserToCategory() {
        try {
            String baseUrl = "https://www.suburbia.com.mx";
            for (int i = 0; i < 1; i++) {
                driver.get(baseUrl + "/tienda/inicio");
                Actions action = new Actions(driver);
                WebElement we = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div[1]/div[2]/div/div[1]/div/span[2]"));
                TestUtils.getScreenshot(driver, "category.jpg");
                action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//*[@id=\"desktopCategory0\"]/a/div[1]/label"))).click().build().perform();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void browserToProduct() {
        try {
            String baseUrl = "https://www.suburbia.com.mx";
            for (int i = 0; i < 1; i++) {
                driver.get(baseUrl + "/tienda/inicio");
                driver.get(baseUrl + "/tienda/nav?Ntt=blusa");
                waitForPageLoaded();
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[3]/div[2]/div/div[2]/div[1]/a/div[1]/img")).click();
                waitForPageLoaded();
                TestUtils.getScreenshot(driver, "pdp.jpg");
                assert driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[2]/div/div/div[2]/h5")).getText().contains("Blusa Contempo");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    @AfterTest
    public void endTest() {
        driver.quit();
    }

}
