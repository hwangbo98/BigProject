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
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "userPK")
public class Alluser implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userPk", updatable = false, unique = true, nullable = false)
    private Long userPK;

    @Column(unique = true, nullable = false)
    private String username; // 로그인id

    @Column(nullable = false)
    private String password; // 비밀번호

    @ManyToOne
    @JoinColumn(name="place_PK")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Place place;

    private String userNickname; // 이름

    private String userAddress; // 주소

    private String userPhoneNumber; //전화번호

    private String profileImg;

    private String userRole; //권한

    private String refreshToken; //토큰?

    // @OneToMany(mappedBy = "userPK", cascade = CascadeType.REMOVE) 
    // private List<Member> memberList; 


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
