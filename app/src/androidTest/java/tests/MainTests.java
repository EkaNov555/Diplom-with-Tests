package tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import page.AboutPage;
import page.AuthPage;
import page.MainPage;
import page.NewsPage;
import page.LoveIsAllPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainTests {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            MainPage.loadMainPage();
        } catch (Exception e) {
            AuthPage.waitForLoadAuthPage();
            AuthPage.positiveAuth();
            AuthPage.pressSignButton();
            MainPage.loadMainPage();
        }
    }

    @After
    public void SetOut() {
        try {
            MainPage.logOut();
        } catch (Exception e) {
        }
    }

    // Т-кейс № 15
    @Test
    public void goToNews() {
        MainPage.tapBar();
        MainPage.tapNews();
        NewsPage.loadNewsPage();
        NewsPage.checkNamesPage();
    }

    @Test
    public void goToAbout() {
        MainPage.tapBar();
        MainPage.tapAbout();
        AboutPage.loadAboutPage();
        AboutPage.checkLabelName();
    }

    @Test
    public void goToAboutFromNews() { //тест не проходит из-за бага в приложении, задокументированного в тест-кейсе №15
        MainPage.tapBar();
        MainPage.tapNews();
        NewsPage.loadNewsPage();
        NewsPage.checkNamesPage();
        MainPage.tapAbout();
        AboutPage.loadAboutPage();
        AboutPage.checkLabelName();
    }

    @Test
    public void goLoveIsAll() {
        MainPage.tapLoveIsAll();
        LoveIsAllPage.loadMissionPage();
        LoveIsAllPage.checkMissionName();
    }
}
