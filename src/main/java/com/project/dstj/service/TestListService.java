package com.project.dstj.service;

import com.project.dstj.dto.TestListDto;
import com.project.dstj.entity.Alluser;
import com.project.dstj.repository.AlluserRepository;
import com.project.dstj.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestListService {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private AlluserRepository alluserRepository;

    public Alluser findByUsername(String username) {
        Optional<Alluser> user = alluserRepository.findByUsername(username);
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<TestListDto> getMemberReportByPlacePkAndMemberPk(Long placePK, Long memberPK) {
        return testRepository.findByTakes_Member_Alluser_Place_PlacePKAndTakes_Member_MemberPK(placePK, memberPK)
                .stream()
                .map(TestListDto::toDto)
                .collect(Collectors.toList());
    }
}
