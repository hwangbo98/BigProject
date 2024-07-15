package com.project.dstj.dto;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Place;
import com.project.dstj.entity.Worker;
import com.project.dstj.entity.Worktime;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WorkerListDto {
    private String userNickName;
    private String worktimeDay;
    private String worktimeStart;
    private String worktimeEnd;
    private Long placePk;
    private Long workerPk;
    private Integer workerSalary;

    // Entity -> DTO 변환
    public static WorkerListDto toDto(Worktime worktime) {
        WorkerListDto dto = new WorkerListDto();
        dto.setUserNickName(worktime.getWorker().getAlluser().getUserNickname());
        dto.setWorktimeDay(worktime.getWorktimeDay().toString());
        dto.setWorktimeStart(worktime.getWorktimeStart().toString());
        dto.setWorktimeEnd(worktime.getWorktimeEnd().toString());
        dto.setWorkerSalary(worktime.getWorker().getWorkerSalary());
        dto.setPlacePk(worktime.getWorker().getAlluser().getPlace().getPlacePK());
        dto.setWorkerPk(worktime.getWorker().getWorkerPK());
        return dto;
    }

    // DTO -> Entity 변환
    public Worktime toEntity() {
        Worktime worktime = new Worktime();
        worktime.setWorktimeDay(this.worktimeDay);
        worktime.setWorktimeStart(this.worktimeStart);
        worktime.setWorktimeEnd(this.worktimeEnd);

        Worker worker = new Worker();
        worker.setWorkerPK(this.workerPk);
        worker.setWorkerSalary(this.workerSalary);

        Alluser alluser = new Alluser();
        alluser.setUserNickname(this.userNickName);

        Place place = new Place();
        place.setPlacePK(this.placePk);

        alluser.setPlace(place);
        worker.setAlluser(alluser);

        worktime.setWorker(worker);

        return worktime;
    }

}
