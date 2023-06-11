package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TopupPage {
    private SelenideElement heading= $("[data-test-id='dashboard']");

public TopupPage() {
    heading.shouldBe(Condition.visible);
}
public DashboardPage transfer(DataHelper.FirstCardNumber firstCardNumber, String amount) {
    $("[data-test-id='amount'] input").setValue(amount);
    $("[data-test-id='from'] input").setValue(firstCardNumber.getCardOneNumber());
    $("[data-test-id='action-transfer']").click();
    return new DashboardPage();
}
}
