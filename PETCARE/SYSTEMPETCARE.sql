-- ===============================================
-- 📘 SCRIPT: Creación de usuario y permisos en Oracle
-- PROYECTO: PetCare - CRUD MVC (Comida Gatitos)
-- ===============================================

-- 🧩 1️⃣ Crear el usuario "petcare" con su contraseña "pet"
-- Se le asigna un tablespace por defecto (users) y uno temporal (temp),
-- además de una cuota ilimitada en el tablespace "users" para evitar errores de espacio.
CREATE USER petcare IDENTIFIED BY pet
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA UNLIMITED ON users;


-- 🧠 2️⃣ Otorgar privilegios básicos
-- Estos permisos permiten al usuario conectarse y crear sus propios objetos (tablas, vistas, secuencias).

GRANT CREATE SESSION TO petcare;     -- 🔐 Permiso para iniciar sesión en la base de datos.
GRANT CREATE TABLE TO petcare;       -- 🧱 Permiso para crear tablas propias.
GRANT CREATE SEQUENCE TO petcare;    -- 🔢 Permiso para crear secuencias (útiles para IDs automáticos).
GRANT CREATE VIEW TO petcare;        -- 👁️ Permiso para crear vistas personalizadas.


-- ⚙️ 3️⃣ Otorgar permisos adicionales sobre objetos
-- Estos permisos amplían las capacidades de administración de las tablas del usuario.
-- (Útiles si el usuario gestionará sus propios objetos en desarrollo o pruebas)

GRANT ALTER ANY TABLE TO petcare;    -- ✏️ Permite modificar la estructura de cualquier tabla.
GRANT DROP ANY TABLE TO petcare;     -- 🗑️ Permite eliminar tablas.
GRANT DELETE ANY TABLE TO petcare;   -- ❌ Permite eliminar datos de cualquier tabla.


-- ✅ Con esto, el usuario "petcare" podrá conectarse desde tu aplicación
-- Spring Boot usando las credenciales configuradas en "application.properties":
-- spring.datasource.username=petcare
-- spring.datasource.password=pet
-- spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XE

-- 💡 Consejo:
-- Ejecuta este script desde un usuario con privilegios (como SYSTEM o SYS)
-- dentro de Oracle SQL Developer o SQL*Plus.


