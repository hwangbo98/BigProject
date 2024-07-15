package com.project.dstj.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "userPK")
public class Alluser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userpk", updatable = false, unique = true, nullable = false)
    private Long userPK;

    @Column(nullable = false)
    private String password;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_phone_number")
    private String userPhoneNumber;

    @Column(name = "user_role")
    private String userRole;

    @Column(unique = true, nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "placepk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Place place;

    // If you also need to reference `place_pk`
    @Column(name = "place_pk")
    private Long placePk;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userRole));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
