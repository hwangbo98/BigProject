package com.project.dstj.dto;

import com.project.dstj.entity.Edu;
import com.project.dstj.entity.Worker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EduDto {
    private Long eduPK;
    private String eduDay;
    private String eduEnd;
    private String eduName;
    private String eduStart;
    private Long placePK;
    private Long workerPK;
    private String userNickname;
    private String username;

    public static EduDto toDto(Edu edu) {
        EduDto eduDto = new EduDto();
        eduDto.setEduPK(edu.getEduPK());
        eduDto.setEduDay(edu.getEduDay());
        eduDto.setEduEnd(edu.getEduEnd());
        eduDto.setEduName(edu.getEduName());
        eduDto.setEduStart(edu.getEduStart());
        eduDto.setPlacePK(edu.getPlace().getPlacePK());
        eduDto.setWorkerPK(edu.getWorker().getWorkerPK());
        eduDto.setUserNickname(edu.getWorker().getAlluser().getUserNickname());
        eduDto.setUsername(edu.getWorker().getAlluser().getUsername());
        return eduDto;
    }
}
