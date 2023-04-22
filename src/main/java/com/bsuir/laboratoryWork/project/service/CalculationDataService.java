package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.model.CalculationData;
import com.bsuir.laboratoryWork.project.repository.CalculationRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculationDataService {
    @Autowired
    private CalculationRepository repository;
    @Transactional
    public void addNewCalculationData(CalculationData data){
        if(!repository.existsById_RectangleLengthAndId_RectangleHeightOrId_RectangleHeightAndId_RectangleLength(data.getId().getRectangleLength(), data.getId().getRectangleHeight(), data.getId().getRectangleHeight(),data.getId().getRectangleLength())) {
            repository.save(data);
        }
    }
    public Iterable<CalculationData> getAllData(){
        return repository.findAll();
    }
    @Transactional
    public void deleteCalculationData(CalculationData data){
//        repository.deleteById(data.getId());
        repository.deleteById_rectangleLengthAndId_rectangleHeightOrId_rectangleHeightAndId_rectangleLength(data.getId().getRectangleLength(), data.getId().getRectangleHeight(), data.getId().getRectangleHeight(),data.getId().getRectangleLength());
    }
}
