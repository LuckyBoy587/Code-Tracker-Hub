package com.first.codingplatformapi.service;

import com.first.codingplatformapi.utilities.HttpRequestSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class GeeksForGeeksAPIService {
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Integer> getProblemsSolved(String username) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Difficulty difficulty : Difficulty.values()) {
            map.put(difficulty.name() + "Total", getTotalCount(difficulty));
        }

        Map<String, Object> solvedData = (Map<String, Object>) getSolvedData(username).get("result");
        for (Difficulty difficulty : Difficulty.values()) {
            map.put(difficulty.name() + "Solved", ((Map) solvedData.get(difficulty.toTitleCase())).size());
        }

        for (Difficulty difficulty : Difficulty.values()) {
            map.putIfAbsent(difficulty.name() + "Solved", 0);
        }

        return map;
    }

    private Map<String, Object> getSolvedData(String username) {
        String postURL = "https://practiceapi.geeksforgeeks.org/api/v1/user/problems/submissions/";
        return HttpRequestSender.postRequest(postURL, Map.of("handle", username));
    }

    private int getTotalCount(Difficulty difficulty) {
        String url = "https://practiceapi.geeksforgeeks.org/api/vr/problems/?pageMode=explore&difficulty=" + difficulty.value;
        Map<String, Object> map = restTemplate.getForObject(url, Map.class);
        return (int) map.get("total");
    }

    enum Difficulty {
        basic(-1), easy(0), medium(1), hard(2);

        public final int value;

        Difficulty(int value) {
            this.value = value;
        }

        public String toTitleCase() {
            return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }
}
