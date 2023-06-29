
package com.data.hope.core.common;

import com.data.hope.core.base.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.net.URL;


@Slf4j
public class WebUtils {

    private static final Integer DEFAULT_TIMEOUT = 60;

    private static WebDriver createWebDriver() throws Exception {

        String driverPath = Application.getProperty("report.screenshot.webdriver-path");

        if (StringUtils.isEmpty(driverPath)) {
            Exceptions.msg("message.not.found.webdriver");
        }

        String driverType = Application.getProperty("report.screenshot.webdriver-type");

        switch (driverType) {
            case "CHROME":
                return createChromeWebDriver(driverPath);
            default:
                Exceptions.msg("message.unsupported.webdriver", driverType);
        }
        return null;
    }

    public static <T> T screenShot(String url, OutputType<T> outputType, int imageWidth) throws Exception {
        WebDriver webDriver = createWebDriver();
        webDriver.get(url);

        WebDriverWait wait = new WebDriverWait(webDriver, getTimeout());

        ExpectedCondition<WebElement> ConditionOfSign = ExpectedConditions.presenceOfElementLocated(By.id("headlessBrowserRenderSign"));
        ExpectedCondition<WebElement> ConditionOfWidth = ExpectedConditions.presenceOfElementLocated(By.id("width"));
        ExpectedCondition<WebElement> ConditionOfHeight = ExpectedConditions.presenceOfElementLocated(By.id("height"));
        wait.until(ExpectedConditions.and(ConditionOfSign, ConditionOfWidth, ConditionOfHeight));

        Double contentWidth = Double.parseDouble(webDriver.findElement(By.id("width")).getAttribute("value"));

        Double contentHeight = Double.parseDouble(webDriver.findElement(By.id("height")).getAttribute("value"));

        if (imageWidth != contentWidth) {
            // scale the window
            webDriver.manage().window().setSize(new Dimension(imageWidth, contentHeight.intValue()));
            Thread.sleep(1000);
        }
        // scale the window again
        contentWidth = Double.parseDouble(webDriver.findElement(By.id("width")).getAttribute("value"));
        contentHeight = Double.parseDouble(webDriver.findElement(By.id("height")).getAttribute("value"));
        webDriver.manage().window().setSize(new Dimension(contentWidth.intValue(), contentHeight.intValue()));
        Thread.sleep(1000);

        TakesScreenshot screenshot = (TakesScreenshot) webDriver;
        return screenshot.getScreenshotAs(outputType);
    }

    public static File screenShot2File(String url, String path, int imageWidth) throws Exception {

        File temp = screenShot(url, OutputType.FILE, imageWidth);
        path = FileUtils.concatPath(path, temp.getName());
        File file = new File(path);

        FileUtils.delete(file);

        FileUtils.mkdirParentIfNotExist(path);

        FileCopyUtils.copy(temp, file);

        FileUtils.delete(temp);

        return file;
    }

    public static Integer getTimeout() {
        return Integer.parseInt(Application.getProperty("report.screenshot.timeout-seconds", DEFAULT_TIMEOUT.toString()));
    }

    private static WebDriver createChromeWebDriver(String driverPath) throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("no-sandbox");
        options.addArguments("disable-gpu");
        options.addArguments("disable-features=NetworkService");
        options.addArguments("ignore-certificate-errors");
        options.addArguments("silent-launch");
        options.addArguments("disable-application-cache");
        options.addArguments("disable-web-security");
        options.addArguments("no-proxy-server");
        options.addArguments("disable-dev-shm-usage");

        if (isRemoteDriver(driverPath)) {
            return new RemoteWebDriver(new URL(driverPath), options);
        }

        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);

        return new ChromeDriver(options);
    }

    private static boolean isRemoteDriver(String driverPath) {
        return driverPath.startsWith("http");
    }
}
