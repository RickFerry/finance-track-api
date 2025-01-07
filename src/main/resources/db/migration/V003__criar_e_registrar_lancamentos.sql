CREATE TABLE lancamento
(
    codigo           BIGINT AUTO_INCREMENT NOT NULL,
    descricao        VARCHAR(255)          NULL,
    data_vencimento  date                  NULL,
    data_pagamento   date                  NULL,
    valor            DECIMAL               NULL,
    observacao       VARCHAR(255)          NULL,
    tipo             VARCHAR(255)          NULL,
    codigo_categoria BIGINT                NULL,
    codigo_pessoa    BIGINT                NULL,
    CONSTRAINT pk_lancamento PRIMARY KEY (codigo)
);

ALTER TABLE lancamento
    ADD CONSTRAINT FK_LANCAMENTO_ON_CODIGO_CATEGORIA FOREIGN KEY (codigo_categoria) REFERENCES categoria (codigo);

ALTER TABLE lancamento
    ADD CONSTRAINT FK_LANCAMENTO_ON_CODIGO_PESSOA FOREIGN KEY (codigo_pessoa) REFERENCES pessoa (codigo);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Conta de luz', '2019-06-20', '2019-06-20', 65.0, 'Conta de luz referente ao mês de junho', 'DESPESA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Conta de água', '2019-06-20', '2019-06-20', 35.0, 'Conta de água referente ao mês de junho', 'DESPESA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Aluguel', '2019-06-10', '2019-06-10', 1200.0, 'Aluguel referente ao mês de junho', 'DESPESA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Salário', '2019-06-10', '2019-06-10', 4000.0, 'Salário referente ao mês de junho', 'RECEITA', 2, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Salário', '2019-06-10', '2019-06-10', 2000.0, 'Salário referente ao mês de junho', 'RECEITA', 2, 3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Salário', '2019-06-10', '2019-06-10', 3000.0, 'Salário referente ao mês de junho', 'RECEITA', 2, 4);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Salário', '2019-06-10', '2019-06-10', 4000.0, 'Salário referente ao mês de junho', 'RECEITA', 2, 5);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Salário', '2019-06-10', '2019-06-10', 5000.0, 'Salário referente ao mês de junho', 'RECEITA', 2, 6);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Salário', '2019-06-10', '2019-06-10', 6000.0, 'Salário referente ao mês de junho', 'RECEITA', 2, 2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES ('Salário', '2019-06-10', '2019-06-10', 7000.0, 'Salário referente ao mês de junho', 'RECEITA', 2, 5);