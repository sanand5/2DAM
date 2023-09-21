#Programa un algoritmo que pida 4 numeros al usuario los ordene de mayor a menor y saque por pantalla la suma, la resta y la mutiplicación de todos ellos
numeros = [int(input("Dime un numero : ")), int(input("Dime un numero : ")), int(input("Dime un numero : ")), int(input("Dime un numero : ")), int(input("Dime un numero : "))]
numeros.sort(reverse=True)
suma = 0
multiplicacion = 1
print("ORDENADOS DE MAYOR A MENOR")
for n in numeros:
    print(n)
    suma+=n
    multiplicacion*=n
print(f"El sumatorio es {suma}")
print(f"La multiplicación es {multiplicacion}")
