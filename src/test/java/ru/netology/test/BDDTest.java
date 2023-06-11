package ru.netology.test;


import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;


public class BDDTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:7777");
        var loginData = DataHelper.getAuthInfo();

        new LoginPage()
                .validLogin(loginData)
                .validVerify(DataHelper.getVerificationCodeFor(loginData))
                .transferPage()
                .transfer(DataHelper.getFirstCardNumber(),"50");
    }
}