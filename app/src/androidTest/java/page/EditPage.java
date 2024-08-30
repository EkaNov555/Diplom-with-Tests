package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import datahelper.MatchHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class EditPage {

    private static int fragmentContainer = R.id.nav_host_fragment;
    private static int newsContainer = R.id.container_custom_app_bar_include_on_fragment_news_control_panel;
    private static int newsCard = R.id.news_item_material_card_view;
    private static int addNewsButton = R.id.add_news_image_view;

    public static void waitForEditNewsPage() {
        Allure.step("Загрузка страницы Редактивания Новостей");
        onView(isRoot()).perform(MatchHelper.waitDisplayed(newsContainer, 7000));
    }

    public static void addNews() {
        Allure.step("Нажатие на кнопку Добавить новость");
        onView(withId(addNewsButton)).perform(click());
    }


}
