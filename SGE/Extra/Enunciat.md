# Enunciado Solterra Energia

Solterra Energia es una empresa que necesita tres modelo nuevos en su ERP. Esta empresa quiere gestionar las instalaciones que tiene que hacer o que estan hechas.

El primero modulo gestionara los Propietarios, en este modelo debes guardar los datos de los propietarios de las casas:
- Nombre
- Apellidos
- Numero de telefono
- Email
- Casa o casas
 
El segundo modelo gestionara las casas y estas tendran los siguientes campos: 
- Propietarios
- Direccion
- Fecha de inicio de la instalación
- Fecha final
- Estado (Por hacer / En proceso / Finalizada)
- Finalizada (Booleano)
- Tareas

> Si una casa no tiene tareas su estado sera "Por hacer", si esta esta tiene tareas se asiganra como "en proceso" y si se presiona el boton de finalizada el estado pasara a ser Finalizada, ademas deberas buscar como poder aplicar un filtro ver las casas segun su estado.

Y el tercer modelo sera una Lista de tareas asignadas a cada casa, los campos seran:
- Nombre de la tarea
- Descripción de la tarea