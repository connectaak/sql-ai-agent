CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    join_year INT
);

INSERT INTO employees (name, join_year) VALUES ('Alice', 2019);
INSERT INTO employees (name, join_year) VALUES ('Bob', 2021);