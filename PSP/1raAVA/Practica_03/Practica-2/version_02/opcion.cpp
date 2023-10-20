#include <iostream>
#include <ctime>
#include <cstdlib>
#include <random>

int main()
{
    std::srand(static_cast<unsigned int>(std::time(nullptr)));
    int eleccion = std::rand() % 3;
    std::string opcion;
    if (eleccion == 0)
    {
        opcion = "piedra";
    }
    else if (eleccion == 1)
    {
        opcion = "papel";
    }
    else
    {
        opcion = "tijeras";
    }
    std::cout << "Maquina: " << opcion <<" "<< std::endl;
    return eleccion;
}
