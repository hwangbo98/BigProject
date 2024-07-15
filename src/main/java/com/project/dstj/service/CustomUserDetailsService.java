package com.project.dstj.service;

import com.project.dstj.entity.Alluser;
import com.project.dstj.repository.AlluserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AlluserRepository alluserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 정보 조회
        Alluser alluser = alluserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("worker");
        // UserDetails 객체로 변환
        return new org.springframework.security.core.userdetails.User(
                alluser.getUsername(),
                alluser.getPassword(), // 암호화된 비밀번호
                authorities
        );
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 return
    private UserDetails createUserDetails(Alluser alluser) {
        return User.builder()
                .username(alluser.getUsername())
                .password(passwordEncoder.encode(alluser.getPassword()))
                .roles(alluser.getUserRole())
                .build();
    }

}