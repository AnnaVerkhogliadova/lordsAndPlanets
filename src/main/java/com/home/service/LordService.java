package com.home.service;

import com.home.model.Lord;
import com.home.repository.LordRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LordService {

    public final LordRepository lordRepository;

    public LordService(LordRepository lordRepository) {
        this.lordRepository = lordRepository;
    }

    public Lord createLord(String name, int age) {
        return lordRepository.save(new Lord(name, age));
    }

    public List<Lord> getLords(Integer topYoungest, Integer planetsCount) {
        Pageable sortedByAge =
                Optional.ofNullable(topYoungest)
                        .map(ty -> (Pageable) PageRequest.ofSize(ty).withSort(Sort.by("age")))
                        .orElse(Pageable.unpaged());
        if (planetsCount != null) {
            return lordRepository.getLordWithoutPlanets(planetsCount, sortedByAge).toList();
        }
        return lordRepository.getYoungestLords(sortedByAge).toList();

    }
}


