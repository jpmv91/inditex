La base del proyecto ha sido generado con spring initializr (https://start.spring.io/)

La arquitectura que se ha utilizado para el desarrollo del proyecto es una arquitectura
hexagonal ( por eso la creación del paquete ports)

Generar api:
``` 
 mvnw.cmd generate-sources (Windows)  
./mvnw generate-sources (Linux/Unix)
```

Estructura de archivos :
- prices
    - data: localización de la base de datos (h2)     
      - Credenciales de la base de datos ( se pueden tambien ver en el application.yml)
        - username: admin
          password: admin
    - src:
        - main:
            - java:
                - com.inditex.prices
                    - PricesApplication: clase main
                    - adapters: controlador, esta clase implementa la api
                    - entities: clases del modelo
                    - exceptions: paquete que contiene clases de control de excepciones
                    - mapper: mapeadores
                    - ports: interfaces a través de las cuales el sistema interactúa con el exterior (bases de datos, etc) 
                    - repository: repositorio, donde hemos utilizado una query nativa
                    - services: donde se implementan los casos de uso
                    - 
            - resources:
                - application.yml: archivo de configuración de la aplicación
                - data.sql: script para inicializar la base de datos
                - swagger.yaml: archivo de definición de la API
        - test:
            - com.inditex.prices:
                - PriceAdapterControllerUnitTest: clase con los tests unitarios de la prueba 
                - PriceAdapterControllerIntegrationTest : clase que contiene pruebas de integración
