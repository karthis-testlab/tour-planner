DROP TABLE IF EXISTS TEST;

CREATE TABLE journey_information(
      id IDENTITY NOT NULL PRIMARY KEY,
      city VARCHAR(100),
      from_date DATE,
      to_date DATE
);