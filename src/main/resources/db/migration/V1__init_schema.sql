CREATE TABLE Planos (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    version INT NOT NULL,
    descripcion TEXT,
    adjunto TEXT,
    nota TEXT,
    validado BOOL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Piezas (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    cantidad INT DEFAULT 0 NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Planos_Piezas (
    id UUID PRIMARY KEY,
    id_plano UUID NOT NULL REFERENCES Planos(id),
    id_pieza UUID NOT NULL REFERENCES Piezas(id),
    cantidad INT NOT NULL
);

CREATE TABLE Cierra (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    plano UUID NOT NULL REFERENCES Planos(id),
    descripcion TEXT,
    tipo VARCHAR(50),
    cantidad INT DEFAULT 0 NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Ordenes_Creacion (
    id UUID PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    cliente VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Ordenes_Creacion_Cierra (
    id UUID PRIMARY KEY,
    id_orden UUID NOT NULL REFERENCES Ordenes_Creacion(id),
    id_cierra UUID NOT NULL REFERENCES Cierra(id),
    cantidad INT NOT NULL
);

CREATE TABLE Ordenes_Salida (
    id UUID PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Ordenes_Salida_Cierra (
    id UUID PRIMARY KEY,
    id_orden UUID NOT NULL REFERENCES Ordenes_Salida(id),
    id_cierra UUID NOT NULL REFERENCES Cierra(id),
    cantidad INT NOT NULL
);

CREATE TABLE Despachos (
    id UUID PRIMARY KEY,
    id_orden UUID NOT NULL REFERENCES Ordenes_Salida(id),
    id_cierra UUID NOT NULL REFERENCES Cierra(id),
    cantidad_despachada INT NOT NULL,
    cantidad_faltante INT NOT NULL,
    fecha_despacho TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);