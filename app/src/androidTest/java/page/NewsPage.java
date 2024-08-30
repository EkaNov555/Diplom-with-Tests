package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static datahelper.MatchHelper.childAtPosition;
import static page.CreateNewsPage.description;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import datahelper.MatchHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class NewsPage {
    private static int newsContainer = R.id.container_list_news_include;
    private static int editNews = R.id.edit_news_material_button;
    public static int newsList = R.id.news_list_recycler_view;
    public static int newsDescription = R.id.news_item_description_text_view;


    public static void loadNewsPage() {
        Allure.step("Ожидание загрузки страницы Новостей");
        onView(isRoot()).perform(MatchHelper.waitDisplayed(newsContainer, 7000));
    }

    public static void checkNamesPage() {
        Allure.step("Страница News загружена и отображается");
        ViewInteraction textView = onView(childAtPosition(
                childAtPosition(
                        withId(newsContainer),
                        0),
                0));
        textView.check(matches(withText("News")));
    }

    public static void editNews() {
        Allure.step("Нажатие на кнопку Редактировать новости");
        onView(withId(editNews)).perform(click());
    }

    public static void openFirstNews() {
        Allure.step("Открыть описание первой новости");
        onView(withId(newsList))
                .perform(actionOnItemAtPosition(0, click()));
    }

    public static void loadDescription() {
        Allure.step("Загрузка описания новости");
        onView(isRoot())
                .perform(MatchHelper.waitDisplayed(newsDescription, 6000));
    }

    public static void checkDescription() {
        Allure.step("Пользователь видит описание созданной новости");
        ViewInteraction textView = onView(
                allOf(withId(newsDescription), withText(description),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText(description)));

    }
}
