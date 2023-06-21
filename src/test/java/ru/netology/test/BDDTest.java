package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;


public class BDDTest {
    int initBalCardOne;
    int initBalCardTwo;
    int finBalCardOne;
    int finBalCardTwo;
    DashboardPage dashboardPage;


    @BeforeEach
    void SetUp() {
        var loginPage = open("http://localhost:7777", LoginPage.class);
        var loginData = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(loginData);
        var verificationCode = getVerificationCodeFor(loginData);
        dashboardPage = verificationPage.validVerify(verificationCode);
        initBalCardOne = dashboardPage.getFirstCardBalance();
        initBalCardTwo = dashboardPage.getSecondCardBalance();
    }


    @Test
    void shouldTransferFromSecondToFirstCard() {
        int amount = 2500;
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var topupPage = dashboardPage.transferPage2();
        dashboardPage = topupPage.transfer(secondCardInfo.getCardNumber(), Integer.toString(amount));
        finBalCardOne = dashboardPage.getFirstCardBalance();
        finBalCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals(initBalCardOne + amount, finBalCardOne);
        assertEquals(initBalCardTwo - amount, finBalCardTwo);
    }

    @Test
    void shouldTransferFromFirstToSecondCard() {
        int amount = 2500;
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var topupPage = dashboardPage.transferPage1();
        dashboardPage = topupPage.transfer(firstCardInfo.getCardNumber(), Integer.toString(amount));
        finBalCardOne = dashboardPage.getFirstCardBalance();
        finBalCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals(initBalCardOne - amount, finBalCardOne);
        assertEquals(initBalCardTwo + amount, finBalCardTwo);
    }

    @Test
    void shouldTransferPennies() {
        double amount = 25.5;
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var topupPage = dashboardPage.transferPage1();
        dashboardPage = topupPage.transfer(firstCardInfo.getCardNumber(), Double.toString(amount));
        finBalCardOne = dashboardPage.getFirstCardBalance();
        finBalCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals(initBalCardOne - amount, finBalCardOne);
        assertEquals(initBalCardTwo + amount, finBalCardTwo);
    }
}



