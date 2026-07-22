# 🛠️ Ferretería "LA POLI" — Sistema de Gestión

## 📄 Descripción del proyecto

Aplicación de escritorio desarrollada en JavaFX aplicando Programación Orientada a Objetos, con conexión a una base de datos PostgreSQL en la nube (Neon).

El sistema resuelve el control manual de inventario y ventas de una ferretería, permitiendo registrar productos, gestionar el stock, registrar ventas con cálculo automático del total y consultar reportes del histórico vendido. El acceso está restringido según el rol del usuario (Administrador, Cajero o Reportes), de modo que cada uno solo ve las opciones que le corresponden.

---

## 👥 Integrantes

- Sergio Toapanta
- Cristhian Veliz

Asignatura: Programación Orientada a Objetos
Docente: Ing. Yadira Gissela Franco Rocha, Mgs.
Período académico: 2026-A

---

## 🧰 Herramientas usadas

| Herramienta | Uso |
|---|---|
| Java 21 | Lenguaje de programación |
| JavaFX 21 | Interfaz gráfica de escritorio |
| Maven | Gestión de dependencias y construcción del proyecto |
| PostgreSQL (Neon) | Base de datos relacional en la nube |
| JDBC | Conexión entre la aplicación y la base de datos |
| CSS | Estilos visuales de la interfaz |
| Git y GitHub | Control de versiones |
| IntelliJ IDEA | Entorno de desarrollo |

---

## ▶️ Pasos de instalación

1. Clonar el repositorio:
   git clone https://github.com/sergiopdf-x/Proyecto_Final_Ferreteria.git

2. Abrir el proyecto en un IDE con soporte para Maven y JavaFX (IntelliJ IDEA, NetBeans o Eclipse).

3. Verificar la conexión a la base de datos en el archivo:
   src/main/java/com/example/ferreteria/db/Conexion.java
   (URL, usuario y contraseña de PostgreSQL).

4. Ejecutar el proyecto con Maven:
   mvn clean javafx:run

5. Alternativa: ejecutar directamente el archivo .jar generado, sin necesidad de abrir el IDE.

6. Iniciar sesión con alguno de los usuarios de prueba:

   | Correo | Contraseña | Rol |
   |---|---|---|
   | admin@ferreteria.com | admin123 | ADMIN |
   | cajero@ferreteria.com | cajero123 | CAJERO |
   | reportes@ferreteria.com | report123 | REPORTES |

---

