package ru.netology.web.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.pages.OrderPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.SQLHelper.cleanData;

public class PayByCreditTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    static void setup() {
        cleanData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    public void cleanTables() {
        cleanData();
    }

    @Nested
    public class PositiveTest {

        @Test
        void shouldBuyIfApprovedCard() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.successMessage();
            val status = SQLHelper.getStatusPayment();
            assertEquals("APPROVED", status);
        }

        @Test
        void shouldNotBuyIfDeclinedCard() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getDeclinedCard(), month, year, cardholder, cvv);
            orderPage.errorMessage();
            val status = SQLHelper.getStatusPayment();
            assertEquals("DECLINED", status);
        }

        @Test
        void shouldBuyIfAnotherBankCard() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getAnotherBankCard(), month, year, cardholder, cvv);
            orderPage.errorMessage();
            val status = SQLHelper.getStatusPayment();
            assertEquals("DECLINED", status);
        }
    }

    @Nested
    public class TestsByNumberCard {

        @Test
        void shouldNotPayIfShortNumber() {
            OrderPage orderPage = new OrderPage();
            DataHelper DataHelper = new DataHelper();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getSmallNumberCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfNoNumber() {
            OrderPage orderPage = new OrderPage();
            DataHelper DataHelper = new DataHelper();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getCardWithoutNumber(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfWrongSymbolNoNumber() {
            OrderPage orderPage = new OrderPage();
            DataHelper DataHelper = new DataHelper();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getNotDigitalNumber(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }
    }

    @Nested
    public class TestsByMonth {

        @Test
        void shouldNotPayIfNoMonth() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getNoMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfNullMonth() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getNullMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }

        @Test
        void shouldNotPayIfWrongMonth() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getWrongMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }

        @Test
        void shouldNotPayIfCardExpired() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getLastMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }
    }

    @Nested
    public class TestsByYear {

        @Test
        void shouldNotPayIfNoYear() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getNoYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfNullYear() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getNullYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.invalidCardMassage();
        }

        @Test
        void shouldNotPayIfLastYear() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getLastYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.invalidCardMassage();
        }

        @Test
        void shouldPayIfWrongYear() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getMaxValidDate();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatForMonthMassage();
        }
    }

    @Nested
    public class TestsByCVV {

        @Test
        void shouldNotPayIfNoCVV() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getNoCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }

        @Test
        void shouldNotPayIfWrongCVV() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholder();
            String cvv = DataHelper.getWrongCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.wrongFormatMassage();
        }
    }

    @Nested
    public class TestsByCardholder {

        @Test
        void shouldNotPayIfNoName() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getNoCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.cardholderNameMassage();
        }

        @Test
        void shouldNotPayIfWrongName() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getCardholderRussianLatter();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.cardholderNameMassage();
        }

        @Test
        void shouldNotPayIfOnlyName() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getOnlyNameCardholder();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.cardholderNameMassage();
        }

        @Test
        void shouldNotPayIfWrongSymbolName() {
            OrderPage orderPage = new OrderPage();
            String month = DataHelper.getCurrentMonth();
            String year = DataHelper.getCurrentYear();
            String cardholder = DataHelper.getWrongSymbolName();
            String cvv = DataHelper.getCvv();
            orderPage.setCreditPayment(DataHelper.getApprovedCard(), month, year, cardholder, cvv);
            orderPage.cardholderNameMassage();
        }
    }

}
