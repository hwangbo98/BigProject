package com.project.dstj.service;

import com.project.dstj.dto.MasterMainDto;
import com.project.dstj.entity.Alluser;
import com.project.dstj.repository.AlluserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterMainService {

    private final AlluserRepository alluserRepository;
    private final EntityManager entityManager;

    public MasterMainService(AlluserRepository alluserRepository, EntityManager entityManager) {
        this.alluserRepository = alluserRepository;
        this.entityManager = entityManager;
    }

    public Alluser findByUsername(String username) {
        return alluserRepository.findByUsername(username).orElse(null);
    }

    public List<MasterMainDto.EduRevenueDto> calculateEduRevenueByPlacePK(Long placePK) {
        String sql = "SELECT e.edupk AS eduPK, e.edu_name AS eduName, SUM(e.edu_tuition) AS totalRevenue, COUNT(t.takespk) AS studentCount " +
                "FROM takes t " +
                "JOIN edu e ON t.edupk = e.edupk " +
                "JOIN member m ON t.memberpk = m.memberpk " +
                "JOIN alluser a ON m.userpk = a.userpk " +
                "WHERE a.placepk = :placePK AND a.user_role = 'member' " +
                "GROUP BY e.edupk, e.edu_name";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("placePK", placePK);

        List<Object[]> results = query.getResultList();
        return results.stream()
                .map(row -> new MasterMainDto.EduRevenueDto(
                        ((Number) row[0]).longValue(),
                        (String) row[1],
                        ((Number) row[2]).doubleValue(),
                        ((Number) row[3]).longValue()))
                .collect(Collectors.toList());
    }

    public List<MasterMainDto.MonthlyEduRevenueDto> calculateMonthlyEduRevenueByPlacePK(Long placePK) {
        String sql = "SELECT DATE_FORMAT(t.takes_adddate, '%Y-%m') AS month, SUM(e.edu_tuition) AS revenue " +
                "FROM takes t " +
                "JOIN edu e ON t.edupk = e.edupk " +
                "JOIN member m ON t.memberpk = m.memberpk " +
                "JOIN alluser a ON m.userpk = a.userpk " +
                "WHERE a.placepk = :placePK AND a.user_role = 'member' " +
                "GROUP BY DATE_FORMAT(t.takes_adddate, '%Y-%m')";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("placePK", placePK);

        List<Object[]> results = query.getResultList();
        return results.stream()
                .map(row -> new MasterMainDto.MonthlyEduRevenueDto(
                        (String) row[0],
                        ((Number) row[1]).doubleValue()))
                .collect(Collectors.toList());
    }

    public List<MasterMainDto.MonthlyRegistrationDto> calculateMonthlyRegistrationsByPlacePK(Long placePK) {
        String sql = "SELECT DATE_FORMAT(t.takes_adddate, '%Y-%m') AS month, COUNT(t.takespk) AS registration_count " +
                "FROM takes t " +
                "JOIN edu e ON t.edupk = e.edupk " +
                "JOIN member m ON t.memberpk = m.memberpk " +
                "JOIN alluser a ON m.userpk = a.userpk " +
                "WHERE a.placepk = :placePK AND a.user_role = 'member' " +
                "GROUP BY DATE_FORMAT(t.takes_adddate, '%Y-%m')";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("placePK", placePK);

        List<Object[]> results = query.getResultList();
        return results.stream()
                .map(row -> new MasterMainDto.MonthlyRegistrationDto(
                        (String) row[0],
                        ((Number) row[1]).longValue()))
                .collect(Collectors.toList());
    }
}
