package com.first.codingplatformapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table
public class Profile {
    @Id
    private String email;
    private String name;
    private String leetcode_username;
    private String gfg_username;
    private String profile_picture_url;

    protected Profile() { }

    public Profile(ProfileCreationDetails profileCreationDetails) {
        this.email = profileCreationDetails.email;
        this.name = profileCreationDetails.name;
        this.profile_picture_url = profileCreationDetails.picture;
    }

    public record ProfileCreationDetails(String email, String name, String picture) {
    }
}
