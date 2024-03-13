package com.example.kkm.user.profile.service.domain;


import com.example.kkm.user.profile.service.domain.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfileDto {
    private Long id;
    private String nickname;
    private String dogSize;
    private String zipCode;
    private String address;

    public static ProfileDto from(Profile profile){
        return new ProfileDto(
            profile.getId(),
            profile.getNickname(),
            profile.getDogSize(),
            profile.getZipCode(),
            profile.getAddress()
        );
    }


}
