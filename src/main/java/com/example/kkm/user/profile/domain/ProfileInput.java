package com.example.kkm.user.profile.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileInput {
    private String nickname;
    private String dogSize;
    private String zipCode;
    private String address;
}
