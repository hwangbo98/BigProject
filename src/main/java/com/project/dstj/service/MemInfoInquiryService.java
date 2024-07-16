package com.project.dstj.service;

import com.project.dstj.dto.MemberInfoInquiryDto;
import com.project.dstj.dto.UserInfoDto;
import com.project.dstj.entity.Ac;
import com.project.dstj.entity.Hc;
import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Test;
import com.project.dstj.repository.AcRepository;
import com.project.dstj.repository.HcRepository;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemInfoInquiryService {

    @Autowired
    private AcRepository acRepository;

    @Autowired
    private HcRepository hcRepository;

    @Autowired
    private AlluserRepository alluserRepository;

    @Autowired
    private TestRepository testRepository;

    public MemberInfoInquiryDto getMemberDetails(Long userPK) {
        Optional<Alluser> alluserOptional = alluserRepository.findById(userPK);
        if (alluserOptional.isEmpty()) {
            return null; // 또는 적절한 예외 처리
        }

        Alluser alluser = alluserOptional.get();

        Ac ac = acRepository.findByMemberAlluserUserPK(userPK).stream().findFirst().orElse(null);
        Hc hc = hcRepository.findByMemberAlluserUserPK(userPK).stream().findFirst().orElse(null);
        Test test = testRepository.findByTakesMemberAlluserUserPK(userPK).stream().findFirst().orElse(null);

        UserInfoDto userInfoDto = UserInfoDto.toDto(alluser, ac, hc, test);

        return MemberInfoInquiryDto.builder()
                .meminfo(userInfoDto)
                .build();
    }
}
