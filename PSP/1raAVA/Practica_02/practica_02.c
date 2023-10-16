#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main() {
    int fd[2];
    int retorno = 0;
    pid_t pid;
    char mensajeHijo[] = "Papa ven a por mi";
    char buffer[17];

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
            close(fd[0]); //Cierro la lectura
            write(fd[1], mensajeHijo, strlen(mensajeHijo)); // Escribe en pipe
            close(fd[1]); //Cierra la escriture
            printf("El hijo envía el mensaje al padre...\n");
            break;
        default:
            // Padre
            wait(NULL);
            close(fd[1]); 
            read(fd[0], buffer, sizeof(buffer));
            close(fd[0]); 
            printf("El padre recibe el mensaje del hijo: %s\n", buffer);
            break;
    }

    return retorno;
}
