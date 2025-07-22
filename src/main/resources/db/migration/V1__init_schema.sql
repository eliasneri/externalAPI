-- Habilita a extensão pgcrypto (necessária para gen_random_uuid())
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS tb_product (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),  -- UUID nativo no PostgreSQL
    id_origin BIGINT NOT NULL,
    title VARCHAR(255),
    description TEXT,
    category VARCHAR(255),
    image VARCHAR(1000),
    price NUMERIC(38, 2),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,  -- TIMESTAMPTZ é o recomendado no PG 14+
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_tb_product_id_origin UNIQUE (id_origin)
);

-- Índice para otimização
CREATE INDEX IF NOT EXISTS idx_product_category ON tb_product(category);