# Memoria Actividad 9 
## TODO
- [ ] Cambiar el nombre de la app
- [x] Mejorar el diseño de las ventanas
  - [x] Arreglar lo de los botones cuadrados
- [x] No enviar informacion a ventanas que no lo necesitan
- [ ] Intentar crear una funcion para cada objeto
- [ ] 

## Windows
- [x] Poner imaguen de fondo
- [x] Cambiar color Scaffold
### Registration
- [ ] Parametros Textfield obligatorios
- [x] Pasar parametroa a sumary

## Fedback
- [ ] Pasar parámetros para Registrarse y Salir innecesarios.
- [ ] Mala decoración en general, sin imágenes, solo texto plano con colores.
- [x] Pantalla principal: Falta el botón Salir y su navegación.
- [ ] Pantalla de registro: 
  - [ ] No hay campos de texto en la pantalla de registro 
  - [X] “fun showToast” nunca se usa 
  - [ ] Toast y Snackbar deben aparecer en casos diferentes, no cuando && o cuando ||
- [ ] Pantalla de resumen: Los iconos deben tener un significado, relacionado con la información mostrada
- [ ] Mala codificación: Hay muchas advertencias sobre código no utilizado, importaciones innecesarias...

## Other
```kotlin
fun LoginButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF7043),
            disabledContainerColor = Color(0xFFFFA891),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
    ) {
        Text(text = "Start session")
    }
}
```