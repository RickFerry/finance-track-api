CREATE TABLE IF NOT EXISTS usuario
(
    codigo
    BIGINT
    PRIMARY
    KEY
    GENERATED
    ALWAYS AS
    IDENTITY,
    nome
    VARCHAR
(
    255
) NOT NULL,
    email VARCHAR
(
    255
) NOT NULL,
    senha VARCHAR
(
    255
) NOT NULL
    );

ALTER TABLE usuario
    ADD CONSTRAINT uc_usuario_email UNIQUE (email);

CREATE TABLE IF NOT EXISTS permissao
(
    codigo
    BIGINT
    PRIMARY
    KEY
    GENERATED
    ALWAYS AS
    IDENTITY,
    descricao
    VARCHAR
(
    255
) NOT NULL
    );

ALTER TABLE permissao
    ADD CONSTRAINT uc_permissao_descricao UNIQUE (descricao);

CREATE TABLE IF NOT EXISTS usuario_permissao
(
    codigo_permissao
    BIGINT
    NOT
    NULL,
    codigo_usuario
    BIGINT
    NOT
    NULL
);

ALTER TABLE usuario_permissao
    ADD CONSTRAINT fk_usuper_on_permissao FOREIGN KEY (codigo_permissao) REFERENCES permissao (codigo);

ALTER TABLE usuario_permissao
    ADD CONSTRAINT fk_usuper_on_usuario FOREIGN KEY (codigo_usuario) REFERENCES usuario (codigo);

INSERT INTO usuario (nome, email, senha)
VALUES ('administrador', 'admin@email.com', '$2a$10$E0LYcIjyF2ZyrhgximySoO09qva/9KwrGQFuzbtM.r0cu4AUcJuL6');
INSERT INTO usuario (nome, email, senha)
VALUES ('usuario', 'user@email.com', '$2a$10$E0LYcIjyF2ZyrhgximySoO09qva/9KwrGQFuzbtM.r0cu4AUcJuL6');

INSERT INTO permissao (descricao)
VALUES ('ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (descricao)
VALUES ('ROLE_PESQUISAR_LANCAMENTO');
INSERT INTO permissao (descricao)
VALUES ('ROLE_REMOVER_LANCAMENTO');

INSERT INTO permissao (descricao)
VALUES ('ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (descricao)
VALUES ('ROLE_REMOVER_PESSOA');
INSERT INTO permissao (descricao)
VALUES ('ROLE_PESQUISAR_PESSOA');

INSERT INTO permissao (descricao)
VALUES ('ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (descricao)
VALUES ('ROLE_REMOVER_CATEGORIA');
INSERT INTO permissao (descricao)
VALUES ('ROLE_PESQUISAR_CATEGORIA');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (1, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (1, 9);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (2, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (2, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
VALUES (2, 8);