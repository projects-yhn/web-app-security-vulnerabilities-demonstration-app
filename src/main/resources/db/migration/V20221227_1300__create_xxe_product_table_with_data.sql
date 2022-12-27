CREATE TABLE IF NOT EXISTS xxe_products
(
    id bigserial NOT NULL,
    name text NOT NULL,
    category text NOT NULL,
    image_url text,
    price double precision NOT NULL,
    description text NOT NULL,
    PRIMARY KEY (id)
    );

ALTER TABLE xxe_products
    OWNER to "cySec";

INSERT INTO xxe_products (name, category, price, description)
VALUES('mobile phone 2022', 'Phones', 333.6, 'The new model of popular phone'),
('laptop 2021', 'Laptops', 2334.6, 'The latest stable model of this brand');