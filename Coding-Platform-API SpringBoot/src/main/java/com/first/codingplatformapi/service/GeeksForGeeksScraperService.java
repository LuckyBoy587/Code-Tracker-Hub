package com.first.codingplatformapi.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeeksForGeeksScraperService {
    private final ChromeDriver driver;

    public GeeksForGeeksScraperService(ChromeDriver driver) {
        this.driver = driver;
    }

    public Map<String, Object> scrapeUserProfile(String username) throws WebDriverException {
        Map<String, Object> userProfile = new LinkedHashMap<>();
        userProfile.put("username", username);
        String BASE_URL = "https://www.geeksforgeeks.org/";
        driver.get(BASE_URL + "user/" + username);
        List<WebElement> cards = driver.findElements(By.cssSelector("div.scoreCard_head__nxXR8"));
        for (WebElement card : cards) {
            List<WebElement> divs = card.findElements(By.tagName("div"));
            String key = null;
            String value = null;

            for (WebElement div : divs) {
                String className = div.getAttribute("class");
                assert className != null;
                if (className.contains("--text")) key = div.getText();
                else if (className.contains("--score")) value = div.getText();
            }

            if (key != null && value != null) {
                userProfile.put(key.toLowerCase(), Integer.parseInt(value));
            }
        }

        WebElement institution = driver.findElement(By.cssSelector("div.educationDetails_head_left--text__tgi9I"));
        if (institution != null) {
            userProfile.put("institution", institution.getText());
        }

        WebElement languagesUsed = driver.findElement(By.cssSelector("div.educationDetails_head_right--text__lLOHI"));
        if (languagesUsed != null) {
            String[] languages = languagesUsed.getText().split(", ");
            userProfile.put("languages", languages);
        }

        Map<String, Integer> problemsSolved = new HashMap<>();
        List<WebElement> problemSolvedDiv = driver.findElements(By.cssSelector("div.problemNavbar_head_nav--text__UaGCx"));
        for (WebElement div : problemSolvedDiv) {
            String text = div.getText();
            String[] parts = text.split(" ");
            String problemType = parts[0];
            Integer solvedCount = Integer.parseInt(parts[1].substring(1, parts[1].length() - 1));
            problemsSolved.put(problemType.toLowerCase(), solvedCount);
        }

        userProfile.put("problems", problemsSolved);

        List<WebElement> contestDetailsCards = driver.findElements(By.cssSelector("span.contestDetailsCard_head_detail__8P4Vo"));
        for (WebElement card : contestDetailsCards) {
            String key = card.findElement(By.tagName("p")).getText().toLowerCase();
            Integer value = Integer.parseInt(card.findElement(By.tagName("span")).getText());
            userProfile.put(key, value);
        }
        return userProfile;
    }

    public Map<String, Object> findPODDetails() {
        RestTemplate template = new RestTemplate();
        String POD_URL = "https://practiceapi.geeksforgeeks.org/api/vr/problems-of-day/problem/today/";
        Map<String, Object> map = template.getForObject(POD_URL, Map.class);
        map.putAll(scrapePOD((String) map.get("problem_url")));
        return map;
    }

    private Map<String, Object> scrapePOD(String pod_url) {
        Map<String, Object> podDetails = new LinkedHashMap<>();
        driver.get(pod_url);
        WebElement questionParaTags = driver.findElement(By.cssSelector("div.problems_problem_content__Xm_eO"));
        WebElement firstParaTag = questionParaTags.findElement(By.tagName("p"));
        WebElement spanTag = firstParaTag.findElement(By.tagName("span"));
        String[] questionLines = spanTag.getAttribute("innerHTML").split("\\.");
        for (int i = 0; i < questionLines.length; i++) {
            questionLines[i] = questionLines[i].strip();
        }
        podDetails.put("description", questionLines);
        return podDetails;
    }
}
