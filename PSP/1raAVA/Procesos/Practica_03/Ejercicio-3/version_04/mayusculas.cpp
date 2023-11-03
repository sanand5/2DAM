#include <iostream>
int main(int argc, char *argv[]) {
    std::string palabra = argv[1];
    for (int i = 0; i < palabra.length(); ++i) {
        palabra[i] = std::toupper(palabra[i]);
    }
    std::cout <<">"<< palabra << std::endl;
    return 0;
}