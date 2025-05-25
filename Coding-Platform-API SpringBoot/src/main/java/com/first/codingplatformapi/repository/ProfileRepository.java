package com.first.codingplatformapi.repository;

import com.first.codingplatformapi.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    boolean existsByEmail(String email);
    Profile findByEmail(String email);
}
