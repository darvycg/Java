package com.learning.test_project.models;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class HomePage {

    private final Page page;
    private final Locator elementsLink;
    private final Locator formsLink;
    private final Locator alertsLink;
    private final Locator widgetsLink;
    private final Locator interactionsLink;
    private final Locator bookStoreAppLink;

    public HomePage(Page page) {
        this.page = page;
        this.elementsLink = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Elements"));
        this.formsLink = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Forms"));
        this.alertsLink = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Alerts, Frame & Windows"));
        this.widgetsLink = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Widgets"));
        this.interactionsLink = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Interactions"));
        this.bookStoreAppLink = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Book Store Application"));
    }

    public void navigate() {
        page.navigate("/");
    }

    public void clickElementsLink() {
        this.getElementsLink().click();
    }
}
