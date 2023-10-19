#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
using namespace std;
class Ejecuta
{
public:
    int ejecutar(char comand[])
    {
        int retorno = system(comand);
        if ( retorno == -1)
        {
            cout << "Error: Ha havido un error en la ejecución";
        }
        return retorno;
    }
};
int main(int argc, char *arg[])
{
    string rojo = "\033[1;31m";
    string normal = "\033[0m";

    if (argc != 2)
    {

        cout << rojo << "Uso: " << arg[0] << " \"<comando>\"" << normal << endl ;
        return 1;
    }
    
    Ejecuta ej;
    pid_t pid = fork();
    int stcomand;
    switch (pid)
    {
    case -1:
        cout << "Error: Ha havido un error en la creacion del proceso";
        break;
    case 0://Hijo
        stcomand = ej.ejecutar(arg[1]);
        break;
    default://Padre
        wait(NULL);
        if (stcomand != -1)
        {
            cout << "Ok: Comando ejecutado correctamente" << endl;
        }else cout << "War: El comando no se ha ejecutado correctamente" << endl;
        break;
    }
    return 0;
}

/*
Duda: Si el comando esta mal escrito ni un sistem = -1 ni un cath salta 
*/