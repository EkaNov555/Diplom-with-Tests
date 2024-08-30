package tests;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import page.AuthPage;
import page.MainPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthTests {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @After
    public void SetOut() {
        try {
            MainPage.logOut();
        } catch (Exception e) {
        }
    }

    @Test
    public void authIsDisplayed() {
        AuthPage.waitForLoadAuthPage();
    }
    // Т-кейс №1
    @Test
    public void emptyAuth() {
        AuthPage.waitForLoadAuthPage();
        AuthPage.pressSignButton();
        AuthPage.checkEmptyLoginPass();
    }
    // Т-кейс №2
    @Test
    public void successfulAuth() {
        AuthPage.waitForLoadAuthPage();
        AuthPage.positiveAuth();
        AuthPage.pressSignButton();
        MainPage.loadMainPage();
    }
    // Т-кейс №3,4
    @Test
    public void wrongAuth() {
        AuthPage.waitForLoadAuthPage();
        AuthPage.textInputIncorrectLoginField();
        AuthPage.textInputIncorrectPassField();
        AuthPage.pressSignButton();
        AuthPage.checkWrongLoginPass();
    }

    // тесты по Т-кейсам №7,8
    @Test
    public void authLessSymbols() {
        AuthPage.waitForLoadAuthPage();
        AuthPage.textInputLessSymbolsLoginField();
        AuthPage.textInputLessSymbolsPassField();
        AuthPage.pressSignButton();
        AuthPage.checkLessSymbolsAuthPass();
    }

    @Test
    public void loginRusSymbols() { // тест не проходит, так как в поле можно ввести символы на кирилице, что должно быть недопустимо
        AuthPage.waitForLoadAuthPage();
        AuthPage.textInputRusSymbolsLoginField();
        AuthPage.checkNoSymbolsLoginField();
    }

    @Test
    public void authBadSpecialSymbols() { // тест не проходит, так как в поля можно ввести спецсимволы, что указывает на уязвимость к инъекциям
        AuthPage.waitForLoadAuthPage();
        AuthPage.textInputSpecialSymbolsLoginField();
        AuthPage.textInputSpecialSymbolsPasswordField();
        AuthPage.checkNoSymbolsLoginField();
        AuthPage.checkNoSymbolsPasswordField();
    }

    @Test
    public void loginSpaceSymbol() { // тест не проходит из-за бага из тест-кейса №7
        AuthPage.waitForLoadAuthPage();
        AuthPage.textInputSpaceSymbolLoginField();
        AuthPage.checkNoSymbolsLoginField();
    }

    @Test
    public void inputLoginWithSpace() { // тест не проходит из-за бага из тест-кейса №7
        AuthPage.waitForLoadAuthPage();
        AuthPage.textInputLoginWithSpace();
        AuthPage.checkNoSpaceSymbolInLogin();
    }

    @Test
    public void loginMoreSymbols() { // тест проходит и подтверждает отсутствие ограничения на кол-во вводимых символов в данном поле
        AuthPage.waitForLoadAuthPage();
        AuthPage.textInputMoreSymbolsLoginField();
        AuthPage.checkMoreSymbolsLoginField();
    }

    @Test
    public void passMoreSymbols() { //тест проходит и подтверждает отсутствие ограничения на кол-во вводимых символов в данном поле
        AuthPage.waitForLoadAuthPage();
        AuthPage.textInputMoreSymbolsPassField();
        AuthPage.checkMoreSymbolsPassField();
    }
}
