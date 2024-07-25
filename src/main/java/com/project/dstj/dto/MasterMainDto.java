package com.project.dstj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterMainDto {

    private List<EduRevenueDto> eduRevenueList;
    private List<MonthlyEduRevenueDto> monthlyEduRevenueList;
    private List<MonthlyRegistrationDto> monthlyRegistrationList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EduRevenueDto {
        private Long eduPK;
        private String eduName;
        private Double totalRevenue;
        private Long studentCount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MonthlyEduRevenueDto {
        private String month;
        private Double revenue;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MonthlyRegistrationDto {
        private String month;
        private Long registrationCount;
    }
}
