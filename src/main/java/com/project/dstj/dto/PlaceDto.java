package com.project.dstj.dto;

import com.project.dstj.entity.Place;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceDto {
    private Long placePK;
    private String placeName;
    private String placeType;

    public Place toEntity() {
        Place placeEntity = new Place();
        placeEntity.setPlacePK(this.placePK);
        placeEntity.setPlaceName(this.placeName);
        placeEntity.setPlaceType(this.placeType);

        return placeEntity;
    }

    public static PlaceDto toDto(Place placeEntity){
        PlaceDto placeDto = new PlaceDto();
        placeDto.setPlacePK(placeEntity.getPlacePK());
        placeDto.setPlaceName(placeEntity.getPlaceName());
        placeDto.setPlaceType(placeEntity.getPlaceType());
        return placeDto;
    }
}
