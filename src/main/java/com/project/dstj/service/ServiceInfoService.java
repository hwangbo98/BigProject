package com.project.dstj.service;

import com.project.dstj.dto.ServiceDto;
import com.project.dstj.entity.Service;
import com.project.dstj.entity.Alluser;
import com.project.dstj.repository.ServiceRepository;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service //@Service와 이름이 같아서 @Service 전체링크 받아옴
public class ServiceInfoService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AlluserRepository alluserRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public List<ServiceDto> getServicesByToken(String token) {
        String username = jwtTokenProvider.getUsernameFromJWT(token); // 토큰에서 사용자 이름 추출
        Alluser user = alluserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")); // 사용자 이름으로 Alluser 엔티티 조회

        Long placePK = user.getPlacePK(); // Alluser 엔티티에서 placePK 추출
        return getServicesByPlacePK(placePK); // placePK로 Service 엔티티 리스트 조회
    }

    private List<ServiceDto> getServicesByPlacePK(Long placePK) {
        List<Service> services = serviceRepository.findByPlace_PlacePK(placePK);
        return services.stream().map(ServiceDto::toDto).collect(Collectors.toList());
    }

    public ServiceDto createService(ServiceDto serviceDto) {
        Service service = serviceDto.toEntity(); // DTO를 엔티티로 변환
        service = serviceRepository.save(service); // 엔티티를 데이터베이스에 저장
        return ServiceDto.toDto(service); // 저장된 엔티티를 DTO로 변환하여 반환
    }
}
