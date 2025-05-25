package com.first.codingplatformapi.controller;

import com.first.codingplatformapi.service.GeeksForGeeksAPIService;
import com.first.codingplatformapi.service.GeeksForGeeksScraperService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/gfg")
@CrossOrigin(origins = "http://localhost:5173")
public class GeeksForGeeksController {
    private final GeeksForGeeksScraperService gfgService;
    private final GeeksForGeeksAPIService gfgAPIService;

    public GeeksForGeeksController(GeeksForGeeksScraperService gfgService, GeeksForGeeksAPIService gfgAPIService) {
        this.gfgService = gfgService;
        this.gfgAPIService = gfgAPIService;
    }

    @GetMapping("/user_profile/{username}")
    public Map<String, Object> userProfile(@PathVariable String username) {
        return gfgService.scrapeUserProfile(username);
    }

    @GetMapping("/pod")
    public Map pod() {
        return gfgService.findPODDetails();
    }

    @GetMapping("/problem-solved/{username}")
    public Map<String, Integer> getProblemsSolved(@PathVariable String username) {
        return gfgAPIService.getProblemsSolved(username);
    }
}
