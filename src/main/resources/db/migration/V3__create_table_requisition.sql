CREATE TABLE requisitions (
    id UUID PRIMARY KEY,
    chainsaw_id UUID NOT NULL REFERENCES chainsaws(id),
    quantity INT CHECK (quantity BETWEEN 1 AND 100) NOT NULL,
    requisition_date DATE NOT NULL,
    observation TEXT,
    status VARCHAR(255) DEFAULT 'PENDING' NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);