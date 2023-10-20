#include <iostream>
#include <string>
#include <locale>
#include <cstring>
#include <iostream>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <errno.h>
#include <algorithm>

int main(int argc, char *argv[]) {
    if (argc != 2) {
        std::cerr << "Uso: " << argv[0] << " palabra" << std::endl;
        return -1;
    }

    std::string palabra = argv[1];

    // Convierte la palabra a mayúsculas
    for (int i = 0; i < palabra.length(); ++i) {
        palabra[i] = std::toupper(palabra[i]);
    }
    // Crea un arreglo de caracteres (char[]) y copia la palabra convertida
    
    std::cout << palabra << std::endl;
    return 0;
}
