package com.learning.test_project.models;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Data
@Component
public class ElementsPage {

    private final Locator textBoxLink;
    private final Locator checkBoxLink;
    private final Locator radioButtonLink;
    private final Locator webTablesLink;
    private final Locator buttonsLink;
    private final Locator linksLink;
    private final Locator brokenLinkImagesLink;
    private final Locator uploadAndDownloadLink;
    private final Locator dynamicPropertiesLink;
    private Page page;

    public ElementsPage(Page page) {
        this.page = page;
        this.textBoxLink = this.page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Text Box"));
        this.checkBoxLink = this.page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Check Box"));
        this.radioButtonLink = this.page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Radio Button"));
        this.webTablesLink = this.page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Web Tables"));
        this.buttonsLink = this.page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Buttons"));
        this.linksLink = this.page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Links$")));
        this.brokenLinkImagesLink = this.page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Broken Links - Images"));
        this.uploadAndDownloadLink = this.page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Upload and Download"));
        this.dynamicPropertiesLink = this.page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Dynamic Properties"));
    }

    public void navigate() {
        page.navigate("/elements");
    }

    public void clickTextBoxLink() {
        this.getTextBoxLink().click();
    }
}
