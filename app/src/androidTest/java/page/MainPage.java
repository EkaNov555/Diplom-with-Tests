package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static datahelper.MatchHelper.waitDisplayed;
import static datahelper.MatchHelper.childAtPosition;

import android.annotation.SuppressLint;

import org.hamcrest.core.AllOf;

import datahelper.MatchHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPage {
    public static int mainLabel = R.id.trademark_image_view;
    public static int newsList = R.id.news_list_recycler_view;
    public static int listOfNewsButton = R.id.expand_material_button;

    public static int logOutImage = R.id.authorization_image_button;
    public static int logOutButton = android.R.id.title;
    public static int mainMenu = R.id.main_menu_image_button;
    public static int tapBar = android.R.id.title;
    public static int tapContent = android.R.id.content;
    public static int ourMission = R.id.our_mission_image_button;

    public static void logOut() {
        Allure.step("Выйти из личного кабинета");
        onView(withId(logOutImage)).perform(click());
        onView(withId(logOutButton)).perform(click());
    }

    public static void tapBar() {
        Allure.step("Открыть меню TapBar");
        onView(withId(mainMenu)).perform(click());
    }

    public static void tapNews() {
        Allure.step("Открыть страницу News из TapBar");
        onView(allOf(withId(tapBar), withText("News"),
                childAtPosition(childAtPosition(
                        withId(tapContent), 0), 0)))
                .perform(click());
    }

    public static void tapAbout() {
        Allure.step("Открыть страницу About из TapBar");
        onView(allOf(withId(tapBar), withText("About"),
                childAtPosition(childAtPosition(
                        withId(tapContent), 0), 0)))
                .perform(click());
    }

    public static void tapLoveIsAll() {
        Allure.step("Открыть страницу Love is all");
        onView(withId(ourMission)).perform(click());
    }

    public static void loadMainPage() {
        Allure.step("Ожидание загрузки главной страницы");
        onView(isRoot()).perform(waitDisplayed(mainLabel, 9000));
    }

    public static void wrapNews() {
        Allure.step("Сворачивание новости в главном блоке");
        onView(withId(listOfNewsButton)).perform(click());
    }
}
