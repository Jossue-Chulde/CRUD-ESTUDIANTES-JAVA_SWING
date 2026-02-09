-- Script para crear la base de datos y la tabla de estudiantes

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS crud_estudiantes;

-- Usar la base de datos
USE crud_estudiantes;

-- Crear la tabla Estudiantes
CREATE TABLE IF NOT EXISTS Estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    carrera VARCHAR(100) NOT NULL,
    semestre INT NOT NULL,
    correo VARCHAR(100) NOT NULL
);

-- Opcional: Insertar datos de ejemplo (puedes comentar estas líneas si no deseas datos iniciales)
-- INSERT INTO Estudiantes (nombres, apellidos, edad, carrera, semestre, correo) VALUES
-- ('Juanito Marck', 'Sanchez Gonzales', 20, 'Economia', 2, 'juanito@gmail.com'),
-- ('Maria Antoneta', 'Carlos Mercedez', 20, 'Medicina', 4, 'maria@gmail.com'),
-- ('Alan Andres', 'Carmano Soler', 20, 'Ingenieria Civil', 3, 'alan@gmail.com');

-- Verificar que la tabla se creó correctamente
SELECT * FROM Estudiantes;
