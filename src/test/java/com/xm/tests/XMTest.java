package com.xm.tests;

import com.xm.config.DriverSetup;
import com.xm.utils.DateUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.xm.pages.EconomicCalendarPage;
import com.xm.pages.EducationalVideosPage;
import com.xm.pages.HomePage;
import com.xm.pages.ResearchAndEducationPage;

public class XMTest {
    private WebDriver driver;
    private HomePage homePage;
    private ResearchAndEducationPage researchAndEducationPage;
    private EconomicCalendarPage economicCalendarPage;
    private EducationalVideosPage educationalVideosPage;
    private String url;

    @DataProvider(name = "screenResolutions")
    public Object[][] screenResolutions() {
        return new Object[][] {
                {1920, 1080}, // Maximum supported by your display
                /*{1024, 768},
                {800, 600}*/
        };
    }

    @BeforeMethod
    public void setup(Object[] resolution) {
        int width = (int) resolution[0];
        int height = (int) resolution[1];

        driver = new DriverSetup().setupDriver(width, height);
        homePage = new HomePage(driver);
        researchAndEducationPage = new ResearchAndEducationPage(driver);
        economicCalendarPage = new EconomicCalendarPage(driver);
        educationalVideosPage = new EducationalVideosPage(driver);
        url = DriverSetup.getProperties().getProperty("url");
    }

    @Test(dataProvider = "screenResolutions")
    public void testXM_Slider_date(int width, int height) throws Exception {
        homePage.openHomePage(url);
        homePage.clickResearchAndEducation();
        researchAndEducationPage.clickEconomicCalendar();
        String date_set_on_Ui = economicCalendarPage.setSliderValue_today();
        Assert.assertTrue(date_set_on_Ui.equals(DateUtils.getDateFromToday(0)));
    }

    @Test(dataProvider = "screenResolutions")
    public void testXM_Edu_Video_Duration(int width, int height) throws Exception {
        homePage.openHomePage(url);
        homePage.clickResearchAndEducation();
        researchAndEducationPage.clickEducationalVideos();
        Assert.assertTrue(educationalVideosPage.openIntoLesson_PlayVideo(10) >= 5, "Video is not played for 10 sec");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
