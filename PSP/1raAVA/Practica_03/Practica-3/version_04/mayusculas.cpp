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
    std::string palabra = argv[1];
    for (int i = 0; i < palabra.length(); ++i) {
        palabra[i] = std::toupper(palabra[i]);
    }
    std::cout <<">"<< palabra << std::endl;
    return 0;
}
//TODO
//Comprobar imports