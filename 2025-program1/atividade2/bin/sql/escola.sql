CREATE TABLE nota (
    id SERIAL PRIMARY KEY,
    nota DOUBLE PRECISION CHECK (nota BETWEEN 0 AND 10),
    diario_id INT NOT NULL,
    FOREIGN KEY (diario_id) REFERENCES diario(id)
);
