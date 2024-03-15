package com.example.kkm.user.profile.domain.model;

import com.example.kkm.user.domain.entity.User;
import com.example.kkm.user.profile.domain.ProfileInput;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Profile extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nickname;
    private String dogSize;
    private String zipCode;
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    public static Profile form(ProfileInput form){
        return Profile.builder()
            .nickname(form.getNickname())
            .dogSize(form.getDogSize())
            .zipCode(form.getZipCode())
            .address(form.getAddress())
            .build();
    }
}
