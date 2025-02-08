package com.example.test_project;

import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microsoft.playwright.*;

@SpringBootApplication
public class TestProjectApplication {

	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch();
			Page page = browser.newPage();
			page.navigate("https://playwright.dev/");
			page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
		}
		// SpringApplication.run(TestProjectApplication.class, args);
	}

}
