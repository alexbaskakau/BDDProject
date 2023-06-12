package ru.netology.test;


import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TopupPage;
import ru.netology.page.VerificationPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.page.TopupPage.transfer;


public class BDDTest {
    int initBalCardOne;
    int initBalCardTwo;
    int finBalCardOne;
    int finBalCardTwo;


    @Test
    void shouldTransferFromSecondToFirstCard() {
        open("http://localhost:7777");
        var loginData = DataHelper.getAuthInfo();

        new LoginPage()
                .validLogin(loginData)
                .validVerify(DataHelper.getVerificationCodeFor(loginData));
        initBalCardOne = new DashboardPage().getFirstCardBalance();
        initBalCardTwo = new DashboardPage().getSecondCardBalance();
        int amount = 2500;
        new DashboardPage()
                .transferPage()
                .transfer(DataHelper.getSecondCardNumber(), Integer.toString(amount));
        finBalCardOne = new DashboardPage().getFirstCardBalance();
        finBalCardTwo = new DashboardPage().getSecondCardBalance();
        assertEquals(initBalCardOne + amount, finBalCardOne);
        assertEquals(initBalCardTwo - amount, finBalCardTwo);
    }
    }



