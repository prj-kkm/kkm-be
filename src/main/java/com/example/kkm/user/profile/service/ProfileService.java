package com.example.kkm.user.profile.service;

import com.example.kkm.user.exception.CustomException;
import com.example.kkm.user.exception.ErrorCode;
import com.example.kkm.user.profile.domain.ProfileInput;
import com.example.kkm.user.profile.domain.model.Profile;
import com.example.kkm.user.profile.domain.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile getUserProfile(Long userId) {
        return profileRepository.findByUser_Id(userId).orElseThrow(
            () -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }


    public Profile updateUserProfile(Long userId, ProfileInput input ){

        Profile profile = profileRepository.findByUser_Id(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        profile.setNickname(input.getNickname());
        profile.setDogSize(input.getDogSize());
        profile.setZipCode(input.getZipCode());
        profile.setAddress(input.getAddress());

        profileRepository.save(profile);
        return profile;
    }

}
