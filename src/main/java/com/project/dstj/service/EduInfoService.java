package com.project.dstj.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dstj.dto.EduDto;
import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Edu;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.EduRepository;
import com.project.dstj.security.JwtTokenProvider;

@Service
public class EduInfoService {
    @Autowired
    private AlluserRepository alluserRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private EduRepository eduRepository;

    public List<EduDto> getEduByToken(String token) {
        String username = jwtTokenProvider.getUsernameFromJWT(token); // 토큰에서 사용자 이름 추출
        Alluser user = alluserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")); // 사용자 이름으로 Alluser 엔티티 조회

        Long placePK = user.getPlacePK(); // Alluser 엔티티에서 placePK 추출
        return getEduByPlacePK(placePK); // placePK로 Service 엔티티 리스트 조회
    }

    private List<EduDto> getEduByPlacePK(Long placePK) {
        List<Edu> edu = eduRepository.findByPlace_PlacePK(placePK);
        return edu.stream().map(EduDto::toDto).collect(Collectors.toList());
    }

    public EduDto createService(EduDto eduDto) {
        Edu edu = eduDto.toEntity(); // DTO를 엔티티로 변환
        edu = eduRepository.save(edu); // 엔티티를 데이터베이스에 저장
        return EduDto.toDto(edu); // 저장된 엔티티를 DTO로 변환하여 반환
    }
}
