create table calculationTable (
    rectangleLength INT NOT NULL,
    rectangleHeight INT NOT NULL,
    rectanglePerimeter INT NOT NULL,
    rectangleSquare INT NOT NULL,
    CONSTRAINT data_pk
    PRIMARY KEY(rectanglePerimeter,rectangleSquare)
);