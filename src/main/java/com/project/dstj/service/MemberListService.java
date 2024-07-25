package com.project.dstj.service;

import com.project.dstj.dto.MemberListDto;
import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Attendance;
import com.project.dstj.entity.Member;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberListService {
    private final MemberRepository memberRepository;
    private final AlluserRepository alluserRepository;

    public MemberListService(MemberRepository memberRepository, AlluserRepository alluserRepository) {
        this.memberRepository = memberRepository;
        this.alluserRepository = alluserRepository;
    }

    public Alluser findByUsername(String username) {
        return alluserRepository.findByUsername(username).orElse(null);
    }


    public List<MemberListDto> getMemberListByPlacePK(Long placePK) {
        return memberRepository.findMembersWithAttendanceByPlacePK(placePK).stream()
                .map(member -> {
                    Attendance attendance = member.getAttendance().isEmpty() ? null : member.getAttendance().get(0);
                    return MemberListDto.toDto(member, attendance);
                })
                .collect(Collectors.toList());
    }



}
