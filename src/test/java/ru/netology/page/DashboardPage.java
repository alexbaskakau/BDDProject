package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public static class LoginPage {

        public VerificationPage validLogin (DataHelper.AuthInfo info) {
            $("[data-test-id=login] input").setValue(info.getLogin());
            $("[data-test-id=password] input").setValue(info.getPassword());
            $("[data-test-id=action-login]").click();
            return new VerificationPage();
        }
    }
}