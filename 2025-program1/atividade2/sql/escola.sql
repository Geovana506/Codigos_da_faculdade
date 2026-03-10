-- Criar banco de dados
CREATE DATABASE escola;

-- Usar o banco
\c escola;

-- Tabela pessoa
CREATE TABLE pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(200),
    telefone VARCHAR(20),
    email VARCHAR(100)
);

-- Tabela aluno
CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    matricula VARCHAR(10) UNIQUE NOT NULL,
    nome_pai VARCHAR(100),
    nome_mae VARCHAR(100),
    pessoa_id INT NOT NULL,
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);

-- Tabela professor
CREATE TABLE professor (
    id SERIAL PRIMARY KEY,
    matricula VARCHAR(10) UNIQUE NOT NULL,
    pessoa_id INT NOT NULL,
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);

-- Tabela disciplina
CREATE TABLE disciplina (
    id SERIAL PRIMARY KEY,
    nome_disciplina VARCHAR(100) NOT NULL
);

-- Tabela turma
CREATE TABLE turma (
    id SERIAL PRIMARY KEY,
    nome_turma VARCHAR(50) NOT NULL
);

-- Tabela periodo
CREATE TABLE periodo (
    id SERIAL PRIMARY KEY,
    nome_periodo VARCHAR(50) NOT NULL
);

-- Tabela diario
CREATE TABLE diario (
    id SERIAL PRIMARY KEY,
    status BOOLEAN DEFAULT FALSE,
    aluno_id INT NOT NULL,
    disciplina_id INT NOT NULL,
    turma_id INT NOT NULL,
    periodo_id INT NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES pessoa(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(id),
    FOREIGN KEY (turma_id) REFERENCES turma(id),
    FOREIGN KEY (periodo_id) REFERENCES periodo(id)
);

-- Tabela nota
CREATE TABLE nota (
    id SERIAL PRIMARY KEY,
    nota DOUBLE PRECISION CHECK (nota BETWEEN 0 AND 10),
    diario_id INT NOT NULL,
    FOREIGN KEY (diario_id) REFERENCES diario(id)
);