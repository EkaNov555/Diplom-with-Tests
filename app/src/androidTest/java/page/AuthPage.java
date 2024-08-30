package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static datahelper.MatchHelper.childAtPosition;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;

import datahelper.DataForTests;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;


public class AuthPage {
    private static int auth = R.id.nav_host_fragment;
    private static int loginField = R.id.login_text_input_layout;
    private static int passField = R.id.password_text_input_layout;
    private static int signButton = R.id.enter_button;
    public static View decorView;
    private static String wrongPassOrLogin = "Something went wrong. Try again later.";
    private static String emptyFields = "Login and password cannot be empty";
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    public static void waitForLoadAuthPage() {
        Allure.step("Загрузка страницы Авторизации");
        onView(isRoot()).perform(datahelper.MatchHelper.waitDisplayed(loginField, 7000));
    }

    public static void checkWrongLoginPass() {
        Allure.step("Появление сообщения об ошибке при вводе незарегистрированных логина или пароля");
        onView(withText(wrongPassOrLogin))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
    public static void checkEmptyLoginPass() {
        Allure.step("Появление сообщения об ошибке при входе с незаполненными полями логина и пароля");
        onView(withText(emptyFields))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
    public static void checkLessSymbolsAuthPass() {
        Allure.step("Появление сообщения об ошибке при вводе недостаточном количество символов");
        onView(withText(wrongPassOrLogin))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    public static void checkMoreSymbolsLoginField() {
        Allure.step("Ввод максимального кол-ва символов поля Логин"); //границы ввода нет либо кол-во знаков очень большое
        ViewInteraction editText = onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)));
        editText.check(matches(withText("4612786139798746786651743574137687897795379875987389579755")));
    }

    public static void checkMoreSymbolsPassField() {
        Allure.step("Ввод максимального кол-ва символов поля Пароль");
        ViewInteraction editText = onView(
                allOf(childAtPosition(childAtPosition(withId(passField),
                        0), 0)));
        editText.check(matches(withText("4612786139798746786651743574137687897795379875987389579755"))); //границы ввода нет либо кол-во знаков очень большое
    }

    public static void checkNoSymbolsLoginField() {
        Allure.step("Ввод пустого значения в поле логин");
        ViewInteraction editText = onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)));
        editText.check(matches(withText("")));
    }

    public static void checkNoSymbolsPasswordField() {
        Allure.step("Ввод пустого значения в поле пароль");
        ViewInteraction editText = onView(
                allOf(childAtPosition(childAtPosition(withId(passField),
                        0), 0)));
        editText.check(matches(withText("")));
    }

    public static void checkNoSpaceSymbolInLogin() {
        Allure.step("Ввод логина с пробелом в составе");
        ViewInteraction editText = onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)));
        editText.check(matches(withText("loginlogin")));
    }

    public static void positiveAuth() {
        Allure.step("Ввод зарегистрированных данных для авторизации");
        onView(withId(loginField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)))
                .perform(replaceText(DataForTests.correctUser().getLogin()));
        onView(withId(passField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(passField),
                        0), 0)))
                .perform(replaceText(DataForTests.correctUser().getPassword()), closeSoftKeyboard());
    }

    public static void pressSignButton() {
        Allure.step("Нажатие на кнопку входа");
        onView(withId(signButton)).perform(click());
    }

    public static void textInputIncorrectLoginField() {
        Allure.step("Ввод незарегистрированного логина");
        onView(withId(loginField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)))
                .perform(replaceText(DataForTests.inCorrectUser().getLogin()), closeSoftKeyboard());
    }

    public static void textInputIncorrectPassField() {
        Allure.step("Ввод незарегистрированного пароля");
        onView(withId(passField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(passField),
                        0), 0)))
                .perform(replaceText(DataForTests.inCorrectUser().getPassword()), closeSoftKeyboard());
    }

    public static void textInputLessSymbolsLoginField() {
        Allure.step("Ввод недостаточного количества символов в поле Логин");
        onView(withId(loginField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)))
                .perform(replaceText(DataForTests.lessSymbolsThanNeed().getLogin()), closeSoftKeyboard());
    }

    public static void textInputLessSymbolsPassField() {
        Allure.step("Ввод недостаточного количества символов в поле Пароль");
        onView(withId(passField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(passField),
                        0), 0)))
                .perform(replaceText(DataForTests.lessSymbolsThanNeed().getPassword()), closeSoftKeyboard());
    }

    public static void textInputMoreSymbolsLoginField() {
        Allure.step("Ввод большего количества символов в поле Логин");
        onView(withId(loginField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)))
                .perform(replaceText(DataForTests.moreSymbolsThanNeed().getLogin()), closeSoftKeyboard());
    }

    public static void textInputMoreSymbolsPassField() {
        Allure.step("Ввод большего количества символов в поле Пароль");
        onView(withId(passField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(passField),
                        0), 0)))
                .perform(replaceText(DataForTests.moreSymbolsThanNeed().getPassword()), closeSoftKeyboard());
    }

    public static void textInputRusSymbolsLoginField() {
        Allure.step("Ввод символов кириллицы");
        onView(withId(loginField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)))
                .perform(replaceText(DataForTests.badSymbolsLogin().getRusSymbols()), closeSoftKeyboard());
    }

    public static void textInputSpecialSymbolsLoginField() {
        Allure.step("Ввод спец символов в поле Логин");
        onView(withId(loginField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)))
                .perform(replaceText(DataForTests.badSymbolsLogin().getSpecialSymbols()), closeSoftKeyboard());
    }

    public static void textInputSpecialSymbolsPasswordField() {
        Allure.step("Ввод спец символов в поле Пароль");
        onView(withId(passField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(passField),
                        0), 0)))
                .perform(replaceText(DataForTests.badSymbolsPassword().getSpecialSymbolsInPass()), closeSoftKeyboard());
    }

    public static void textInputSpaceSymbolLoginField() {
        Allure.step("Ввод пробела в поле Логин");
        onView(withId(loginField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)))
                .perform(replaceText(DataForTests.badSymbolsLogin().getSpaceSymbol()), closeSoftKeyboard());
    }

    public static void textInputLoginWithSpace() {
        Allure.step("Ввод логина с пробелом в составе");
        onView(withId(loginField)).perform(click());
        onView(
                allOf(childAtPosition(childAtPosition(withId(loginField),
                        0), 0)))
                .perform(replaceText(DataForTests.badSymbolsLogin().getLoginWithSpace()), closeSoftKeyboard());
    }
}