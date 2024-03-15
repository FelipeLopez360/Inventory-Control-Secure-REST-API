
# Inventory Control: Secure REST API con Spring Security & JSON WebToken

Inventory Control: Secure REST API con Spring Security & JSON WebToken es un proyecto que ofrece una solución completa para gestionar inventarios de manera segura y eficiente. Esta aplicación presenta una API REST robusta, implementada con Spring Security y JSON WebToken (JWT), que garantiza la autenticación segura y la autorización de usuarios. Con operaciones CRUD integradas, los usuarios pueden realizar fácilmente acciones de creación, lectura, actualización y eliminación de elementos de inventario. Esta solución no solo brinda funcionalidades esenciales de gestión de inventario, sino que también prioriza la seguridad, asegurando que los datos críticos estén protegidos contra amenazas y vulnerabilidades. Con "Inventory Control", puedes administrar tus inventarios con confianza y tranquilidad, sabiendo que tu información está protegida por las mejores prácticas de seguridad de la industria.






### Características

- Autenticación: Verificación de la identidad de los usuarios para garantizar que solo personas autorizadas puedan acceder a la aplicación.
- Encriptación de Contraseñas en Base de Datos: Proceso de encriptación de contraseñas almacenadas en la base de datos para proteger la información confidencial de los usuarios.
- Spring Security: Integración de Spring Security para proporcionar una capa adicional de seguridad a la aplicación.
- API REST: Implementación de una API RESTful que permite a los clientes interactuar con la aplicación de manera eficiente y escalable.
- CRUD: Funcionalidades completas de Crear, Leer, Actualizar y Eliminar (CRUD) para gestionar los datos de inventario.
- Creación de JSON Web Token (JWT): Generación de tokens JWT para autenticación de usuarios y garantizar la seguridad de las comunicaciones.
- Roles: Asignación de roles a los usuarios para definir sus permisos y capacidades dentro de la aplicación.
- Login: Implementación de un sistema de inicio de sesión para que los usuarios puedan acceder a la aplicación.
- Permisos: Definición y gestión de permisos para controlar el acceso a las diferentes funcionalidades y recursos de la aplicación.
- Sesiones: Manejo seguro de sesiones de usuario para mantener la integridad y confidencialidad de los datos durante la interacción con la aplicación.
## Construido Con 🛠️

[JAVA](https://www.java.com/es/) - Lenguaje Programación

[Spring boot](https://spring.io/projects/spring-boot) - Framework usado

[Spring security](https://spring.io/projects/spring-security) - Framework usado

[Spring Initializr](https://start.spring.io/) - Constructor de proyecto

[Maven](https://maven.apache.org/) - Manejador de dependencias

[Docker](https://www.docker.com/) - Sistema de contenedores

[PostgreSQL](https://www.postgresql.org/) - Base de datos

[H2](https://www.h2database.com/) - Base de datos en memoria
## Despliegue

#### Despliegue Base Datos H2 📦

- Este es el despliegue sencillo, solo necesitas ejecutar la aplicacion.
   - La base de datos H2 almacena los datos en memoria temporal, recuerda que al finalizar la aplicacion los datos se borran, si deseas persistencia utiliza la configuracion con postgreSQL, se explica mas abajo.
  
- Puedes cambiar el puerto en application.properties, por defecto use el siguiente:
  
  - server.port=8082

- Utilicé POSTMAN para las peticiones HTTP, te dejo las rutas y los metodos:

 - Crear Admin:
     - POST 
    http://localhost:8082/inventorycontrol/api/auth/registro/admin

  - Crear User:
      - POST 
    http://localhost:8082/inventorycontrol/api/auth/registro

  - Login:
      - POST 
    http://localhost:8082/inventorycontrol/api/auth/login

- Listar Productos:
    - GET
    http://localhost:8082/inventorycontrol/api/producto

- Listar Producto Por ID:
    - GET
    http://localhost:8082/inventorycontrol/api/producto/buscar/3

- Agregar Producto:
    - POST 
    http://localhost:8082/inventorycontrol/api/producto/agregar

- Editar Producto:
    - PUT 
    http://localhost:8082/inventorycontrol/api/producto/editar/1

- Eliminar Producto:
    - DELETE 
    http://localhost:8082/inventorycontrol/api/producto/eliminar/2

#### Despliegue DOCKER 📦

- Abre Docker y ejecuta el archivo "docker-compose.yml" (adjunto en el proyecto) en una linea de comandos.
    
    nota: en "# add name" coloca el nombre que deseas para tu container y tu base de datos.

- Crea una base de datos llamada "srt_security_jwt" en postgreSQL.

- Debes cambiar los parametros en "application.properties" para que postgreSQL sea la base de datos predeterminada.

- Puedes uttilizar POSTMAN para las peticiones a la API.
## License

Este proyecto está bajo la licencia [MIT](https://choosealicense.com/licenses/mit/). Siéntete libre de utilizar, modificar y distribuir este software de acuerdo con los términos de la licencia.

