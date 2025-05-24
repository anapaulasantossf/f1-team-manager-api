CREATE TABLE funcionario (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nomeFuncionario     VARCHAR(150)    NOT NULL,
    dataNascimento      DATE            NOT NULL
);