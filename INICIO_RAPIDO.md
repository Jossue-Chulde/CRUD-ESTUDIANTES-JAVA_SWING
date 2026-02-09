# GUÍA RÁPIDA DE INICIO

## 1. Configurar MySQL (PRIMERO)

Abre MySQL Workbench y ejecuta:

```sql
CREATE DATABASE IF NOT EXISTS crud_estudiantes;
USE crud_estudiantes;

CREATE TABLE IF NOT EXISTS Estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    carrera VARCHAR(100) NOT NULL,
    semestre INT NOT NULL,
    correo VARCHAR(100) NOT NULL
);
```

Usuario: root
Contraseña: root

## 2. Abrir en IntelliJ IDEA

1. File > Open
2. Selecciona la carpeta `crud-estudiantes`
3. Espera a que se descarguen las dependencias de Gradle

## 3. Configurar Java 21

1. File > Project Structure
2. Project > SDK > Selecciona Java 21
3. Apply > OK

## 4. Ejecutar

1. Abre `Main.java`
2. Click derecho > Run 'Main.main()'

O desde terminal:
```bash
gradlew run          # Windows
./gradlew run        # Linux/Mac
```

## 5. Usar la Aplicación

- **Guardar**: Llena los campos y click en "Guardar"
- **Mostrar**: Click en "Mostrar" para ver todos los estudiantes
- **Actualizar**: Selecciona una fila, modifica datos y click en "Actualizar"
- **Eliminar**: Selecciona una fila y click en "Eliminar"

¡Listo! 🎉
