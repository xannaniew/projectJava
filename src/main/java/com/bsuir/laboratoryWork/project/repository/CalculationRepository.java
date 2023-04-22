package com.bsuir.laboratoryWork.project.repository;

import com.bsuir.laboratoryWork.project.model.CalculationData;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends CrudRepository<CalculationData,ParametersKey> {
    @Modifying
    @Query("DELETE FROM CalculationData c WHERE c.id.rectangleLength = :length1 AND c.id.rectangleHeight = :height1 OR c.id.rectangleLength = :length2 AND c.id.rectangleHeight = :height2")
    void deleteById_rectangleLengthAndId_rectangleHeightOrId_rectangleHeightAndId_rectangleLength(int length1, int height1, int length2, int height2);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CalculationData c WHERE (c.id.rectangleLength = :length1 AND c.id.rectangleHeight = :height1) OR (c.id.rectangleLength = :length2 AND c.id.rectangleHeight = :height2)")
    boolean existsById_RectangleLengthAndId_RectangleHeightOrId_RectangleHeightAndId_RectangleLength(int length1, int height1, int length2,int height2);
}
