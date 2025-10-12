package com.learning.test_project;

import com.learning.test_project.models.HomePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
