CREATE TABLE t_drone(
    id BIGSERIAL PRIMARY KEY,
    serial_number VARCHAR(100) NOT NULL UNIQUE,
    model VARCHAR(32),
    weight_limit NUMERIC,
    battery_capacity NUMERIC,
    state VARCHAR(32),
    created_on TIMESTAMP DEFAULT now()
);

CREATE TABLE t_medication(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    weight NUMERIC NOT  NULL,
    code VARCHAR(50) NOT NULL UNIQUE,
    image_url TEXT,
    created_on TIMESTAMP DEFAULT now()
);

CREATE TABLE t_load_drone(
    id BIGSERIAL PRIMARY KEY,
    drone_serial_number BIGINT NOT NULL REFERENCES t_drone(serial_number),
    drone_load_total_weight NUMERIC,
    created_on TIMESTAMP DEFAULT now()
);

CREATE TABLE t_medication_load(
    id BIGSERIAL PRIMARY KEY,
    load_drone_id BIGINT REFERENCES t_load_drone(id),
    quantity NUMERIC,
    medication_code VARCHAR(32) REFERENCES t_medication(code),
    medication_total_weight NUMERIC,
    created_on TIMESTAMP DEFAULT now()
);