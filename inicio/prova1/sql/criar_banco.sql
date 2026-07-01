CREATE DATABASE controle_estoque_prog2_geovana;

\c controle_estoque_prog2_geovana;

CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE,
    rg VARCHAR(20),
    endereco VARCHAR(200),
    telefone VARCHAR(30)
);

CREATE TABLE fornecedor (
    id SERIAL PRIMARY KEY,
    nome_fantasia VARCHAR(120) NOT NULL,
    razao_social VARCHAR(120),
    cnpj VARCHAR(25) NOT NULL UNIQUE
);

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    preco_medio NUMERIC(10, 2) DEFAULT 0,
    qtde_estoque NUMERIC(10, 2) DEFAULT 0,
    valor_ultima_compra NUMERIC(10, 2) DEFAULT 0,
    valor_ultima_venda NUMERIC(10, 2) DEFAULT 0,
    categoria_id INTEGER NOT NULL REFERENCES categoria(id)
);

CREATE TABLE fornecedor_produto (
    id SERIAL PRIMARY KEY,
    fornecedor_id INTEGER NOT NULL REFERENCES fornecedor(id) ON DELETE CASCADE,
    produto_id INTEGER NOT NULL REFERENCES produto(id) ON DELETE CASCADE,
    UNIQUE (fornecedor_id, produto_id)
);

CREATE TABLE compra (
    id SERIAL PRIMARY KEY,
    fornecedor_id INTEGER NOT NULL REFERENCES fornecedor(id),
    data_compra DATE NOT NULL,
    valor_total NUMERIC(10, 2) NOT NULL
);

CREATE TABLE compra_produto (
    id SERIAL PRIMARY KEY,
    compra_id INTEGER NOT NULL REFERENCES compra(id) ON DELETE CASCADE,
    produto_id INTEGER NOT NULL REFERENCES produto(id),
    quantidade NUMERIC(10, 2) NOT NULL,
    valor_unitario NUMERIC(10, 2) NOT NULL
);

CREATE TABLE venda (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL REFERENCES cliente(id),
    data_venda DATE NOT NULL,
    valor_total NUMERIC(10, 2) NOT NULL
);

CREATE TABLE venda_produto (
    id SERIAL PRIMARY KEY,
    venda_id INTEGER NOT NULL REFERENCES venda(id) ON DELETE CASCADE,
    produto_id INTEGER NOT NULL REFERENCES produto(id),
    quantidade NUMERIC(10, 2) NOT NULL,
    valor_unitario NUMERIC(10, 2) NOT NULL
);
