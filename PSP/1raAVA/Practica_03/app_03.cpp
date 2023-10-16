/*
Práctica 1. Escribe una clase llamada Ejecuta que reciba como argumentos el comando
y las opciones del comando que se quiere ejecutar.
El programa debe crear un proceso hijo que ejecute el comando con las opciones
correspondientes mostrando un mensaje de error en el caso de que no se realizase
correctamente la ejecución. El padre debe esperar a que el hijo termine e informar si se
produjo alguna anomalía en la ejecución del hijo.
*/

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
using namespace std;
class Ejecuta
{
public:
    void ejecuta(char comand[])
    {
        if ( system(comand) == -1)
        {
            cout << "Error: Ha havido un error en la ejecución";
        }
    }
};
int main()
{
    char comand[100];
    pid_t pid;
    cout << "Dime el comando:";
    cin >> comand;
    Ejecuta ej;
    pid = fork();
    switch (pid)
    {
    case -1:
        cout << "Error: Ha havido un error en la ejecución";
        break;
    case 0:
        ej.ejecuta(comand);
        break;
    default:
        wait(NULL);
        cout << "Hola soy el padre";
        break;
    }
    return 0;
}

