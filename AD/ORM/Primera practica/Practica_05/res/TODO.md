# Cosas que hacer en el proyecto
## Generales
- [ ] Mensajes informativos de ok
### Alumnos
- [x] Dar de alta a alumnos.
  - [x] El nia no se puede repetir
- [x] Dar de baja a alumnos
  - [x] Dar de baja a un alumno que tine una matricula y notas
- [x] Mostrar la lista de los alumnos
### Modulos
- [x] Dar de alta un modulo
- [x] Dar de baja un modulo
  - [x] Que tiene registrado unos alumnos
  - [x] Si pones 0 que salga en pedir modulo
- [x] Mostrar los modulos
- [x] Matricular a un alumno en un modulo
  - [x] Dar de alta a un alumno en el mismo modulo dos veces
  - [x] Si pones 0 que salga en pedir nia
  - [x] Si pones 0 que salga
### Matriculas
- [x] calificar a un alumno en un modulo
  - [x] Quando pido el id de el modulo, alumno no esta registrado en ese modulo el programa sale
- [x] modificar una nota de un alumno
  - [x] se duplican las notas
- [x] mostrar notas de un alumno
- [x] mostrar notas de un modulo (creo) @revised
- [x] mostrar notas de todos los alumnos
### Errores
- [x] Al intentar eliminar un modulo, tanto si existe como si no y tambien si pongo 0
- [x] Al intentar matricular a un alumno quando pregunta el modulo y pongo 0
- [x] Al mostrar las notas de un alumno (creo que es porque el alumno no tenia notas)
- [x] No se pueden actualizar las notas, sale un error: java.lang.NumberFormatException
  - [x] ya no sale el error pero no modifica la db

Dede powersheell, funciona con:
```
& 'C:\Users\andre\.vscode\extensions\redhat.java-1.24.0-win32-x64\jre\17.0.8.1-win32-x86_64\bin\java.exe' '@C:\Users\andre\AppData\Local\Temp\cp_bfybqqzp2kb4oyum0i2mrt5ir.argfile' 'app.app' 
```
