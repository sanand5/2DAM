function verificarRespuestas() {
  // Desmarcar todas las opciones incorrectas y correctas
  var opciones = document.querySelectorAll(
    ".respuesta-incorrecta, .respuesta-correcta"
  );
  opciones.forEach(function (opcion) {
    opcion.classList.remove("respuesta-incorrecta", "respuesta-correcta");
  });

  var respuestasUsuario = {
    pregunta1: obtenerSeleccion("pregunta1"),
    pregunta2: obtenerSeleccion("pregunta2"),
    pregunta3: obtenerSeleccion("pregunta3"),
    pregunta4: obtenerSeleccion("pregunta4"),
    pregunta5: obtenerSeleccion("pregunta5"),
    pregunta6: obtenerSeleccion("pregunta6"),
    pregunta7: obtenerSeleccion("pregunta7"),
    pregunta8: obtenerSeleccion("pregunta8"),
    pregunta9: obtenerSeleccion("pregunta9"),
    pregunta10: obtenerSeleccion("pregunta10"),
    pregunta11: obtenerSeleccion("pregunta11"),
    pregunta12: obtenerSeleccion("pregunta12"),
    pregunta13: obtenerSeleccion("pregunta13"),
    pregunta14: obtenerSeleccion("pregunta14"),
    pregunta15: obtenerSeleccion("pregunta15"),
    pregunta16: obtenerSeleccion("pregunta16"),
    pregunta17: obtenerSeleccion("pregunta17"),
    pregunta18: obtenerSeleccion("pregunta18"),
    pregunta19: obtenerSeleccion("pregunta19"),
    pregunta20: obtenerSeleccion("pregunta20"),
    pregunta21: obtenerSeleccion("pregunta21"),
    pregunta22: obtenerSeleccion("pregunta22"),
    pregunta23: obtenerSeleccion("pregunta23"),
    pregunta24: obtenerSeleccion("pregunta24"),
    pregunta25: obtenerSeleccion("pregunta25"),
    pregunta26: obtenerSeleccion("pregunta26"),
    pregunta27: obtenerSeleccion("pregunta27"),
    pregunta28: obtenerSeleccion("pregunta28"),
    pregunta29: obtenerSeleccion("pregunta29"),
    pregunta30: obtenerSeleccion("pregunta30"),
    pregunta31: obtenerSeleccion("pregunta31"),
    pregunta32: obtenerSeleccion("pregunta32"),
    pregunta33: obtenerSeleccion("pregunta33"),
    pregunta34: obtenerSeleccion("pregunta34"),
    pregunta35: obtenerSeleccion("pregunta35"),
    pregunta36: obtenerSeleccion("pregunta36"),
    pregunta37: obtenerSeleccion("pregunta37"),
    pregunta38: obtenerSeleccion("pregunta38"),
    pregunta39: obtenerSeleccion("pregunta39"),
    pregunta40: obtenerSeleccion("pregunta40"),
    pregunta41: obtenerSeleccion("pregunta41"),
    pregunta42: obtenerSeleccion("pregunta42"),
    pregunta43: obtenerSeleccion("pregunta43"),
    pregunta44: obtenerSeleccion("pregunta44"),
    pregunta45: obtenerSeleccion("pregunta45"),
    pregunta46: obtenerSeleccion("pregunta46"),
    pregunta47: obtenerSeleccion("pregunta47"),
    pregunta48: obtenerSeleccion("pregunta48"),
    pregunta49: obtenerSeleccion("pregunta49"),
    pregunta50: obtenerSeleccion("pregunta50"),
    pregunta51: obtenerSeleccion("pregunta51"),
    pregunta52: obtenerSeleccion("pregunta52"),
    pregunta53: obtenerSeleccion("pregunta53"),
    pregunta54: obtenerSeleccion("pregunta54"),
    pregunta55: obtenerSeleccion("pregunta55"),
    pregunta56: obtenerSeleccion("pregunta56"),
    pregunta57: obtenerSeleccion("pregunta57"),
    pregunta58: obtenerSeleccion("pregunta58"),
    pregunta59: obtenerSeleccion("pregunta59"),
    pregunta60: obtenerSeleccion("pregunta60"),
    pregunta61: obtenerSeleccion("pregunta61"),
    pregunta62: obtenerSeleccion("pregunta62"),
    pregunta63: obtenerSeleccion("pregunta63"),
    pregunta64: obtenerSeleccion("pregunta64"),
    pregunta65: obtenerSeleccion("pregunta65"),
    pregunta66: obtenerSeleccion("pregunta66"),
    pregunta67: obtenerSeleccion("pregunta67"),
    pregunta68: obtenerSeleccion("pregunta68"),
    pregunta69: obtenerSeleccion("pregunta69"),
    pregunta70: obtenerSeleccion("pregunta70"),
    pregunta71: obtenerSeleccion("pregunta71"),
    pregunta72: obtenerSeleccion("pregunta72"),
    pregunta73: obtenerSeleccion("pregunta73"),
    pregunta74: obtenerSeleccion("pregunta74"),
    pregunta75: obtenerSeleccion("pregunta75"),
    pregunta76: obtenerSeleccion("pregunta76"),
    pregunta77: obtenerSeleccion("pregunta77"),
    pregunta78: obtenerSeleccion("pregunta78"),
    pregunta79: obtenerSeleccion("pregunta79"),
    pregunta80: obtenerSeleccion("pregunta80"),
    pregunta81: obtenerSeleccion("pregunta81"),
    pregunta82: obtenerSeleccion("pregunta82"),
    pregunta83: obtenerSeleccion("pregunta83"),
    pregunta84: obtenerSeleccion("pregunta84"),
    pregunta85: obtenerSeleccion("pregunta85"),
    pregunta86: obtenerSeleccion("pregunta86"),
    pregunta87: obtenerSeleccion("pregunta87"),
    pregunta88: obtenerSeleccion("pregunta88"),
    pregunta89: obtenerSeleccion("pregunta89"),
    pregunta90: obtenerSeleccion("pregunta90"),
    pregunta91: obtenerSeleccion("pregunta91"),
    pregunta92: obtenerSeleccion("pregunta92"),
    pregunta93: obtenerSeleccion("pregunta93"),
    pregunta94: obtenerSeleccion("pregunta94"),
    pregunta95: obtenerSeleccion("pregunta95"),
    pregunta96: obtenerSeleccion("pregunta96"),
    pregunta97: obtenerSeleccion("pregunta97"),
    pregunta98: obtenerSeleccion("pregunta98"),
    pregunta99: obtenerSeleccion("pregunta99"),
  };

  aplicarEstilosRespuestas(respuestasUsuario);

  var porcentajeCorrectas = calcularPorcentajeCorrectas(respuestasUsuario);
  var porcentajeIncorrectas = 100 - porcentajeCorrectas;

  var resultado =
    "Porcentaje de respuestas correctas: " +
    porcentajeCorrectas.toFixed(2) +
    "%" +
    "<br>Porcentaje de respuestas incorrectas: " +
    porcentajeIncorrectas.toFixed(2) +
    "%";

  document.getElementById("resultado").innerHTML = resultado;
}

