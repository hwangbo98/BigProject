package com.project.dstj.dto;

import com.project.dstj.entity.Edu;
import com.project.dstj.entity.Place;
import com.project.dstj.entity.Worker;

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

    public Edu toEntity(){
        Edu eduEntity = new Edu();
        eduEntity.setEduPK(this.eduPK);
        eduEntity.setEduDay(this.eduDay);
        eduEntity.setEduEnd(this.eduEnd);
        eduEntity.setEduName(this.eduName);
        eduEntity.setEduStart(this.eduStart);

        if (this.placePK != null){
            Place place = new Place();
            place.setPlacePK(this.placePK);
            eduEntity.setPlace(place);
        }

        if (this.workerPK != null) {
            Worker worker = new Worker();
            worker.setWorkerPK(this.workerPK);
            eduEntity.setWorker(worker);
        }

        return eduEntity;
    }

    public static EduDto toDto(Edu eduEntity){
        EduDto eduDto = new EduDto();
        eduDto.setEduPK(eduEntity.getEduPK());
        eduDto.setEduDay(eduEntity.getEduDay());
        eduDto.setEduEnd(eduEntity.getEduEnd());
        eduDto.setEduName(eduEntity.getEduName());
        eduDto.setEduStart(eduEntity.getEduStart());
        eduDto.setPlacePK(eduEntity.getPlace().getPlacePK());
        eduDto.setWorkerPK(eduEntity.getWorker().getWorkerPK());
        return eduDto;
    }
}
