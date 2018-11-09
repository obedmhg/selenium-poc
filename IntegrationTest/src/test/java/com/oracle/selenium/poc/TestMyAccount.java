package com.oracle.selenium.poc;

import com.oracle.poc.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestMyAccount {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "D:\\shared\\selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        try {
            String baseUrl = "https://www.suburbia.com.mx";
            for (int i = 0; i < 1; i++) {
                driver.get(baseUrl + "/tienda/inicio");
                waitForPageLoaded();
                driver.findElement(By.linkText("Inicia sesión")).click();
                waitForPageLoaded();
                driver.findElement(By.cssSelector("input.input-material")).click();
                driver.findElement(By.cssSelector("input.input-material")).sendKeys("obedmhg@gmail.com");
                driver.findElement(By.cssSelector(".col-12:nth-child(2) .input-material")).sendKeys("obed3333");
                driver.findElement(By.cssSelector("button.btn-primary-sb.btn-full")).click();
                TestUtils.getScreenshot(driver, "login.jpg");
                assert driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div[1]/div/div[4]/a/span[1]")).getText().contains("Hola");

            }
        } catch (Exception e1) {
            e1.printStackTrace();
            assert false;
        }
    }


    @Test
    public void testBadLogin() {
        try {
            String baseUrl = "https://www.suburbia.com.mx";
            for (int i = 0; i < 1; i++) {
                driver.get(baseUrl + "/tienda/inicio");
                waitForPageLoaded();
                driver.findElement(By.linkText("Inicia sesión")).click();
                waitForPageLoaded();
                driver.findElement(By.cssSelector("input.input-material")).click();
                driver.findElement(By.cssSelector("input.input-material")).sendKeys("obed@test.com");
                driver.findElement(By.cssSelector(".col-12:nth-child(2) .input-material")).sendKeys("obed3333");
                driver.findElement(By.cssSelector("button.btn-primary-sb.btn-full")).click();
                TestUtils.getScreenshot(driver, "badLogin.jpg");
                assert driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div/div/div[2]/div/div[1]/div/div[2]/p")).getText().contains("Tu Correo Electrónico y/o Contraseña son incorrectos. Por favor intenta nuevamente");

            }
        } catch (Exception e1) {
            e1.printStackTrace();
            assert false;
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
