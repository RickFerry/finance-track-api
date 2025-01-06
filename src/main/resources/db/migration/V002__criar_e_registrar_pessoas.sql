CREATE TABLE pessoa (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    logradouro VARCHAR(255),
    numero VARCHAR(255),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cep VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('João', true, 'Rua 1', '123', 'Apto 1', 'Centro', '12345-678', 'São Paulo', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Maria', true, 'Rua 2', '456', 'Apto 2', 'Centro', '12345-678', 'São Paulo', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('José', true, 'Rua 3', '789', 'Apto 3', 'Centro', '12345-678', 'São Paulo', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Ana', true, 'Rua 4', '101112', 'Apto 4', 'Centro', '12345-678', 'São Paulo', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Carlos', true, 'Rua 5', '131415', 'Apto 5', 'Centro', '12345-678', 'São Paulo', 'SP');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Mariana', true, 'Rua 6', '161718', 'Apto 6', 'Centro', '12345-678', 'São Paulo', 'SP');