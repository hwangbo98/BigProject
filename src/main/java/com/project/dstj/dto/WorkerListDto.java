package com.project.dstj.dto;

import com.project.dstj.entity.Alluser;
import com.project.dstj.entity.Place;
import com.project.dstj.entity.Worker;
import com.project.dstj.entity.Worktime;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class WorkerListDto {
    // 직원 출퇴근, 월급 정보
    private String userNickName;
    private LocalDate worktimeDay;
    private LocalTime worktimeStart;
    private LocalTime worktimeEnd;
    private Long placePk;
    private Long workerPk;
    private Integer workerSalary;

    // Entity -> DTO 변환
    public static WorkerListDto toDto(Worktime worktime) {
        WorkerListDto dto = new WorkerListDto();
        dto.setUserNickName(worktime.getWorker().getAlluser().getUserNickname());
        dto.setWorktimeDay(worktime.getWorktimeDay());
        dto.setWorktimeStart(worktime.getWorktimeStart());
        dto.setWorktimeEnd(worktime.getWorktimeEnd());
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
