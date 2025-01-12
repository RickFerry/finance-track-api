CREATE TABLE IF NOT EXISTS usuario
(
    codigo BIGINT AUTO_INCREMENT NOT NULL,
    nome   VARCHAR(255) NOT NULL,
    email  VARCHAR(255) NOT NULL,
    senha  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (codigo)
    );

ALTER TABLE usuario
    ADD CONSTRAINT uc_usuario_email UNIQUE (email);

CREATE TABLE IF NOT EXISTS permissao
(
    codigo    BIGINT AUTO_INCREMENT NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    CONSTRAINT pk_permissao PRIMARY KEY (codigo)
    );

ALTER TABLE permissao
    ADD CONSTRAINT uc_permissao_descricao UNIQUE (descricao);

CREATE TABLE IF NOT EXISTS usuario_permissao
(
    codigo_permissao BIGINT NOT NULL,
    codigo_usuario   BIGINT NOT NULL
);

ALTER TABLE usuario_permissao
    ADD CONSTRAINT fk_usuper_on_permissao FOREIGN KEY (codigo_permissao) REFERENCES permissao (codigo);

ALTER TABLE usuario_permissao
    ADD CONSTRAINT fk_usuper_on_usuario FOREIGN KEY (codigo_usuario) REFERENCES usuario (codigo);

INSERT INTO usuario (codigo, nome, email, senha) VALUES (1, 'administrador', 'admin@email.com', '$2y$10$XqBdZSMrn37jFZ0xC9x68.vxnLm.Tc7KFRvg99l2cH34gCStr3YiS');
INSERT INTO usuario (codigo, nome, email, senha) VALUES (2, 'usuario', 'user@email.com', '$2y$10$XqBdZSMrn37jFZ0xC9x68.vxnLm.Tc7KFRvg99l2cH34gCStr3YiS');

INSERT INTO permissao (codigo, descricao) VALUES (1, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (codigo, descricao) VALUES (2, 'ROLE_PESQUISAR_LANCAMENTO');
INSERT INTO permissao (codigo, descricao) VALUES (3, 'ROLE_REMOVER_LANCAMENTO');

INSERT INTO permissao (codigo, descricao) VALUES (4, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (codigo, descricao) VALUES (5, 'ROLE_REMOVER_PESSOA');
INSERT INTO permissao (codigo, descricao) VALUES (6, 'ROLE_PESQUISAR_PESSOA');

INSERT INTO permissao (codigo, descricao) VALUES (7, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (codigo, descricao) VALUES (8, 'ROLE_REMOVER_CATEGORIA');
INSERT INTO permissao (codigo, descricao) VALUES (9, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 9);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 8);