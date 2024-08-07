package com.xm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;

public class EconomicCalendarPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By iframe_date = By.xpath(".//*[@title='iframe']");
    private By slider_loc = By.xpath("//mat-slider[@role='slider']");
    private By date_on_ui_loc = By.xpath("//span[contains(@class, 'tc-economic-calendar-item-header-left-title')]");
    //private By slider_filter_name_loc = By.xpath("//span[contains(@class, 'tc-finalval-tmz')]//div[contains(@class, 'ng-star-inserted')]");


    public EconomicCalendarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
    }


    /**
     * Sets the slider value to today's date and retrieves the date displayed on the UI.
     * This method performs the following steps:
     * 1. Waits for the iframe containing the slider to be visible and switches to it.
     * 2. Waits for the slider element to be visible.
     * 3. Executes JavaScript to change the slider value and adjust its styles.
     * 4. Waits for the element displaying the date on the UI to be visible.
     * 5. Retrieves the date text from the UI and logs it.
     * 6. Switches back to the default content from the iframe.
     * 7. Returns the fetched date.
     *
     * @return the date displayed on the UI after setting the slider value
     */
    public String setSliderValue_today() {
        // Wait for the iframe containing the slider to be visible and switch to it
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(iframe_date));
        driver.switchTo().frame(iframe);

        // Wait for the slider element to be visible
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(slider_loc));

        // Cast the driver to JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Execute JavaScript to change the slider value and styles
        js.executeScript(
                "arguments[0].setAttribute('aria-valuenow', '1');" + // Set the 'aria-valuenow' attribute to '1'
                        "arguments[0].setAttribute('aria-valuetext', '1');" + // Set the 'aria-valuetext' attribute to '1'
                        "arguments[0].querySelector('.mat-slider-track-background').style.transform = 'translateX(0px) scale3d(0.833333, 1, 1)';" + // Adjust the slider background track style
                        "arguments[0].querySelector('.mat-slider-track-fill').style.transform = 'translateX(0px) scale3d(0.166667, 1, 1)';" + // Adjust the slider fill track style
                        "arguments[0].querySelector('.mat-slider-thumb-container').style.transform = 'translateX(-83.3333%)';" + // Adjust the slider thumb container style
                        "arguments[0].dispatchEvent(new Event('input'));" + // Dispatch 'input' event to update the slider value
                        "arguments[0].dispatchEvent(new Event('change'));" + // Dispatch 'change' event to confirm the change
                        "arguments[0].dispatchEvent(new Event('blur'));" , // Dispatch 'blur' event to trigger any blur-related logic
                slider
        );

        // Wait for the element displaying the date on the UI to be visible
        WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(date_on_ui_loc));

        // Retrieve the date text from the UI and trim any surrounding whitespace
        String fetchedDate = dateElement.getText().trim();

        // Log the date set on the UI
        Reporter.log("Date set on UI : " + fetchedDate, true);

        // Switch back to the default content from the iframe
        driver.switchTo().defaultContent();

        // Return the fetched date
        return fetchedDate;
    }

    //Need to modify and make it reusable ie parameterized using value passed by user and set slider
    public  String  setSliderValue_tomorrow() {
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@title='iframe']")));
        driver.switchTo().frame(iframe);
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-slider[@role='slider']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Execute JavaScript to change the slider value
        js.executeScript(
                "arguments[0].setAttribute('aria-valuenow', '3');" +
                        "arguments[0].setAttribute('aria-valuetext', '3');" +
                        "arguments[0].querySelector('.mat-slider-track-background').style.transform = 'translateX(0px) scale3d(0.5, 1, 1)';" +
                        "arguments[0].querySelector('.mat-slider-track-fill').style.transform = 'translateX(0px) scale3d(0.5, 1, 1)';" +
                        "arguments[0].querySelector('.mat-slider-thumb-container').style.transform = 'translateX(-50%)';" +
                        "arguments[0].dispatchEvent(new Event('input'));"
                , slider);
        WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'tc-economic-calendar-item-header-left-title')]")));
        String fetchedDate = dateElement.getText().trim();
        System.out.println("Fetched Date: " + fetchedDate);
        driver.switchTo().defaultContent();
        return fetchedDate;
    }

    // TODO: Refactor this method to make it reusable and parameterized using value passed by the user
    // Steps to achieve:
    // 1. Create a method named setSliderValue that accepts an integer parameter for the slider value.
    // 2. Reuse the logic from setSliderValue_today and setSliderValue_tomorrow methods, replacing hardcoded values with the parameter.
    // 3. Ensure the JavaScript execution dynamically adjusts the slider based on the passed parameter.
    // 4. Call this new method from setSliderValue_today and setSliderValue_tomorrow with appropriate values.


}


