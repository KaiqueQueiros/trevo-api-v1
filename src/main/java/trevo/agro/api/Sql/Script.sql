CREATE TABLE tb_category (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_tb_category PRIMARY KEY (id)
);

ALTER TABLE tb_category ADD CONSTRAINT uc_tb_category_name UNIQUE (name);


CREATE TABLE tb_culture (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_tb_culture PRIMARY KEY (id)
);


CREATE TABLE tb_product (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   description VARCHAR(255),
   area_size VARCHAR(255),
   date date,
   img VARCHAR(255),
   CONSTRAINT pk_tb_product PRIMARY KEY (id)
);

CREATE TABLE tb_product_category (
  category_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL
);

CREATE TABLE tb_product_culture (
  culture_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL
);

ALTER TABLE tb_product ADD CONSTRAINT uc_tb_product_name UNIQUE (name);

ALTER TABLE tb_product_category ADD CONSTRAINT fk_tbprocat_on_category FOREIGN KEY (category_id) REFERENCES tb_category (id);

ALTER TABLE tb_product_category ADD CONSTRAINT fk_tbprocat_on_product FOREIGN KEY (product_id) REFERENCES tb_product (id);

ALTER TABLE tb_product_culture ADD CONSTRAINT fk_tbprocul_on_culture FOREIGN KEY (culture_id) REFERENCES tb_culture (id);

ALTER TABLE tb_product_culture ADD CONSTRAINT fk_tbprocul_on_product FOREIGN KEY (product_id) REFERENCES tb_product (id);



CREATE TABLE tb_budget (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   email VARCHAR(255),
   phone VARCHAR(255),
   country VARCHAR(255),
   date date,
   company VARCHAR(255),
   CONSTRAINT pk_tb_budget PRIMARY KEY (id)
);

CREATE TABLE tb_budget_product (
  budget_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL
);

ALTER TABLE tb_budget_product ADD CONSTRAINT fk_tbbudpro_on_budget FOREIGN KEY (budget_id) REFERENCES tb_budget (id);

ALTER TABLE tb_budget_product ADD CONSTRAINT fk_tbbudpro_on_product FOREIGN KEY (product_id) REFERENCES tb_product (id);