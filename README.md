# 🥗 NutriLens API

NutriLens es una plataforma avanzada de gestión nutricional impulsada por Inteligencia Artificial. Permite a los usuarios realizar un seguimiento detallado de su ingesta de alimentos mediante análisis de imágenes y audio, recibir recomendaciones personalizadas y gestionar su perfil nutricional de manera eficiente.

---

## 🚀 Live Demo & API Documentation
Puedes interactuar con la API en tiempo real y ver la documentación detallada de todos los endpoints aquí:

👉 **[Live Swagger UI Documentation](https://nutrilens-0x37.onrender.com/swagger-ui/index.html)**

---

## ✨ Características Principales
- **🤖 Análisis de Comidas con IA**: Sube fotos o audios de tus comidas para obtener un desglose nutricional automático (calorías, proteínas, carbohidratos, grasas).
- **🥘 Generación de Recetas**: Obtén sugerencias de recetas personalizadas basadas en tus objetivos y preferencias.
- **📈 Seguimiento Nutricional**: Historial completo de consumos y progreso diario hacia tus metas calóricas.
- **💬 Asistente Nutricional**: Chat integrado con IA para resolver dudas sobre alimentación y salud.
- **🔐 Seguridad y Autenticación**: Soporte para autenticación tradicional y Google OAuth2.

---

## 🛠️ Tecnologías Usadas
- **Backend**: Java 21, Spring Boot 3.3.5
- **Seguridad**: Spring Security, JWT (JSON Web Tokens)
- **Base de Datos**: MySQL (Relacional), MongoDB (Conversaciones)
- **IA**: Google Gemini AI (Análisis y Chat)
- **Multimedia**: Cloudinary (Almacenamiento de imágenes)
- **Documentación**: SpringDoc OpenAPI (Swagger)

---

## 🔐 Endpoints de Autenticación (`/auth`)
Para la mayoría de los endpoints, se requiere un header `Authorization: Bearer <TOKEN>`.

### Registro de Usuario
**POST** `/auth/register`
*Crea una nueva cuenta y perfil nutricional.*

### Inicio de Sesión
**POST** `/auth/login`
*Retorna `accessToken` y `refreshToken`.*

### Login con Google
**POST** `/auth/google`

---

## 👤 Usuario (`/api/users`)
- **GET** `/api/users/profile`: Obtener perfil actual.
- **PUT** `/api/users/profile`: Actualizar datos físicos y metas.

---

## 🥗 Comidas y Análisis (`/api/meals`)
- **POST** `/api/meals/analyze`: Subir imagen/audio para análisis con IA.
- **GET** `/api/meals/history`: Historial de comidas analizadas.
- **GET** `/api/meals/summary`: Resumen nutricional del día.

---

## 🍲 Recetas y Chat
- **GET** `/api/recipes`: Sugerencias de recetas según el tipo (Desayuno, Almuerzo, etc.).
- **GET** `/api/chat/history/{conversationId}`: Recuperar mensajes de una conversación.
- **POST** `/api/chat/history`: Guardar mensajes nuevos en el historial.

---

## ⚙️ Desarrollo Local
1. Clona el repositorio.
2. Configura las variables de entorno en un archivo `.env` en la raíz del proyecto:

```env
# Database Configuration
SERVER_PORT=8080
MYSQL_HOST=localhost
MYSQL_PORT=3306
MYSQL_DB=nutrilens
MYSQL_USER=root
MYSQL_PASSWORD=password
MONGODB_URI=mongodb://localhost:27017/nutrilens

# Security
JWT_SECRET=your_super_secret_jwt_key_here
JWT_EXPIRATION=86400000

# external APIs
CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret
GEMINI_API_KEY=your_gemini_api_key_here
GROQ_API_KEY=gsk_your_groq_api_key_here

# Mail Configuration (para verificacion de email y recuperacion de contrasena)
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_app_password
```

3. Ejecuta con `./mvnw spring-boot:run`.
