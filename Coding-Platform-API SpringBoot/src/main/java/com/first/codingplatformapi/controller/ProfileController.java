package com.first.codingplatformapi.controller;

import com.first.codingplatformapi.entity.Profile;
import com.first.codingplatformapi.service.ProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/login")
    public Profile login(@RequestBody Profile.ProfileCreationDetails profileCreationDetails) {
        return profileService.onUserLogin(profileCreationDetails);
    }

    @PostMapping("/update")
    public Profile update(@RequestBody Profile profile) {
        return profileService.updateProfile(profile);
    }
}
