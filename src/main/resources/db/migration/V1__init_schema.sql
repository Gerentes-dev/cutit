CREATE TABLE plans (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    version INT NOT NULL,
    description TEXT,
    attachment TEXT,
    note TEXT,
    status VARCHAR(255) DEFAULT 'PENDING' NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE parts (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT DEFAULT 0 NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE parts_plans (
    id UUID PRIMARY KEY,
    plan_id UUID NOT NULL REFERENCES plans(id),
    part_id UUID NOT NULL REFERENCES parts(id),
    quantity INT NOT NULL
);

CREATE TABLE chainsaws (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    plan_id UUID NOT NULL REFERENCES plans(id),
    description TEXT,
    type VARCHAR(50),
    quantity INT DEFAULT 0 NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE creation_orders (
    id UUID PRIMARY KEY,
    creation_date TIMESTAMP NOT NULL,
    client VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE chainsaw_creation_orders (
    id UUID PRIMARY KEY,
    creation_order_id UUID NOT NULL REFERENCES creation_orders(id),
    chainsaw_id UUID NOT NULL REFERENCES chainsaws(id),
    quantity INT NOT NULL
);

CREATE TABLE departure_orders (
    id UUID PRIMARY KEY,
    departure_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE chainsaw_departure_orders (
    id UUID PRIMARY KEY,
    departure_order_id UUID NOT NULL REFERENCES departure_orders(id),
    chainsaw_id UUID NOT NULL REFERENCES chainsaws(id),
    quantity INT NOT NULL
);

CREATE TABLE dispatchs (
    id UUID PRIMARY KEY,
    departure_order_id UUID NOT NULL REFERENCES departure_orders(id),
    chainsaw_id UUID NOT NULL REFERENCES chainsaws(id),
    quantity_dispatched INT NOT NULL,
    quantity_missing INT NOT NULL,
    dispatch_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);