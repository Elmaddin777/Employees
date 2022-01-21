package com.bank.employees.mapper;

import com.bank.employees.dto.LocationDto;
import com.bank.employees.entity.Location;

public class LocationMapper {

    public static Location toLocation(LocationDto locationDto){
        if (locationDto == null)
            return null;

        return Location.builder()
                .Id(locationDto.getId())
                .branch(locationDto.getBranch())
                .city(locationDto.getCity())
                .build();
    }

    public static LocationDto toLocationDto(Location location){
        if (location == null)
            return null;

        return LocationDto.builder()
                .Id(location.getId())
                .branch(location.getBranch())
                .city(location.getCity())
                .build();
    }
}
