package com.first.codingplatformapi.controller;

import com.first.codingplatformapi.service.LeetCodeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/leetcode")
public class LeetCodeController {
    private final LeetCodeService leetCodeService;

    public LeetCodeController(LeetCodeService leetCodeService) {
        this.leetCodeService = leetCodeService;
    }

    @GetMapping("/pod")
    public ResponseEntity<Object> pod() {
        try {
            Object data = leetCodeService.getPOD().get("data");
            if (data instanceof Map<?, ?> mapObject) {
                return ResponseEntity.ok(mapObject.get("activeDailyCodingChallengeQuestion"));
            }
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Unable to connect", HttpStatusCode.valueOf(404));
        }
    }

    @GetMapping("/user_profile/{username}")
    public ResponseEntity<Object> userProfile(@PathVariable String username) {
        try {
            Object data = leetCodeService.getUserProfile(username).get("data");
            if (data instanceof Map<?, ?> mapObject) {
                return ResponseEntity.ok(mapObject.get("matchedUser"));
            }
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Unable to connect", HttpStatusCode.valueOf(404));
        }
    }

    @GetMapping("/problems-solved/{username}")
    public ResponseEntity<Object> getProblemsSolved(@PathVariable String username) {
        try {
            Map<String, Object> data = leetCodeService.getUserProblemsSolved(username);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Unable to connect", HttpStatusCode.valueOf(404));
        }
    }
}
