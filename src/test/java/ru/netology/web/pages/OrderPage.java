package ru.netology.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OrderPage {

    private String paymentUrl = "http://localhost:8080";

    private ElementsCollection buttons = $$("[role=button]");
    private SelenideElement buyButton = buttons.first();
    private SelenideElement creditButton = $(byText("Купить в кредит"));
    private SelenideElement continueButton = $(byText("Продолжить"));

    private ElementsCollection fields = $$(".input__control");
    private SelenideElement numberField = $("input[type=\"text\"][placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement cardholderField = fields.get(3);
    private SelenideElement cvvField = $("[placeholder='999']");

    private SelenideElement wrongFormatMassage = $(byText("Неверный формат"));
    private SelenideElement wrongFormatForMonthMassage = $(byText("Неверно указан срок действия карты"));
    private SelenideElement invalidCardMassage = $(byText("Истёк срок действия карты"));
    private SelenideElement cardholderNameMassage = $(byText("Поле обязательно для заполнения"));

    public OrderPage() {
        open(paymentUrl);
    }

    public void setPayment(DataHelper.CardNumber info, String month, String year, String cardholder, String cvv) {
        buyButton.click();
        numberField.setValue(info.getCardNumber());
        monthField.setValue(month);
        yearField.setValue(year);
        cardholderField.setValue(cardholder);
        cvvField.setValue(cvv);
        continueButton.click();
    }

    public void setCreditPayment(DataHelper.CardNumber info, String month, String year, String cardholder, String cvv) {
        creditButton.click();
        numberField.setValue(info.getCardNumber());
        monthField.setValue(month);
        yearField.setValue(year);
        cardholderField.setValue(cardholder);
        cvvField.setValue(cvv);
        continueButton.click();
    }

    public void getSuccessMessage() {
        $(byText("Операция одобрена Банком.")).waitUntil(Condition.visible, 15000);
    }

    public void getErrorMessage() {
        $(byText("Ошибка! Банк отказал в проведении операции.")).waitUntil(Condition.visible, 15000);
    }

    public void waitWrongFormatMassage() {
        wrongFormatMassage.waitUntil(visible, 15000);
    }

    public void waitWrongFormatForMonthMassage() {
        wrongFormatForMonthMassage.waitUntil(visible, 15000);
    }

    public void waitInvalidCardMassage() {
        invalidCardMassage.waitUntil(visible, 15000);
    }

    public void waitCardholderNameMassage() {
        cardholderNameMassage.waitUntil(visible, 15000);
    }
}
