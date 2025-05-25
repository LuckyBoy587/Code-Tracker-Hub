package com.first.codingplatformapi.service;

import com.first.codingplatformapi.entity.Profile;
import com.first.codingplatformapi.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile onUserLogin(Profile.ProfileCreationDetails profileCreationDetails) {
        if (profileCreationDetails.email() == null || profileCreationDetails.email().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (profileRepository.existsByEmail(profileCreationDetails.email())) {
            return profileRepository.findByEmail(profileCreationDetails.email());
        }

        Profile profile = new Profile(profileCreationDetails);
        profileRepository.save(profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}
