CREATE TABLE Curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    clave VARCHAR(255) NOT NULL
);

CREATE TABLE Topico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fechaCreacion DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    autor_id INT NOT NULL,
    curso_id INT NOT NULL,
    CONSTRAINT fk_topico_autor FOREIGN KEY (autor_id) REFERENCES Usuario(id),
    CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES Curso(id)
);

CREATE TABLE Respuesta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico_id INT NOT NULL,
    fechaCreacion DATETIME NOT NULL,
    autor_id INT NOT NULL,
    solucion BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_respuesta_topico FOREIGN KEY (topico_id) REFERENCES Topico(id),
    CONSTRAINT fk_respuesta_autor FOREIGN KEY (autor_id) REFERENCES Usuario(id)
);