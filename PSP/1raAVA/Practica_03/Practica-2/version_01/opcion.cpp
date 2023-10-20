#include <iostream>
#include <ctime>
#include <cstdlib>
#include <random>

int main()
{

    std::srand(static_cast<unsigned int>(std::time(nullptr)));

    std::mt19937 rng(std::random_device{}());
    std::uniform_int_distribution<int> distribution(0, 2);

    //int eleccion = std::rand() % 3;
    int eleccion = distribution(rng);

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

    //std::cout << "del: "<< eleccion << std::endl;
    std::cout << "Maquina: " << opcion <<" "<< std::endl;

    return eleccion;
}
