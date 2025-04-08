
CREATE TABLE delivery_parts (
    id UUID PRIMARY KEY,
    request_id UUID NOT NULL REFERENCES request_parts(id),
    quantity_delivered INT DEFAULT 0 NOT NULL,
    delivery_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);