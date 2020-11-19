package ru.netology.web.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.pages.OrderPage;

public class PayByCardUITest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldBuyIfApprovedCard() {
        OrderPage orderPage = new OrderPage();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getCurrentYear();
        String cardholder = DataHelper.getCardholder();
        String cvv = DataHelper.getCvv();
        orderPage.setPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
        orderPage.successMessage();
    }

    @Test
    void shouldNotBuyIfDeclinedCard() {
        OrderPage orderPage = new OrderPage();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getCurrentYear();
        String cardholder = DataHelper.getCardholder();
        String cvv = DataHelper.getCvv();
        orderPage.setPayment(DataHelper.getDeclinedCard(), month, year, cardholder, cvv);
        orderPage.errorMessage();
    }

    @Test
    void shouldBuyIfAnotherBankCard() {
        OrderPage orderPage = new OrderPage();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getCurrentYear();
        String cardholder = DataHelper.getCardholder();
        String cvv = DataHelper.getCvv();
        orderPage.setPayment(DataHelper.getAnotherBankCard(), month, year, cardholder, cvv);
        orderPage.successMessage();
    }
}
