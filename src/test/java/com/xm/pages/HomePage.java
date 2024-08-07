package com.xm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    // Locators for elements on the homepage
    private By researchAndEducationLink = By.partialLinkText("RESEARCH & EDUCATION");
    private By acceptCookiesButton = By.xpath("//button[text()='ACCEPT ALL']");

    // Constructor to initialize the WebDriver
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Opens the homepage using the specified URL and handles the cookie consent if present.
     *
     * @param url the URL of the homepage to be opened
     */
    public void openHomePage(String url) {
        driver.get(url);
        handleCookieConsent();
    }

    /**
     * Retrieves the title of the current page.
     *
     * @return the title of the current page
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Clicks on the "RESEARCH & EDUCATION" link on the homepage.
     */
    public void clickResearchAndEducation() {
        WebElement link = driver.findElement(researchAndEducationLink);
        link.click();
    }

    /**
     * Handles the cookie consent popup by clicking the "ACCEPT ALL" button if it appears.
     */
    public void handleCookieConsent() {
        try {
            // Wait for the "ACCEPT ALL" button to be clickable and then click it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
            WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
            acceptAllButton.click();
        } catch (Exception e) {
            // Handle the case where the cookie consent popup is not found or already handled
            Reporter.log("Cookie consent popup not found or already handled.", true);
        }
    }
}
