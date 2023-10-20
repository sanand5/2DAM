
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
using namespace std;
void mayusculas()
{
    pid_t pid;
    int tub[2];
    char buf[1024] = "";
    pipe(tub);
    pid = fork();
    if (pid < 0)
    { /* Error */
        cout << "Error: fallo en el fork()" << endl;
    }
    else if (pid == 0)
    {
        /* Hijo */
        cout << "del: PROCESO PADRE" << endl; //del
        read(tub[0], buf, sizeof(buf)); // lee el pipe
        close(tub[0]);                  // Cierro la lectura
        cout <<"del: el buffer es: "<< buf << endl; //del

        const char *array = "./mayusculas ";
        const char *buffer = buf;
        char *all = new char[strlen(array) + strlen(buffer) + 1];
        strcpy(all, array);
        strcat(all, buffer);

        cout <<"del: la orden a ejecutar es: "<< all << endl; //del
        system(all);
    }
    else
    {
        cout << "del: PROCESO PADRE" << endl; //del
        string palabra;
        cout << "Ingresa una palabra: " << endl;
        cin >> palabra;                          /* Padre */

        cout << "del: la palabra que has escrito es: " << palabra << endl; 
        const char* msg = palabra.c_str();
        close(tub[0]);                           // Cierro la lectura
        write(tub[1], msg, strlen(msg)); // Escribe en pipe
        close(tub[1]);                           // Cierra la escritura1
    }
}

int main(int argc, char const *argv[])
{
    int a =0;
    while (true)
    {
        cout << "del: RONDA " <<a<< endl;
        a++;
        mayusculas();
    }
}
// es molt raro perque realmente los procesos siempre empiezan por el hijo pero en la actividad pride primero el padre
