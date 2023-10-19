
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
void func()
{
    pid_t pid;
    int tub[2];
    char buf[10];
    pipe(tub);
    char palabra[10];
    pid = fork();
    if (pid < 0)
    { /* Error */
        cout << "Error: fallo en el fork()" << endl;
    }
    else if (pid == 0)
    { /* Hijo */
        // cout << "Hijo" << endl;
        //write(tub[1], " ", strlen(" ")); // Escribe en pipe
        close(tub[1]);                  // Cierra la escritu
        read(tub[0], buf, sizeof(buf)); // lee el pipe
        close(tub[0]);                  // Cierro la lectura
        string modified = buf;
        for (char &c : modified)
        {
            c = toupper(c);
        }
        cout << "Palabra convertida a mayúsculas: " << modified << endl;
        // write(tub[1], modified.c_str(), modified.length()); // escribe en el pipe
        // close(tub[1]);                           // Cierra la escritu
    }
    else
    {
        // wait(NULL);
        cout << "Ingresa una palabra: " << endl;
        cin >> palabra;                          /* Padre */
        close(tub[0]);                           // Cierro la lectura
        write(tub[1], palabra, strlen(palabra)); // Escribe en pipe
        close(tub[1]);                           // Cierra la escritura1
    }
}

int main(int argc, char const *argv[])
{
    while (true)
    {
        func();
    }
}
// es molt raro perque realmente los procesos siempre empiezan por el hijo pero en la actividad pride primero el padre
