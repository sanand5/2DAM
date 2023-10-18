#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <errno.h>
int main(int argc, char const *argv[])
{
    pid_t pid;
    int tub[2];
    char buf[1024];
    pipe(tub);
    pid = fork();

    if (pid < 0)
    { /* Error */
        fprintf(stderr, "Fallo en el fork().\n%s\n", strerror(errno));
    }
    else if (pid == 0)
    { /* Hijo */
    }
    else
    { /* Padre */
    }

    return 0;
}
