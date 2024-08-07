package com.xm.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DriverSetup {
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = DriverSetup.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
                System.out.println("Properties loaded: " + properties);
            } else {
                throw new IOException("Property file not found in the classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the WebDriver with specified width and height.
     *
     * @param width the width of the browser window
     * @param height the height of the browser window
     * @return WebDriver instance
     */
    public WebDriver setupDriver(int width, int height) {
        WebDriver driver = null;

        String browser = properties.getProperty("browser");
        String chromeDriverPath = properties.getProperty("chromeDriverPath");

        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-extensions");
            options.addArguments("--incognito");

            driver = new ChromeDriver(options);

            // Set the browser window size
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        return driver;
    }

    public static Properties getProperties() {
        return properties;
    }
}
