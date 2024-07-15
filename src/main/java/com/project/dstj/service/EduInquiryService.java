package com.project.dstj.service;

import com.project.dstj.entity.Edu;
import com.project.dstj.entity.Member;
import com.project.dstj.entity.Takes;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.EduRepository;
import com.project.dstj.repository.MemberRepository;
import com.project.dstj.repository.TakesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EduInquiryService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EduRepository eduRepository;
    @Autowired
    private TakesRepository takesRepository;
    @Autowired
    private AlluserRepository alluserRepository;

    public List<Member> getMembersByEduPk(Long eduPk) {
        Edu edu = eduRepository.findById(eduPk)
                .orElseThrow(() -> new RuntimeException("Edu not found"));

        List<Takes> allTakes = takesRepository.findByEduEduPK(edu.getEduPK());
        List<Member> members = allTakes.stream()
                .map(Takes::getMember)
                .collect(Collectors.toList());
        return members;
    }
}
