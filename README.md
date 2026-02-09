# Sistema CRUD de Gestión de Estudiantes

Sistema de gestión de estudiantes desarrollado en Java con Swing para la interfaz gráfica, Gradle para la gestión de dependencias y MySQL como base de datos.

## Requisitos Previos

- **Java 21** (JDK 21)
- **IntelliJ IDEA Community Edition**
- **MySQL Workbench** o **MySQL Server**
- **Gradle** (se incluye el wrapper en el proyecto)

## Configuración de la Base de Datos

### Paso 1: Crear la base de datos en MySQL

1. Abre **MySQL Workbench**
2. Conéctate al servidor MySQL con:
   - Usuario: `root`
   - Contraseña: `root`

3. Ejecuta el script SQL que se encuentra en `database_setup.sql`:
   - Abre el archivo `database_setup.sql` en MySQL Workbench
   - Ejecuta todo el script (Ctrl + Shift + Enter)
   - Esto creará la base de datos `crud_estudiantes` y la tabla `Estudiantes`

### Paso 2: Verificar la creación

Ejecuta la siguiente consulta para verificar:
```sql
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

/*Opcional: Insertar datos de ejemplo (puedes comentar estas líneas si no deseas datos iniciales)
INSERT INTO Estudiantes (nombres, apellidos, edad, carrera, semestre, correo) VALUES
	('Juanito Marck', 'Sanchez Gonzales', 20, 'Economia', 2, 'juanito@gmail.com'),
    ('Maria Antoneta', 'Carlos Mercedez', 20, 'Medicina', 4, 'maria@gmail.com'),
    ('Alan Andres', 'Carmano Soler', 20, 'Ingenieria Civil', 3, 'alan@gmail.com');*/

-- Verificar que la tabla se creó correctamente
SELECT * FROM Estudiantes;
```

## Configuración del Proyecto en IntelliJ IDEA

### Paso 1: Abrir el proyecto

1. Abre **IntelliJ IDEA Community Edition**
2. Selecciona `File > Open`
3. Navega hasta la carpeta `crud-estudiantes` y ábrela
4. IntelliJ detectará automáticamente que es un proyecto Gradle

### Paso 2: Configurar el JDK

1. Ve a `File > Project Structure`
2. En `Project`, selecciona Java 21 como SDK
3. Haz clic en `Apply` y luego en `OK`

### Paso 3: Descargar dependencias

1. Espera a que IntelliJ descargue las dependencias de Gradle automáticamente
2. Si no se descargan automáticamente, abre la ventana de Gradle (botón en el lateral derecho)
3. Haz clic en el botón de recarga (Reload All Gradle Projects)

## Estructura del Proyecto

```
crud-estudiantes/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── estudiantes/
│                   ├── Main.java                    # Clase principal
│                   ├── model/
│                   │   └── Estudiante.java          # Modelo de datos
│                   ├── database/
│                   │   ├── ConexionDB.java          # Conexión a MySQL
│                   │   └── EstudianteDAO.java       # Operaciones CRUD
│                   └── gui/
│                       └── VentanaPrincipal.java    # Interfaz gráfica
├── build.gradle                                      # Configuración de Gradle
├── settings.gradle
├── database_setup.sql                                # Script SQL
└── README.md                                         # Este archivo
```

## Ejecutar la Aplicación

### Opción 1: Desde IntelliJ IDEA

1. Abre la clase `Main.java` (ubicada en `src/main/java/com/estudiantes/`)
2. Haz clic derecho en el archivo
3. Selecciona `Run 'Main.main()'`

### Opción 2: Usando Gradle

En la terminal de IntelliJ (o terminal del sistema), ejecuta:

**En Windows:**
```bash
gradlew run
```

**En Linux/Mac:**
```bash
./gradlew run
```

## Uso de la Aplicación

### Funcionalidades

#### 1. Guardar Estudiante
- Completa todos los campos del formulario (Nombres, Apellidos, Edad, Carrera, Semestre, Correo)
- Haz clic en el botón **Guardar**
- Los datos se almacenarán en la base de datos

#### 2. Mostrar Estudiantes
- Haz clic en el botón **Mostrar**
- Se cargarán todos los estudiantes de la base de datos en la tabla

#### 3. Actualizar Estudiante
- Haz clic en una fila de la tabla para seleccionar un estudiante
- Los datos se cargarán automáticamente en los campos del formulario
- Modifica los campos que desees
- Haz clic en el botón **Actualizar**
- Los cambios se guardarán en la base de datos

#### 4. Eliminar Estudiante
- Haz clic en una fila de la tabla para seleccionar un estudiante
- Los datos se cargarán automáticamente en los campos
- Haz clic en el botón **Eliminar**
- Aparecerá una ventana de confirmación
- Selecciona **Sí** para confirmar la eliminación o **No** para cancelar
- El estudiante será eliminado de la base de datos

## Validaciones

- Todos los campos son obligatorios
- La edad y el semestre deben ser números válidos
- No se puede actualizar o eliminar sin seleccionar un estudiante de la tabla

## Solución de Problemas

### Error de conexión a la base de datos

Si aparece un error de conexión, verifica:
1. MySQL está ejecutándose
2. El usuario y contraseña son correctos (`root`/`root`)
3. La base de datos `crud_estudiantes` existe
4. El puerto MySQL es el 3306 (predeterminado)

### El driver MySQL no se encuentra

Si aparece "No se encontró el driver de MySQL":
1. Recarga el proyecto Gradle
2. Verifica que el archivo `build.gradle` tenga la dependencia del conector MySQL
3. Limpia y reconstruye el proyecto: `Build > Rebuild Project`

### Error al compilar

1. Verifica que estás usando Java 21
2. Limpia el proyecto: `Build > Clean Project`
3. Reconstruye: `Build > Rebuild Project`

## Tecnologías Utilizadas

- **Java 21**: Lenguaje de programación
- **Swing**: Biblioteca para la interfaz gráfica
- **Gradle 8.5**: Gestión de dependencias y construcción
- **MySQL 8.0**: Base de datos
- **MySQL Connector/J 8.0.33**: Driver JDBC para MySQL

## Autor

Sistema desarrollado para gestión académica de estudiantes.
