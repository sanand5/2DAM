import sys
try:
    numero = int(input("Introduce un numero: "))
    print("El numero es", numero)
except ValueError:
    print("La conversión de este número no ha tenido exito", file=sys.stderr)
    sys.exit()
    