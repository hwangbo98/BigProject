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
    private final TestRepository testRepository;
    private final AlluserRepository alluserRepository;

    public TestListService(TestRepository testRepository, AlluserRepository alluserRepository) {
        this.testRepository = testRepository;
        this.alluserRepository = alluserRepository;
    }

    public Alluser findByUsername(String username) {
        return alluserRepository.findByUsername(username).orElse(null);
    }

    public List<TestListDto> getMemberReportByPlacePKAndUserPK(Long placePK, Long userPK) {
        return testRepository.findByTakes_Member_Alluser_Place_PlacePKAndTakes_Member_Alluser_UserPK(placePK, userPK)
                .stream()
                .map(TestListDto::toDto)
                .collect(Collectors.toList());
    }
}
