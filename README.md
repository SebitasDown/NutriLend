# NutriLens API Documentation

NutriLens es una aplicación de gestión nutricional impulsada por IA. Esta API permite gestionar usuarios, autenticación y generación de recetas personalizadas.


## 🔐 Autenticación (`/auth`)

### Registro de Usuario
**POST** `/auth/register`

Crea una nueva cuenta de usuario y su perfil nutricional.

**Cuerpo de la petición (JSON):**
```json
{
  "displayName": "Nombre de Usuario",
  "email": "usuario@example.com",
  "password": "password123",
  "weight": 70.5,
  "height": 175.0,
  "age": 25,
  "preference": "NORMAL", // O "VEGETARIANO"
  "meals": 3,
  "goal": "LOSE_WEIGHT", // O "MAINTAIN", "GAIN_MUSCLE"
  "activityLevel": "LOW" // O "MEDIUM", "HIGH"
}
```

### Inicio de Sesión
**POST** `/auth/login`

**Cuerpo de la petición (JSON):**
```json
{
  "email": "usuario@example.com",
  "password": "password123"
}
```

**Respuesta Exitosa:**
```json
{
  "accessToken": "eyJhbG...",
  "refreshToken": "eyJhbG..."
}
```

### Login con Google
**POST** `/auth/google`

**Cuerpo de la petición (JSON):**
```json
{
  "googleSub": "string",
  "email": "string",
  "name": "string",
  "avatarUrl": "string"
}
```

---

## 👤 Usuario (`/api/users`)
*Requiere Header: `Authorization: Bearer <TOKEN>`*

### Obtener Perfil
**GET** `/api/users/profile`

### Actualizar Perfil
**PUT** `/api/users/profile`

**Cuerpo de la petición (JSON):**
```json
{
  "displayName": "Nuevo Nombre",
  "avatarUrl": "http://imagen.com/foto.jpg",
  "weight": 72.0,
  "height": 175.0,
  "age": 26,
  "preference": "VEGETARIANO",
  "meals": 4,
  "goal": "GAIN_MUSCLE",
  "activityLevel": "MEDIUM"
}
```

---

## 🥗 Recetas (`/api/recipes`)
*Requiere Header: `Authorization: Bearer <TOKEN>`*

### Obtener Recetas Sugeridas
**GET** `/api/recipes?typeFood=LUNCH`

El ID del usuario se extrae automáticamente del token.

**Parámetros:**
- `typeFood` (Obligatorio): `BREAKFAST`, `LUNCH`, `DINNER`, `SNACK`.

**Respuesta:**
```json
[
  {
    "id": "65...",
    "name": "Ensalada de Quinoa",
    "typeFood": "LUNCH",
    "time": 20,
    "description": "Una ensalada fresca y nutritiva...",
    "portion": 1,
    "calories": 350,
    "image": "url_de_la_imagen",
    "ingredients": [
      { "name": "Quinoa", "quantity": "100g" }
    ],
    "steps": [
      "Lavar la quinoa",
      "Cocer por 15 min"
    ]
  }
]
```
