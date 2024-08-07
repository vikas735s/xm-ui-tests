package com.xm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResearchAndEducationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for the links on the Research and Education page
    private By economicCalendarLink = By.linkText("Economic Calendar");
    private By educationalVideosLink = By.linkText("Educational Videos");

    // Constructor to initialize WebDriver and WebDriverWait
    public ResearchAndEducationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    }

    /**
     * Clicks on the "Economic Calendar" link on the Research and Education page.
     * This method waits for the link to be clickable before performing the click action.
     */
    public void clickEconomicCalendar() {
        // Wait for the "Economic Calendar" link to be clickable and then click it
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(economicCalendarLink));
        link.click();
    }

    /**
     * Clicks on the "Educational Videos" link on the Research and Education page.
     * This method waits for the link to be clickable before performing the click action.
     */
    public void clickEducationalVideos() {
        // Wait for the "Educational Videos" link to be clickable and then click it
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(educationalVideosLink));
        link.click();
    }
}
