package com.learning.test_project.config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PlaywrightConfig {

    @Value("${playwright.base.url}")
    private String baseUrl;

    @Bean(destroyMethod = "close")
    public Playwright playwright() {
        return Playwright.create();
    }

    @Bean(destroyMethod = "close")
    public Browser browser(Playwright playwright) {
        return playwright.chromium().launch();
    }

    @Bean
    @Scope("prototype") // Creates a new instance for each test
    public BrowserContext browserContext(Browser browser) {
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setBaseURL(baseUrl));
        browserContext.setDefaultTimeout(60000);
        return browserContext;
    }

    @Bean
    @Scope("prototype")
    public Page page(BrowserContext browserContext) {
        return browserContext.newPage();
    }
}
