#include <iostream>
#include <cstring>
#include <regex>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

using namespace std;
class Juego
{
public:
    void jugar();
    string generateOption();
private:
};

void Juego::jugar()
{
    string rojo = "\033[1;31m", normal = "\033[0m";
    pid_t pid;
    int fd[2], retorno = 0, pUser = 0, pCPU = 0;
    char buf[1024];
    string opcion;
    
    if (pipe(fd) == -1){
        cout << "error"; // TODO
    }
    for (int i = 0; i < 3; i++){
        pid = fork();
        switch (pid){
        case -1:
            cout << "error";
            break;
        case 0:
            opcion = generateOption();
            char array[sizeof(opcion)];
            strcpy(array, opcion.c_str());
            close(fd[0]);                       // Cierro la lectura
            write(fd[1], array, strlen(array)); // Escribe en pipe
            close(fd[1]);                       // Cierra la escritura
            break;
        default:
            wait(NULL);
            close(fd[1]);
            read(fd[0], buf, sizeof(buf));
            close(fd[0]);
            int a;
            cout << buf << endl;
            break;
        }
    }
}
string Juego::generateOption(){
    string opciones[] = {"piedra", "papel", "tijeras"};
    int ran = rand() % 3;
    return opciones[ran];
}

int main(int argc, char const *argv[]){
    srand((unsigned)time(NULL));
    Juego ej;
    ej.jugar();
    return 0;
}
/*
Ja se perque me trau sempre pedra perque el buffer es queda en el ultim que es la pedra, si arregle lo faga primer el fill i despres el pare segurament s'arregle asoles lo de que semre me trau pedra.
*/