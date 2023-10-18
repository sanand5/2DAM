#include <iostream>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <errno.h>
using namespace std;
int main(int argc, char const *argv[])
{
    pid_t pid;
    int tub[2];
    char buf[1024];
    pipe(tub);
    pid = fork();

    if (pid < 0)
    { /* Error */
        cout << "Err: fallo en el fork()"
    }
    else if (pid == 0)
    {                                       /* Hijo */
        close(tub[0]);                       // Cierro la lectura
        write(tub[1], array, strlen(array)); // Escribe en pipe
        close(tub[1]);                       // Cierra la escritura
    }
    else
    { /* Padre */
    }

    return 0;
}
