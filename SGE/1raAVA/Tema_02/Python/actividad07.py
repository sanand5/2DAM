#En caso que el usuario se equivoque vuelve a pedir el numero
import sys
    
while True:
    a = input("Introduce un numero a: ")
    try:
        a = int(a)        
    except:
        pass
    else:
        if 1 <= a <= 10:
            break
print(f"Estamos seguros que {a} es un numero y esta comprendido entre 1 y 10")

