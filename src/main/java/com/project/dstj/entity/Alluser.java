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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "userPK")
public class Alluser implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userPK", updatable = false, unique = true, nullable = false)
    private Long userPK;

    @Column(unique = true, nullable = false)
    private String username; // 로그인id

    @Column(nullable = false)
    private String password; // 비밀번호

    @ManyToOne
    @JoinColumn(name="placePK")       //07.11 place_PK -> placePK
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

    @OneToMany(mappedBy = "alluser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Worker> workers;

    // placePK를 반환하는 메서드
    public Long getPlacePK() {
        return place != null ? place.getPlacePK() : null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userRole));
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (userRole == null || userRole.isEmpty()) {
//            throw new IllegalArgumentException("A granted authority textual representation is required");
//        }
//        return Collections.singletonList(new SimpleGrantedAuthority(userRole));
//    }

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
