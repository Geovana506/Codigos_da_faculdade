CREATE TABLE Prioridade (
    id INT PRIMARY KEY AUTO_INCREMENT, -- Ou SERIAL no PostgreSQL
    descricao VARCHAR(50) NOT NULL
);

CREATE TABLE Responsavel (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE ListaTarefas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data_tarefa DATE NOT NULL,
    descricao_tarefa VARCHAR(255) NOT NULL,
    observacao TEXT,
    id_prioridade INT,
    id_responsavel INT,
    FOREIGN KEY (id_prioridade) REFERENCES Prioridade(id),
    FOREIGN KEY (id_responsavel) REFERENCES Responsavel(id)
);