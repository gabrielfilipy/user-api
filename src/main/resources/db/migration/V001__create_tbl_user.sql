CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE TBL_USER (
    user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    active BOOLEAN,
    nome VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    matricula VARCHAR(255),
    email VARCHAR(255),
    department_id UUID,
    telefone VARCHAR(255),
    endereco TEXT,
    rg VARCHAR(20),
    cpf VARCHAR(14)
);
