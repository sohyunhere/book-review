package com.example.bookreview.location.service;

import com.example.bookreview.location.model.Location;
import com.example.bookreview.location.model.LocationDto;
import com.example.bookreview.location.repo.LocationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {
    private final LocationRepo locationRepo;

    //위치 생성
    @Transactional
    public Location registerLocation(LocationDto dto){
        Location location = dto.toEntity();
        Location savedLocation = locationRepo.save(location);

        return savedLocation;
    }
}
