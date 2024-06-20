# Examen Andreu Sanz Sanz Tipo B
## Crear el modulo
Para crear el modulo devemos ejecutar en la terminal de docker `cd /mnt/extra-addons` y `odoo scaffold gandibandreu`

## Crear los modelos
Luego creamos los modelos, en mi caso he creado coches, proveedores y bancos, de momento todos los campos son char y en un futuro los modificare.
![alt text](image.png)
<div style="page-break-before:always"></div>

## Seguridad
Debemos añadir los modelos al fichero de seguridad además tambien debemos modificar el manifest.py para permitir la seguridad además modifico el nombre de el autor.
![alt text](image-1.png)
![alt text](image-2.png)
<div style="page-break-before:always"></div>

## Ajustar las vistas
PD: Al actualizar las vistas me he dado quenta de que el modelo campo no debe estar en el fichero de seguridad
![alt text](image-3.png)
![alt text](image-4.png)
![alt text](image-5.png)
<div style="page-break-before:always"></div>

## Configurar los modulos
Con esto ya tendiramos las relaciones:
![alt text](image-7.png)

Ahora los ids: 
![alt text](image-8.png)
![alt text](image-9.png)

Por último he cambiado el nombre de la variable name por nombre, para que se pueda ver como "Nombre" en las tablas