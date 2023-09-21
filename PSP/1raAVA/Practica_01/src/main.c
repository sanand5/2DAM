#include <unistd.h>
#include <stdio.h>

int main() {
    pid_t pid;
    int variable = 6;
    printf("Valor inicial de la variable: %d\n", variable);
    
    pid = fork();
    if (pid == 0) //Hijo
    {
        variable-=5;
        printf("Valor en proceso hijo: %d\n", variable);
    }else if (pid == -1)  //Error
    {
        printf("***Error en el proceso");    
    }else { //Padre
        wait(NULL);
        variable+=5;
        printf("Valor en proceso padre: %d\n", variable);
    }
}