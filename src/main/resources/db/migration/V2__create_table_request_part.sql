CREATE TABLE Solicitar_Material (
    id UUID PRIMARY KEY,
    id_pieza UUID NOT NULL REFERENCES Piezas(id),
    cantidad INT DEFAULT 0 NOT NULL,
    fecha TIMESTAMP NOT NULL,
    estado VARCHAR(255) DEFAULT 'PENDIENTE' NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);