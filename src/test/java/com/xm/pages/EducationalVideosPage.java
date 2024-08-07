package com.xm.pages;

import com.xm.utils.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;

public class EducationalVideosPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for various elements on the page
    private By lession_Intro_to_Markets_Loc = By.xpath(".//*[contains(text(), 'Intro to the Markets')]");
    private By lession_Intro_to_Markets_Video_Loc = By.partialLinkText("Lesson 1.1");
    private By video_iframe = By.xpath(".//*[@class='sproutvideo-player']");
    private By videoElement = By.xpath(".//*[@class = 'player-video-holder']");
    private By videoPlayButton_Loc = By.xpath(".//*[@class = 'player-big-play-button']");
    private By videoprogressTime_loc = By.xpath(".//*[@class='player-progress-time']");

    // Constructor to initialize WebDriver and WebDriverWait
    public EducationalVideosPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
    }

    /**
     * This method opens a lesson, plays a video, and returns the time played in seconds.
     * It performs the following steps:
     * 1. Clicks on the lesson link using JavaScript.
     * 2. Clicks on the video link to open the video.
     * 3. Switches to the video iframe.
     * 4. Clicks the play button to start the video.
     * 5. Waits for the specified amount of time to let the video play.
     * 6. Retrieves the time played from the video progress element using JavaScript.
     * 7. Converts the retrieved time to seconds and returns it.
     *
     * @param sec the amount of time to play the video in seconds
     * @return the time played in seconds
     * @throws Exception if any error occurs during the process
     */
    public int openIntoLesson_PlayVideo(int sec) throws Exception {
        // Cast the driver to JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait for the lesson link to be clickable and then click it using JavaScript
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(lession_Intro_to_Markets_Loc));
        js.executeScript("arguments[0].click();", link);

        // Wait for the video link to be clickable and then click it
        WebElement linkVideo = wait.until(ExpectedConditions.elementToBeClickable(lession_Intro_to_Markets_Video_Loc));
        linkVideo.click();

        // Wait for the video iframe to be present and switch to it
        WebElement frame_Video = wait.until(ExpectedConditions.presenceOfElementLocated(video_iframe));
        driver.switchTo().frame(frame_Video);

        // Wait for the video play button to be visible and then click it to play the video
        WebElement videoPlayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(videoPlayButton_Loc));
        videoPlayButton.click();

        // Wait for the user-defined playtime
        Thread.sleep(sec * 1000);

        // Wait for the video progress time element to be present
        WebElement videoTimePlayed = wait.until(ExpectedConditions.presenceOfElementLocated(videoprogressTime_loc));

        // Get the text of the video progress time element using JavaScript
        String videoTimePlayedvalue = (String) js.executeScript("return arguments[0].innerText;", videoTimePlayed);

        // Trim the retrieved value and log the playtime
        videoTimePlayedvalue = videoTimePlayedvalue.trim();
        Reporter.log("Video play time is " + videoTimePlayedvalue, true);

        // Convert the playtime to seconds
        int seconds = DateUtils.convertTimeToSeconds(videoTimePlayedvalue);

        // Return the time played in seconds
        return seconds;
    }
}
