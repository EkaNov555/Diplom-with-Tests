package tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import page.AuthPage;
import page.EditPage;
import page.MainPage;
import page.CreateNewsPage;
import page.NewsPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTests {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            MainPage.loadMainPage();
            MainPage.tapBar();
            MainPage.tapNews();
        } catch (Exception e) {
            AuthPage.waitForLoadAuthPage();
            AuthPage.positiveAuth();
            AuthPage.pressSignButton();
            MainPage.loadMainPage();
            MainPage.tapBar();
            MainPage.tapNews();
        }
    }

    @After
    public void SetOut() {
        try {
            MainPage.logOut();
        } catch (Exception e) {
        }
    }

    @Test
    public void addNews() {
        NewsPage.editNews();
        EditPage.waitForEditNewsPage();
        EditPage.addNews();
        CreateNewsPage.waitForCreateNewsPage();
        CreateNewsPage.checkNewsTitle();
    }

    // Т-кейс №27
    @Test
    public void addNewsPositive() {
        NewsPage.editNews();
        EditPage.waitForEditNewsPage();
        EditPage.addNews();
        CreateNewsPage.waitForCreateNewsPage();
        CreateNewsPage.chooseCategory();
        CreateNewsPage.chooseName();
        CreateNewsPage.chooseDate();
        CreateNewsPage.chooseTime();
        CreateNewsPage.descriptionNews();
        CreateNewsPage.saveNews();
        EditPage.waitForEditNewsPage();
        MainPage.tapBar();
        MainPage.tapNews();
        NewsPage.openFirstNews();
        NewsPage.loadDescription();
        NewsPage.checkDescription();
    }

    // Т-кейс №28
    @Test
    public void addEmptyNews() {
        NewsPage.editNews();
        EditPage.waitForEditNewsPage();
        EditPage.addNews();
        CreateNewsPage.saveNews();
        CreateNewsPage.checkEmptyFields();
    }
}
