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
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutTests {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            MainPage.loadMainPage();
            MainPage.tapBar();
            MainPage.tapAbout();
        } catch (Exception e) {
            AuthPage.waitForLoadAuthPage();
            AuthPage.positiveAuth();
            AuthPage.pressSignButton();
            MainPage.loadMainPage();
            MainPage.tapBar();
            MainPage.tapAbout();
        }
    }

    @After
    public void SetOut() {
        try {
            MainPage.logOut();
        } catch (Exception e) {
        }
    }

    // Т-кейс 22
    @Test
    public void goToPrivacyPolicy() {
        AboutPage.tapPolicyLink();
    }

    @Test
    public void goToTermsOfUse() {
        AboutPage.tapTermsOfUse();
    }
}

