# 🛠️ Ferretería "LA POLI" — Sistema de Gestión

Aplicación de escritorio desarrollada en **JavaFX** aplicando Programación Orientada a Objetos, con conexión a una base de datos **PostgreSQL** en la nube (Neon) y control de acceso por roles.

Permite gestionar el inventario de una ferretería, registrar ventas con descuento automático de stock y consultar reportes del histórico vendido.

---

## 📋 Funcionalidades

- **Inicio de sesión** con validación de credenciales contra la base de datos.
- **Control de acceso por roles**: `ADMIN`, `CAJERO` y `REPORTES`, cada uno con distintas opciones habilitadas en el panel principal.
- **Gestión de inventario (CRUD)**: registrar, modificar, eliminar y listar productos.
- **Registro de ventas**: selección de producto, cálculo automático del total y descuento de stock.
- **Reportes**: historial de ventas y total vendido acumulado.
- **Validaciones**: campos obligatorios, valores numéricos positivos, confirmación antes de eliminar y control de integridad referencial (no se puede borrar un producto con ventas asociadas).

---

## 🧰 Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| Java 21 | Lenguaje principal |
| JavaFX 21 | Interfaz gráfica de escritorio |
| Maven | Gestión de dependencias y build |
| PostgreSQL (Neon) | Base de datos en la nube |
| JDBC | Conexión a la base de datos |
| CSS | Estilos de la interfaz |
| Git y GitHub | Control de versiones |

---

## 🏗️ Arquitectura del proyecto

El proyecto está organizado por capas, siguiendo el paquete `com.example.ferreteria`:
