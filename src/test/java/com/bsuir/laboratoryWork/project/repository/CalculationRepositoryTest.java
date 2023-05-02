package com.bsuir.laboratoryWork.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
@DataJpaTest
@SpringJUnitConfig
class CalculationRepositoryTest {
    @Autowired
    private CalculationRepository repository;

    @Test
    void deleteById_rectangleLengthAndId_rectangleHeightOrId_rectangleHeightAndId_rectangleLength() {
//        CalculationData data = new CalculationData(new ParametersKey(10,15),50,150);
//        ParametersKey keyEqual = new ParametersKey(15,10);
//        repository.save(data);
//        repository.deleteById_rectangleLengthAndId_rectangleHeightOrId_rectangleHeightAndId_rectangleLength(keyEqual.getRectangleLength(), keyEqual.getRectangleHeight(), keyEqual.getRectangleHeight(), keyEqual.getRectangleLength());
//        assertEquals(null,repository.findById(new ParametersKey(10,15)));
    }
}