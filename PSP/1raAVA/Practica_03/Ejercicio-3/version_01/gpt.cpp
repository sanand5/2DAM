#include <iostream>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string>
#include <cstring>
#include <cctype>
using namespace std;

int main(int argc, char const *argv[]) {
    pid_t pid;
    int tub[2];
    char buf[1024];
    pipe(tub);

    while (true) {
        pid = fork();
        string palabra;

        if (pid < 0) {
            cout << "Error: fallo en el fork()" << endl;
            break;
        } else if (pid == 0) {
            // Hijo
            close(tub[1]); // Cierra la escritura
            read(tub[0], buf, sizeof(buf)); // Lee el pipe
            close(tub[0]); // Cierra la lectura
            string modified = buf;
            for (char &c : modified) {
                c = toupper(c);
            }
            cout << "Palabra convertida a mayúsculas: " << modified << endl;
            exit(0); // El hijo sale después de mostrar el resultado.
        } else {
            // Padre
            close(tub[0]); // Cierra la lectura
            cout << "Ingresa una palabra (o 'salir' para terminar): ";
            getline(cin, palabra);
            if (palabra == "salir") {
                close(tub[1]); // Asegura que se cierre la escritura en caso de "salir"
                break; // Termina el bucle si se ingresa "salir"
            }
            write(tub[1], palabra.c_str(), palabra.length() + 1); // Escribe en el pipe (incluyendo el carácter nulo)
            close(tub[1]); // Cierra la escritura
            wait(NULL); // Espera a que el hijo termine
        }
    }

    return 0;
}