package com.project.dstj.dto;

import com.project.dstj.entity.Place;
import com.project.dstj.entity.Service;
import com.project.dstj.entity.Worker;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDto {
    private Long servicePK;
    private String serviceDay;
    private String serviceEnd;
    private String serviceName;
    private String serviceStart;
    private Long placePK;
    private Long workerPK;

    public Service toEntity() {
        Service serviceEntity = new Service();
        serviceEntity.setServicePK(this.servicePK);
        serviceEntity.setServiceDay(this.serviceDay);
        serviceEntity.setServiceEnd(this.serviceEnd);
        serviceEntity.setServiceName(this.serviceName);
        serviceEntity.setServiceStart(this.serviceStart);

        if (this.placePK != null) {
            Place place = new Place();
            place.setPlacePK(this.placePK);
            serviceEntity.setPlace(place);
        }

        if (this.workerPK != null) {
            Worker worker = new Worker();
            worker.setWorkerPK(this.workerPK);
            serviceEntity.setWorker(worker);
        }

        return serviceEntity;
    }

    public static ServiceDto toDto(Service serviceEntity) {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setServicePK(serviceEntity.getServicePK());
        serviceDto.setServiceDay(serviceEntity.getServiceDay());
        serviceDto.setServiceEnd(serviceEntity.getServiceEnd());
        serviceDto.setServiceName(serviceEntity.getServiceName());
        serviceDto.setServiceStart(serviceEntity.getServiceStart());
        serviceDto.setPlacePK(serviceEntity.getPlace().getPlacePK());
        serviceDto.setWorkerPK(serviceEntity.getWorker().getWorkerPK());
        return serviceDto;
    }
}
