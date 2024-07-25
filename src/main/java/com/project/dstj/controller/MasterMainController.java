//package com.project.dstj.controller;
//
//import com.project.dstj.dto.MasterMainDto;
//import com.project.dstj.entity.Alluser;
//import com.project.dstj.security.JwtTokenProvider;
//import com.project.dstj.service.MasterMainService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/master")
//public class MasterMainController {
//
//    private final MasterMainService masterMainService;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public MasterMainController(MasterMainService masterMainService, JwtTokenProvider jwtTokenProvider) {
//        this.masterMainService = masterMainService;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @GetMapping("/main")
//    public MasterMainDto getMasterMainData(@RequestHeader("Authorization") String token) {
//        // 토큰에서 Bearer 제거
//        token = token.substring(7);
//
//        // 토큰에서 사용자명 추출
//        String username = jwtTokenProvider.getUsernameFromJWT(token);
//
//        // 사용자 정보 가져오기
//        Alluser user = masterMainService.findByUsername(username);
//
//        if (user == null || !user.getUserRole().equalsIgnoreCase("master")) {
//            return new MasterMainDto(); // 빈 객체 반환
//        }
//
//        // 사용자 정보에서 placePK 추출
//        Long placePK = user.getPlace().getPlacePK();
//
////        // Edu별 수강료와 수강생의 명수를 곱한 값
////        List<MasterMainDto.EduRevenueDto> eduRevenueList = masterMainService.calculateEduRevenueByPlacePK(placePK);
////
////        // 월별 Edu 수강료와 수강생의 명수를 곱한 값
////        List<MasterMainDto.MonthlyEduRevenueDto> monthlyEduRevenueList = masterMainService.calculateMonthlyEduRevenueByPlacePK(placePK);
////
////        // 월별 등록 추이
////        List<MasterMainDto.MonthlyRegistrationDto> monthlyRegistrationList = masterMainService.calculateMonthlyRegistrationsByPlacePK(placePK);
//
//        // 각 계산 결과를 가져오기
////        List<MasterMainDto.EduRevenueDto> eduRevenueList = masterMainService.calculateEduRevenueByPlacePK(placePK);
////        List<MasterMainDto.MonthlyEduRevenueDto> monthlyEduRevenueList = masterMainService.calculateMonthlyEduRevenueByPlacePK(placePK);
////        List<MasterMainDto.MonthlyRegistrationDto> monthlyRegistrationList = masterMainService.calculateMonthlyRegistrationsByPlacePK(placePK);
////
////        // 결과를 DTO로 반환
////        MasterMainDto masterMainDto = new MasterMainDto();
////        masterMainDto.setEduRevenueList(eduRevenueList);
////        masterMainDto.setMonthlyEduRevenueList(monthlyEduRevenueList);
////        masterMainDto.setMonthlyRegistrationList(monthlyRegistrationList);
////
////        return masterMainDto;
//    }
//}


package com.project.dstj.controller;

import com.project.dstj.dto.MasterMainDto;
import com.project.dstj.entity.Alluser;
import com.project.dstj.security.JwtTokenProvider;
import com.project.dstj.service.MasterMainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/masters")
public class MasterMainController {

    private final MasterMainService masterMainService;
    private final JwtTokenProvider jwtTokenProvider;

    public MasterMainController(MasterMainService masterMainService, JwtTokenProvider jwtTokenProvider) {
        this.masterMainService = masterMainService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/main")
    public MasterMainDto getMasterMain(@RequestHeader("Authorization") String token) {
        // 토큰에서 Bearer 제거
        token = token.substring(7);

        // 토큰에서 사용자명 추출
        String username = jwtTokenProvider.getUsernameFromJWT(token);

        // 사용자 정보 가져오기
        Alluser user = masterMainService.findByUsername(username);

        if (user == null || !"Master".equalsIgnoreCase(user.getUserRole())) {
            return new MasterMainDto();
        }

        // 사용자 정보에서 placePK 추출
        Long placePK = user.getPlace().getPlacePK();

        // Edu별 수강료와 수강생의 명수를 곱한 값과 총합을 가져오기
        List<MasterMainDto.EduRevenueDto> eduRevenueList = masterMainService.calculateEduRevenueByPlacePK(placePK);

        // 월별 Edu 수강료를 가져오기
        List<MasterMainDto.MonthlyEduRevenueDto> monthlyEduRevenueList = masterMainService.calculateMonthlyEduRevenueByPlacePK(placePK);

        // 월별 등록 추이를 가져오기
        List<MasterMainDto.MonthlyRegistrationDto> monthlyRegistrationList = masterMainService.calculateMonthlyRegistrationsByPlacePK(placePK);

        // DTO에 값 설정
        MasterMainDto masterMainDto = new MasterMainDto();
        masterMainDto.setEduRevenueList(eduRevenueList);
        masterMainDto.setMonthlyEduRevenueList(monthlyEduRevenueList);
        masterMainDto.setMonthlyRegistrationList(monthlyRegistrationList);

        return masterMainDto;

    }



}

