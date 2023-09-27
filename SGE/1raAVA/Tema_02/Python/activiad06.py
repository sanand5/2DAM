import sys
try:
    a = int(input("Introduce un numero a: "))
    b = int(input("Introduce un numero b: "))
    if 1 <= a <= 10 :
        print(f"El numero a ({a}) esta entre 0 y 10")    
    else : 
        print(f"El numero a ({a}) no esta entre 0 y 10")    
    if 1 <= b <= 10 :
        print(f"El numero b ({b}) esta entre 0 y 10")    
    else : 
        print(f"El numero b ({b}) no esta entre 0 y 10")    
except ValueError:
    print("La conversión de este número no ha tenido exito", file=sys.stderr)
    sys.exit()
    