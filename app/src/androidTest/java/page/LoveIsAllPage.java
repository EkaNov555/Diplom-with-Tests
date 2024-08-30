package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static page.CreateNewsPage.description;

import android.view.View;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import datahelper.MatchHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class LoveIsAllPage {
    private static int missionText = R.id.our_mission_title_text_view;
    private static int quoteTitle = R.id.our_mission_item_title_text_view;
    private static int descriptionButton = R.id.our_mission_item_open_card_image_button;
    private static int quoteText = R.id.our_mission_item_description_text_view;

    public static void loadMissionPage() {
        Allure.step("Загрузка страницы Цитат");
        onView(isRoot()).perform(MatchHelper.waitDisplayed(missionText, 5000));
    }

    public static void checkMissionName() {
        Allure.step("Пользователь видит страницу цитат");
        ViewInteraction textView = onView(withId(missionText));
        textView.check(matches(withText("Love is all")));
    }
    public static void checkQuoteDescription() {
        Allure.step("Пользователь видит описание цитаты");
        ViewInteraction quoteView = onView(
              allOf(withId(quoteText),
                       withParent(withParent(withId(R.id.our_mission_item_material_card_view))),
                       isDisplayed()));
       quoteView.check(matches(isDisplayed()));

    }
    public static void openFirstQuote() {
        Allure.step("Открыть описание первой цитаты");
        onView(withId(R.id.our_mission_item_list_recycler_view))
                .perform(actionOnItemAtPosition(0, click()));
    }
}
