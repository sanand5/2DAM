#include <iostream>
#include <cstring>
#include <regex>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

using namespace std;
class Juego
{
public:
    void jugar();

private:
};

void Juego::jugar()
{
    string rojo = "\033[1;31m", normal = "\033[0m";
    pid_t pid;
    int fd[2], retorno = 0, pUser = 0, pCPU = 0, ran;
    char buf[1024];
    string opcion;
    srand((unsigned)time(NULL));
    if (pipe(fd) == -1)
    {
        cout << "error"; // TODO
    }

    pid = fork();
    string opciones[] = {"piedra", "papel", "tijeras"};
    for (int i = 0; i < 3; i++)
    {
        switch (pid)
        {
        case -1:
            cout << "error";
            break;
        case 0:
            ran = rand() % 3;
            cout << ran << endl;
            char array[sizeof(opciones[ran])];
            strcpy(array, opciones[ran].c_str());
            close(fd[0]);                       // Cierro la lectura
            write(fd[1], array, strlen(array)); // Escribe en pipe
            close(fd[1]);                       // Cierra la escritura
            break;
        default:
            close(fd[1]);
            read(fd[0], buf, sizeof(buf));
            close(fd[0]);
            cout << buf << endl;
            break;
        }
    }
}
int main(int argc, char const *argv[])
{
    Juego ej;
    ej.jugar();
    /* code */
    return 0;
}
