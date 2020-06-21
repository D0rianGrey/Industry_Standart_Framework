package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import com.letskodeit.pageclasses.CategoryFilterPage;
import com.letskodeit.pageclasses.SearchBarPage;
import com.letskodeit.utilities.Constants;
import com.letskodeit.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AllCoursesTests extends BaseTest {

    @DataProvider(name = "verifySearchCourseData")
    public Object[][] getSearchCourseDara(){
        Object[][] testData = ExcelUtility.getTestData("verify_search_course");
        return testData;
    }

    @BeforeClass
    public void setUp() {
        navigationPage = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE,"AllCoursesTests");
    }


    @Test(dataProvider = "verifySearchCourseData")
    public void verifySearchCourse(String courseName) {
        navigationPage.allCourses();
        searchBarPage = new SearchBarPage(driver);
        resultsPage = searchBarPage.course(courseName);
        boolean searchResult = resultsPage.verifySearchResult();
        Assert.assertTrue(searchResult);
    }

    @Test(enabled = false)
    public void filterByCategory() {
        navigationPage.allCourses();
        categoryFilterPage = new CategoryFilterPage(driver);
        resultsPage = categoryFilterPage.select("Software IT");
        int count = categoryFilterPage.findCoursesCount("Software IT");
        boolean filterResult = resultsPage.verifyFilterCourseCount(count);
        Assert.assertTrue(filterResult);
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}
