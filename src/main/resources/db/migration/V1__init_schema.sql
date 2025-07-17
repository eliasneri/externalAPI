CREATE TABLE IF NOT EXISTS tb_product (
    id VARCHAR(255) NOT NULL,
    id_origin BIGINT NOT NULL,
    title VARCHAR(255),
    description TEXT,
    category VARCHAR(255),
    image VARCHAR(1000),
    price NUMERIC(38, 2),

    CONSTRAINT pk_tb_product PRIMARY KEY (id),
    CONSTRAINT uq_tb_product_id_origin UNIQUE (id_origin)
);