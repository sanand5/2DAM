import random
max = 99
min = 0
intentos = 0
num = 0
a = random.randint(min,max)
print("Bienvenido a mi minijuego, el juego consistra en que debes hadibinar el numero que jo el elegido. El numero esta entre 1-99")


while num != a :
    try:
        num = int(input(f"El numero esta entre {int(min)}-{int(max)} que numero crees que es? "))
        if min > num or max < num:
            print(f"El numero debe estar entre {int(min)}-{int(max)}")
        elif num > a:
            max = num
            intentos+=1
        elif num < a:
            min = num
            intentos+=1
    except ValueError:
        print("Debes introducir un entero")
print(f"""Enorabuena, lo has adivinado!!!
      Numero de inventos {intentos}
      """)

# 
#
#
#