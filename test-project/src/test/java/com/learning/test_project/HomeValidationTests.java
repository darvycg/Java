package com.learning.test_project;

import com.learning.test_project.models.HomePage;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright
@SpringBootTest
public class HomeValidationTests {

    @Autowired
    private HomePage homePage;

    @Test
    void homePageLoads() {
        homePage.navigate();
        assertThat(homePage.getElementsLink()).isVisible();
        assertThat(homePage.getFormsLink()).isVisible();
        assertThat(homePage.getAlertsLink()).isVisible();
        assertThat(homePage.getWidgetsLink()).isVisible();
        assertThat(homePage.getInteractionsLink()).isVisible();
        assertThat(homePage.getBookStoreAppLink()).isVisible();
    }
}
