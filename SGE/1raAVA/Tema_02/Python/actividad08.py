import random
max = 100
min = 0
intentos = 0
a = random.randint(min,max)
print("Bienvenido a mi minijuego, el juego consistra en que debes hadibinar el numero que jo el elegido. El numero esta entre 1-100")
num=0


while num != a :
    try:
        num = int(input(f"El numero esta entre {min}-{max} que numero crees que es? {a} -"))
        if min > num or max < num:
            print(f"El numero debe estar entre {min}-{max}")
        elif a < max/2 and num < max/2:
            max/=2
        elif a > max/2 and num > max/2:
            min=max/2
    except ValueError:
        print("Debes introducir un entero")
print("Enorabuena, lo has adivinado!!!")