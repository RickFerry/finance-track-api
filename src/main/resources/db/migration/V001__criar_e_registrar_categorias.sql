CREATE TABLE categoria
(
    codigo BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome   VARCHAR(255) NOT NULL
);

INSERT INTO categoria (nome)
VALUES ('Alimentação');
INSERT INTO categoria (nome)
VALUES ('Lazer');
INSERT INTO categoria (nome)
VALUES ('Transporte');
INSERT INTO categoria (nome)
VALUES ('Saúde');
INSERT INTO categoria (nome)
VALUES ('Moradia');
INSERT INTO categoria (nome)
VALUES ('Outros');