function limpiarRespuestas() {
  var opciones = document.querySelectorAll('input[type="radio"]');
  opciones.forEach(function (opcion) {
    opcion.checked = false;
    opcion.parentNode.classList.remove(
      "respuesta-incorrecta",
      "respuesta-correcta"
    );
  });

  document.getElementById("resultado").innerHTML = "";
}

function obtenerSeleccion(nombrePregunta) {
  var opciones = document.getElementsByName(nombrePregunta);
  for (var i = 0; i < opciones.length; i++) {
    if (opciones[i].checked) {
      return opciones[i].value;
    }
  }
  return null; // Si ninguna opción está seleccionada
}

function aplicarEstilosRespuestas(respuestasUsuario) {
  var fieldsets = document.querySelectorAll("fieldset");
  fieldsets.forEach(function (fieldset, index) {
    var respuestaCorrecta = fieldset.getAttribute("data-respuesta-correcta");
    var opcion = fieldset.querySelector("input:checked");
    if (opcion && opcion.value === respuestaCorrecta) {
      opcion.parentNode.classList.add("respuesta-correcta");
    } else if (opcion) {
      opcion.parentNode.classList.add("respuesta-incorrecta");
    }
  });
}

function calcularPorcentajeCorrectas(respuestasUsuario) {
  var correctas = 0;
  var fieldsets = document.querySelectorAll("fieldset");
  fieldsets.forEach(function (fieldset, index) {
    var respuestaCorrecta = fieldset.getAttribute("data-respuesta-correcta");
    if (respuestasUsuario["pregunta" + (index + 1)] === respuestaCorrecta) {
      correctas++;
    }
  });
  return (correctas / fieldsets.length) * 100;
}

function actualizarEstilos(nombrePregunta) {
  var opciones = document.getElementsByName(nombrePregunta);
  opciones.forEach(function (opcion) {
    opcion.parentNode.classList.remove(
      "respuesta-incorrecta",
      "respuesta-correcta"
    );
  });
}
