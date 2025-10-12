package com.learning.test_project;

import com.learning.test_project.models.ElementsPage;
import com.learning.test_project.models.HomePage;
import com.learning.test_project.models.elements.CheckBoxPage;
import com.learning.test_project.models.elements.TextBoxPage;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright
@SpringBootTest
public class ElementsTests {

    @Autowired
    HomePage homePage;
    @Autowired
    ElementsPage elementsPage;
    @Autowired
    TextBoxPage textBoxPage;

    private static String FULL_NAME = "Automated Test";
    private static String EMAIL = "automated@test.com";
    private static String CURRENT_ADDRESS = "1234 N Automated St., San Francisco, CA";
    private static String PERMANENT_ADDRESS = "5678 S Automated St., San Francisco, CA";

    @Test
    void loadElementsPage() {
        elementsPage.navigate();
        assertThat(elementsPage.getTextBoxLink()).isVisible();
        assertThat(elementsPage.getCheckBoxLink()).isVisible();
        assertThat(elementsPage.getRadioButtonLink()).isVisible();
        assertThat(elementsPage.getWebTablesLink()).isVisible();
        assertThat(elementsPage.getButtonsLink()).isVisible();
        assertThat(elementsPage.getLinksLink()).isVisible();
        assertThat(elementsPage.getBrokenLinkImagesLink()).isVisible();
        assertThat(elementsPage.getUploadAndDownloadLink()).isVisible();
        assertThat(elementsPage.getDynamicPropertiesLink()).isVisible();
    }

    @Test
    void textBoxHappyPath() {
        elementsPage.navigate();
        elementsPage.clickTextBoxLink();
        textBoxPage.enterFullName(FULL_NAME);
        textBoxPage.enterEmail(EMAIL);
        textBoxPage.enterCurrentAddress(CURRENT_ADDRESS);
        textBoxPage.enterPermanentAddress(PERMANENT_ADDRESS);
        textBoxPage.clickSubmit();
        assertThat(textBoxPage.getNameValidator()).containsText(FULL_NAME);
    }
}
