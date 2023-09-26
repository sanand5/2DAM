#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main() {
    int fd[2];
    int retorno = 0;
    pid_t pid;
    char mensajeHijo[] = "Papa ven a por mi";
    char buffer[20];

    // Crear el pipe
    
    if (pipe(fd) == -1)
    {
        printf("### Error : No se ha podido crear la tuberia ###");
        retorno = -1;
    }
    
    pid = fork();
    switch (pid) {
        case -1: // ERROR
            printf("### Error : No se ha podido crear el hijo ###");
            retorno = -1;
            break;
        case 0:
            // Hijo
            close(fd[0]); // Cierra el descriptor de entrada/lectura
            write(fd[1], mensajeHijo, strlen(mensajeHijo)); // Escribe en pipe
            printf("El hijo envía el mensaje al padre...\n");
            break;
        default:
            // Padre
            close(fd[1]); // Cierra el descriptor de salida/escritura
            read(fd[0], buffer, sizeof(buffer));
            printf("El padre recibe el mensaje del HIJO: %s\n", buffer);
            wait(NULL);
            break;
    }

    return retorno;
}
