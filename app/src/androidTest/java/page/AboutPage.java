package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import datahelper.MatchHelper;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutPage {
    private static int aboutLabel = R.id.about_company_info_label_text_view;
    private static int privacyText = R.id.about_privacy_policy_value_text_view;
    private static int termsOfUse = R.id.about_terms_of_use_value_text_view;

    public static void loadAboutPage() {
        Allure.step("Загрузка страницы About");
        onView(isRoot()).perform(MatchHelper.waitDisplayed(aboutLabel, 5000));
    }

    public static void checkLabelName() {
        Allure.step("Страница About загружена");
        ViewInteraction textView = onView(withId(aboutLabel));
        textView.check(matches(withText("© I-Teco, 2022")));
    }

    public static void tapPolicyLink() {
        Allure.step("При клике по ссылке Privacy Policy выполняется переход");
        onView(withId(privacyText)).check(matches(isClickable()));
    }

    public static void tapTermsOfUse() {
        Allure.step("При клике по ссылке Terms of Use выполняется переход");
        onView(withId(termsOfUse)).check(matches(isClickable()));
    }
}
