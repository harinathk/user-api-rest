DROP TABLE user IF EXISTS;

CREATE TABLE user (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  salary     DOUBLE
);


