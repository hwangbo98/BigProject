package com.project.dstj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Attendance;
import com.project.dstj.entity.Member;
import com.project.dstj.entity.Place;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.AttendanceRepository;
import com.project.dstj.repository.MemberRepository;
import com.project.dstj.repository.PlaceRepository;
import com.project.dstj.security.JwtTokenProvider;


@Service
public class AddMemberService {
    @Autowired
    private AlluserRepository allUserRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Place getPlacePKByToken(String token){
        String loginUser = jwtTokenProvider.getUsernameFromJWT(token);
        Alluser user = allUserRepository.findByUsername(loginUser)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Long placePK = user.getPlacePK();
        Place place = placeRepository.findByPlacePK(placePK).orElseThrow(() -> new RuntimeException("Place not found"));
        return place;
    }

    public void saveMember(Alluser alluser, Member member, Attendance attendance){
        Alluser savedAlluser = allUserRepository.save(alluser);
        member.setAlluser(savedAlluser);
        Member savedMember = memberRepository.save(member);
        attendance.setMember(savedMember);;
        attendanceRepository.save(attendance);
    }
}
