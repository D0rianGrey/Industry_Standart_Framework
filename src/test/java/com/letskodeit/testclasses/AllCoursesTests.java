package com.letskodeit.testclasses;

import com.letskodeit.pageclasses.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AllCoursesTests {
    private WebDriver driver;
    private String baseURL;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        baseURL = "https://learn.letskodeit.com";
        driver.get(baseURL);
    }

    @Test
    public void verifySearchCourse() {
        LoginPage login = new LoginPage(driver);
        login.open();
        NavigationPage navigationPage = login.signInWith("test@email.com", "abcabc");
        navigationPage.allCourses();
        SearchBarPage search = new SearchBarPage(driver);
        ResultsPage result = search.course("rest api");
        boolean searchResult = result.verifySearchResult();
        Assert.assertTrue(searchResult);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
