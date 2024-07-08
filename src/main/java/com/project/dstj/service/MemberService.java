package com.project.dstj.service;

import com.project.dstj.dto.JwtToken;
import com.project.dstj.dto.MemberDto;
import com.project.dstj.dto.SignUpDto;
import com.project.dstj.entity.Member;
import com.project.dstj.repository.MemberRepository;
import com.project.dstj.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService{
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public JwtToken signIn(String username, String password) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setRefreshToken(jwtToken.getRefreshToken());
            memberRepository.save(member); // 변경 사항을 데이터베이스에 저장
        }

        return jwtToken;
    }

    @Transactional
    public MemberDto signUp(SignUpDto signUpDto) {
        if (memberRepository.existsByUsername(signUpDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }
        // Password 암호화
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        String role = signUpDto.getRole();  // USER 권한 부여
        return MemberDto.toDto(memberRepository.save(signUpDto.toEntity(encodedPassword, role)));
    }
}
