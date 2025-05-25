package com.first.codingplatformapi.service;

import com.first.codingplatformapi.utilities.GraphQLRequestSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeetCodeService {
    public Map<String, Object> getPOD() throws Exception {
        return GraphQLRequestSender.sendGraphQLRequest("graphql/leetcode-pod.graphql");
    }

    public Map<String, Object> getUserProfile(String username) throws Exception {
        return GraphQLRequestSender.sendGraphQLRequest("graphql/leetcode-user-profile.graphql", Map.of("username", username));
    }

    public Map<String, Object> getUserProblemsSolved(String username) throws Exception {
        Map<String, Object> data = (Map<String, Object>) GraphQLRequestSender.sendGraphQLRequest("graphql/leetcode-problems-solved.graphql", Map.of("username", username)).get("data");
        Map<String, Object> problems = new HashMap<>();

        List totalObject = (List) data.get("allQuestionsCount");
        for (Object difficultyObj : totalObject) {
            if (difficultyObj instanceof Map<?, ?> difficultyMap) {
                String difficulty = (String) difficultyMap.get("difficulty");
                int count = (int) difficultyMap.get("count");

                switch (difficulty) {
                    case "Easy":
                        problems.put("easyTotal", count);
                        break;

                    case "Medium":
                        problems.put("mediumTotal", count);
                        break;

                    case "Hard":
                        problems.put("hardTotal", count);
                        break;
                }
            }
        }

        List solvedObject = (List) (((Map) (((Map) (data.get("matchedUser"))).get("submitStats"))).get("acSubmissionNum"));
        for (Object difficultyObj : solvedObject) {
            if (difficultyObj instanceof Map<?, ?> difficultyMap) {
                String difficulty = (String) difficultyMap.get("difficulty");
                int count = (int) difficultyMap.get("count");

                switch (difficulty) {
                    case "Easy":
                        problems.put("easySolved", count);
                        break;

                    case "Medium":
                        problems.put("mediumSolved", count);
                        break;

                    case "Hard":
                        problems.put("hardSolved", count);
                        break;
                }
            }
        }
        return problems;
    }
}
