# 📘 Guía de Usuario — Sistema de Gestión Ferretería "LA POLI"

Esta guía explica cómo usar la aplicación paso a paso: iniciar sesión, gestionar el inventario, registrar ventas con los datos del cliente para la factura, y consultar reportes.

---

## 1. Inicio de sesión

Al abrir la aplicación verás la pantalla de **Login**, donde debes ingresar tu correo y contraseña.

Usuarios disponibles:

| Correo | Contraseña | Rol |
| admin@ferreteria.com | admin123 | ADMIN |
| cajero@ferreteria.com | cajero123 | CAJERO |
| reportes@ferreteria.com | report123 | REPORTES |

**Validaciones del login:**
- Ningún campo puede estar vacío.
- La contraseña debe tener mínimo 6 caracteres.

Si los datos son correctos, entrarás automáticamente al **Dashboard** correspondiente a tu rol.

---

## 2. El Dashboard (panel principal)

Al ingresar verás un saludo con tu nombre y tu rol, y un menú lateral con las opciones disponibles **según tu rol**:

| Rol | Opciones visibles |
| **ADMIN** | Inventario, Ventas, Reportes |
| **CAJERO** | Inventario, Ventas *(Reportes no aparece)* |
| **REPORTES** | Solo Reportes *(Inventario y Ventas no aparecen)* |

> Los botones que no corresponden a tu rol **no se muestran en absoluto** en el menú, no solo se deshabilitan.

También tienes un botón de **Cerrar sesión** para volver al Login.

---

## 3. Módulo de Inventario

Aquí se gestionan los productos de la ferretería.

**Para registrar un producto nuevo:**
1. Llena el formulario: código de barras, nombre, marca, precio y stock.
2. Presiona **Guardar**.

**Para modificar un producto:**
1. Selecciónalo en la tabla (se llenará el formulario automáticamente).
2. Cambia los datos que necesites.
3. Presiona **Guardar** para actualizar.

**Para eliminar un producto:**
1. Selecciónalo en la tabla.
2. Presiona **Eliminar**.
3. Confirma la acción en el mensaje que aparece.

**Validaciones:**
- Código y nombre son obligatorios.
- El precio debe ser mayor a 0.
- El stock no puede ser negativo.
- No se puede eliminar un producto que ya tenga ventas registradas (el sistema te avisará con un mensaje).

---

## 4. Módulo de Ventas

Aquí se registran las ventas, incluyendo los datos del cliente para generar la factura.

**Pasos para registrar una venta:**
1. Ingresa el **Nombre completo** del cliente.
2. Ingresa la **Cédula / RUC** del cliente.
3. Selecciona el **producto** del listado desplegable.
4. Escribe la **cantidad** a vender.
5. El **Total** se calcula automáticamente según el precio y la cantidad.
6. Presiona **Registrar venta**.
7. Aparecerá una ventana con el resumen tipo **factura** (cliente, cédula, vendedor, producto, cantidad y total). Presiona **Aceptar** para cerrarla.

**Qué pasa automáticamente al registrar la venta:**
- El **stock del producto se descuenta** según la cantidad vendida.
- La venta queda guardada en el historial, visible en la tabla inferior con el nombre del cliente.

**Validaciones:**
- Nombre y cédula del cliente son obligatorios.
- Debe seleccionarse un producto.
- La cantidad debe ser un número entero mayor a 0.
- No se puede vender más unidades de las que hay en stock (el sistema te lo indica con el stock disponible).

---

## 5. Módulo de Reportes

Muestra el historial completo de ventas realizadas: cliente, producto, cantidad, total y fecha, además del **total acumulado vendido**.

Este módulo es de **solo consulta**; no permite editar ni eliminar ventas desde aquí.

---

## 6. Cerrar sesión

Desde cualquier pantalla puedes volver al Dashboard con el botón **Volver al menú**, y desde el Dashboard puedes usar **Cerrar sesión** para regresar al Login y permitir que otro usuario ingrese con su propia cuenta.

---

## 7. Solución de problemas comunes

| Problema | Posible causa |
|---|---|
| No puedo iniciar sesión | Verifica que el correo y contraseña sean exactos, sin espacios. |
| No aparece un producto en Ventas | Puede que su stock esté en 0 o que haya sido eliminado. |
| No me deja registrar la venta | Revisa que llenaste nombre y cédula del cliente, y que la cantidad no supere el stock disponible. |
| No veo el botón de Reportes | Es normal si tu rol es CAJERO; ese módulo solo lo ve ADMIN y REPORTES. |
| Error de conexión al iniciar | Verifica tu conexión a internet, ya que la base de datos está alojada en la nube (Neon). |
