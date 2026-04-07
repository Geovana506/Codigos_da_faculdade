
-- Tabela Categoria (1 Categoria para N Produtos)
CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Tabela Fornecedor (N Fornecedores para N Produtos)
CREATE TABLE fornecedor (
    id SERIAL PRIMARY KEY,
    nome_fantasia VARCHAR(150) NOT NULL,
    razao_social VARCHAR(150) NOT NULL,
    cnpj VARCHAR(20) UNIQUE NOT NULL
);

-- Tabela Cliente (1 Cliente para N Vendas)
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(15) UNIQUE NOT NULL,
    rg VARCHAR(20),
    endereco VARCHAR(255),
    telefone VARCHAR(20)
);

-- Tabela Produto
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    preco NUMERIC(10, 2) NOT NULL,
    qtde_estoque NUMERIC(10, 2) NOT NULL,
    id_categoria INT NOT NULL,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

-- Tabela Associativa: Produto_Fornecedor (Muitos para Muitos)
CREATE TABLE produto_fornecedor (
    id_produto INT NOT NULL,
    id_fornecedor INT NOT NULL,
    PRIMARY KEY (id_produto, id_fornecedor),
    CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES produto(id),
    CONSTRAINT fk_fornecedor FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id)
);

-- Tabela Venda
CREATE TABLE venda (
    id SERIAL PRIMARY KEY,
    data_venda DATE NOT NULL,
    valor_total NUMERIC(10, 2) NOT NULL,
    id_cliente INT NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

-- Tabela Associativa: Venda_Produto (Muitos para Muitos)
-- Adicionei a coluna 'quantidade' para permitir abater do estoque corretamente conforme a regra de negócio.
CREATE TABLE venda_produto (
    id_venda INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade NUMERIC(10, 2) NOT NULL, 
    PRIMARY KEY (id_venda, id_produto),
    CONSTRAINT fk_venda FOREIGN KEY (id_venda) REFERENCES venda(id),
    CONSTRAINT fk_produto_venda FOREIGN KEY (id_produto) REFERENCES produto(id)
);

INSERT INTO categoria (nome) VALUES ('Eletronicos'), ('Vestuario');
