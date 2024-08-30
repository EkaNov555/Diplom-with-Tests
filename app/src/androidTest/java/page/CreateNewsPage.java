package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import datahelper.MatchHelper;

public class CreateNewsPage {

    private static int nameCreation = R.id.custom_app_bar_title_text_view;
    private static int titleNews = R.id.custom_app_bar_sub_title_text_view;
    private static int chooseCategory = R.id.text_input_end_icon;
    private static int nameField = R.id.news_item_title_text_input_edit_text;
    private static int dateField = R.id.news_item_publish_date_text_input_edit_text;
    private static int timeField = R.id.news_item_publish_time_text_input_edit_text;
    private static int descriptionField = R.id.news_item_description_text_input_edit_text;
    public static View decorView;
    private static String emptyField = "Fill empty fields";
    public static String description = "Вы заказали сыр на завтрак";

    public static void waitForCreateNewsPage() {
        Allure.step("Загрузка страницы создания новости");
        onView(isRoot()).perform(MatchHelper.waitDisplayed(nameCreation, 7000));
    }

    public static void checkNewsTitle() {
        Allure.step("Пользователь видит страницу создания новости");
        ViewInteraction textView = onView(withId(titleNews));
        textView.check(matches(withText("News")));
    }

    public static void checkEmptyFields() {
        Allure.step("Появление сообщения при добавлении новости с незаполненными обязательными полями");
        onView(withText(emptyField))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    public static void chooseCategory() {
        Allure.step("Выбор категории для новости");
        onView(allOf(withId(chooseCategory),
                withContentDescription("Show dropdown menu")))
                .perform(click());
        onView(withText("Объявление"))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
    }

    public static void chooseName() {
        Allure.step("Выбор названия для новости");
        onView(withId(nameField))
                .perform(replaceText("Cыр c дырками"));
    }

    public static void chooseDate() {
        Allure.step("Выбор текущей даты");
        onView(withId(dateField)).perform(click());
        onView(allOf(withId(android.R.id.button1), withText("OK")))
                .perform(click());
    }

    public static void chooseTime() {
        Allure.step("Выбор текущего времени");
        onView(withId(timeField)).perform(click());
        onView(allOf(withId(android.R.id.button1), withText("OK")))
                .perform(click());
    }

    public static void descriptionNews() {
        Allure.step("Ввод описания новости");
        onView(withId(descriptionField))
                .perform(replaceText(description));
    }

    public static void saveNews() {
        Allure.step("Сохранить новость");
        onView(withId(R.id.save_button)).perform(click());
    }

}
