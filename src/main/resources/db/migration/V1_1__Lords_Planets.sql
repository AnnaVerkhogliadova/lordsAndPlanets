CREATE TABLE lords (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    age varchar
);

CREATE TABLE planets (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    lord_id integer,
    CONSTRAINT fk_planets_lord_id__lords_id FOREIGN KEY (lord_id) REFERENCES lords (id)
);