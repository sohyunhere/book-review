package com.example.bookreview.batch.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChartService {

    LocalDate today = LocalDate.now();
    LocalDate yesterday = today.minusDays(1);
    LocalDate lastday = today.minusDays(7);
    List<String> days;

    public List<String> findLabels(){
        days = new ArrayList<>();

        for(int i = 7; i >= 1; i--) {
            LocalDate d = today.minusDays(i);
            days.add(String.valueOf(d));
        }
        return days;
    }

    public List<Integer> literatureData(){
        List<Integer> data = new ArrayList<>();
        for(int i = 0; i < days.size(); i++){

        }

    }
}
