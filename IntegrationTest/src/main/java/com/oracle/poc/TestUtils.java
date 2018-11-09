package com.oracle.poc;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class TestUtils {

    public static void getScreenshot(WebDriver driver, String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile,
                    new File("D:\\shared\\selenium\\selenium\\" + TestUtils.class.getName() + "\\" + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

