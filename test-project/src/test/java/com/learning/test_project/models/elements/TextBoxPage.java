package com.learning.test_project.models.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TextBoxPage {

    private final Page page;
    private final Locator fullNameInput;
    private final Locator emailInput;
    private final Locator currentAddressInput;
    private final Locator permanentAddressInput;
    private final Locator submitButton;
    private final Locator nameValidator;
    private final Locator emailValidator;
    private final Locator currendAddressValidator;
    private final Locator permanentAddressValidator;

    public TextBoxPage(Page page) {
        this.page = page;
        this.fullNameInput = this.page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Full Name"));
        this.emailInput = this.page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("name@example.com"));
        this.currentAddressInput = this.page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Current Address"));
        this.permanentAddressInput = this.page.locator("#permanentAddress");
        this.submitButton = this.page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"));
        this.nameValidator = this.page.getByText("Name:");
        this.emailValidator = this.page.getByText("Email:");
        this.currendAddressValidator = this.page.getByText("Current Address :");
        this.permanentAddressValidator = this.page.getByText("Permananet Address :");
    }

    public void navigate() {
        this.page.navigate("/text-box");
    }

    public void enterFullName(String name) {
        this.getFullNameInput().fill(name);
    }

    public void enterEmail(String email) {
        this.getEmailInput().fill(email);
    }

    public void enterCurrentAddress(String address) {
        this.getCurrentAddressInput().fill(address);
    }

    public void enterPermanentAddress(String address) {
        this.getPermanentAddressInput().fill(address);
    }

    public void clickSubmit() {
        this.getSubmitButton().click();
    }
}
