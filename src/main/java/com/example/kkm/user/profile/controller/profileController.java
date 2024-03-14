package com.example.kkm.user.profile.controller;

import com.example.kkm.user.profile.service.domain.ProfileDto;
import com.example.kkm.user.profile.service.domain.ProfileInput;
import com.example.kkm.user.profile.service.domain.repository.ProfileRepository;
import com.example.kkm.user.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class profileController {

    private final ProfileService profileService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileDto> getUserProfile(@PathVariable Long id) {
        return ResponseEntity.ok(ProfileDto.from(profileService.getUserProfile(id)));
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable Long id,
        @RequestBody ProfileInput input) {
        return ResponseEntity.ok(ProfileDto.from(profileService.updateUserProfile(id, input)));
    }
}
