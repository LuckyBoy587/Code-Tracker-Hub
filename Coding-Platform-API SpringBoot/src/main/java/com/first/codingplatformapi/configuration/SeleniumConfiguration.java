package com.first.codingplatformapi.configuration;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {

    @PostConstruct
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromium\\chromedriver-win64\\chromedriver.exe");
    }

    @Bean
    public ChromeDriver chromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }
}
