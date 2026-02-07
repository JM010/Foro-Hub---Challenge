# 🧠 ForoHub – API REST

ForoHub es una **API REST desarrollada con Spring Boot** que simula el funcionamiento de un foro de discusión.
Permite gestionar usuarios, autenticación con JWT, cursos, tópicos y respuestas, incluyendo control de acceso por roles.

Este proyecto fue desarrollado como parte de un desafío backend propuesto por
Alura y el programa Oracle next Education, aplicando buenas prácticas de arquitectura, seguridad y persistencia.

---

## 🚀 Tecnologías utilizadas

- ☕ Java 17

- 🌱 Spring Boot

  - Spring Web

  - Spring Data JPA

  - Spring Security

- 🔐 JWT (Auth0)

- 🛢️ MySQL

- 🧪 Hibernate Validator

- 📦 Maven

- 🧰 Flyway (migraciones)

- 🧠 Lombok

---

## 🔐 Autenticación y Seguridad

- Autenticación basada en JWT

- Sesiones STATELESS

- Control de acceso mediante roles:

  -  ROLE_USER

  - ROLE_ADMIN

- Uso de @PreAuthorize para autorización a nivel de endpoint y método

- Filtros personalizados con OncePerRequestFilter


---

## 👥 Usuarios y Roles

- Registro de usuarios con validación de datos
- Roles asignados al momento del registro (por defecto ROLE_USER)
- Gestión de usuarios (solo para admins)
- Encriptación de contraseñas con BCryptPasswordEncoder
- Endpoints protegidos según el rol del usuario
- Ejemplo de endpoint protegido:

```java
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/usuarios/{id}/roles/admin")
    public ResponseEntity asignarAdmin(@PathVariable Long id) {
        adminService.asignarRolAdmin(id);
        return ResponseEntity.ok().build();
    }

}
```

---

## 📚 Funcionalidades principales

### 🔑 Autenticación

- Login con email y contraseña

- Generación y validación de token JWT

### 🧵 Tópicos

- Crear, editar y eliminar tópicos (solo para el autor o admin)

### 💬 Respuestas

- Crear, editar y eliminar respuestas (solo para el autor o admin)
- Obtener respuestas de un tópico

### 📘 Cursos
- Listar cursos 
- crear cursos (solo para admin)
- editar cursos (solo para admin)
- eliminar cursos (solo para admin)

## ❌ Exepciones personalizadas para manejo de errores y respuestas consistentes.
- Manejo global de excepciones con @ControllerAdvice
- Excepcion personalizada para recursos no encontrados (ValidationException)

---

## 📌 Endpoints principales

### Autenticación
```
POST /auth/register
POST /auth/login
```

### Tópicos
```
POST   /topicos
GET    /topicos
PUT    /topicos/{id}
DELETE /topicos/{id}
```

### Respuestas
```
POST   /topicos/{id}/respuestas
GET    /topicos/{id}/respuestas
PUT    /topicos/{id}/respuestas/{idRespuesta}
DELETE /topicos/{id}/respuestas/{idRespuesta}
PUT    /topicos/{id}/respuestas/{idRespuesta}/solucion
```

### Cursos

```
GET    /cursos
POST   /cursos
PUT    /cursos/{id}
DELETE /cursos/{id}
```

## 🧪 Ejemplo de autenticación

1. Registro de usuario:
```
POST /auth/register
{
    "nombre": "Juan Pérez",
    "email": juan@gmail.com"
    "password": "password123" 
}
```
2. Login:
```
POST /auth/login
{
    "email":juan@gmail.com"
    "password": "password123"
}
```
Respuesta:
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQGdtYWlsLmNvbSIsImlhdCI6MTY5ODg4ODAwMCwiZXhwIjoxNjk4ODkyNjAwfQ.abc123def456ghi789"
}   
```
3. Acceso a endpoint protegido:

```
GET /topicos
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQGdtYWlsLmNvbSIsImlhdCI6MTY5ODg4ODAwMCwiZXhwIjoxNjk4ODkyNjAwfQ.abc123def456ghi789
```

## 🗃️ Base de datos
- MySQL con JPA/Hibernate
- Relación Many-to-Many entre usuarios y perfiles
  Relación One-to-Many entre:
  - Tópico → Respuestas
  - Curso → Tópicos
  - Usuario → Tópicos
  - Usuario → Respuestas
- Migraciones con Flyway

## 👨‍🏫 Documencación
- Documentación de la API con Swagger/OpenAPI


## 📄 Licencia
Proyecto educativo / de práctica. Agregar licencia si se desea publicar o reutilizar.
