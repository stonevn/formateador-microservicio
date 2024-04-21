# Microservicio Formateador

Este es un microservicio que formatea cantidades monetarias según el país especificado.

## Requisitos

- Java 17
- Maven

## Estructura del Proyecto

El proyecto sigue la siguiente estructura:

- `src/`: Directorio principal del código fuente.
- `pom.xml`: Archivo de configuración de Maven.
- `README.md`: Este archivo que estás leyendo.

## Configuración

1. Clona el repositorio.
2. Asegúrate de tener Java 11 y Maven instalados en tu sistema.
3. Ejecuta `mvn clean install` para compilar y construir el proyecto.
4. Ejecuta la aplicación con `mvn spring-boot:run` o despliega el archivo JAR generado.

## Uso

Una vez que la aplicación esté en funcionamiento, puedes acceder a la API desde tu navegador o herramienta de cliente REST favorita.

### Endpoints

- `/formateador/{country}`: Formatea una cantidad monetaria según el país especificado.
- `/formateador/countries`: Devuelve la lista de países disponibles para formatear.

## Ejemplos

### Formateo de una cantidad monetaria