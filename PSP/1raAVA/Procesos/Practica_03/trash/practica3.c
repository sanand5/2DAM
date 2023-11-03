#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>
int convertirAMayusculas() {
    int pipefd[2];
    pid_t pid;

    if (pipe(pipefd) == -1) {
        printf("Error en la creación de la tubería");
        exit(1);
    }

    pid = fork();

    if (pid == -1) {
        printf("Error al crear el proceso hijo");
        exit(1);
    } else if (pid == 0) {  // Proceso hijo
        close(pipefd[1]);  // Cerrar el extremo de escritura de la tubería

        // Redirigir la entrada estándar al extremo de lectura de la tubería
        dup2(pipefd[0], STDIN_FILENO);
        close(pipefd[0]);

        execlp("tr", "tr", "[:lower:]", "[:upper:]", NULL);
        printf("Error en el proceso hijo");
        exit(1);
    } else {  // Proceso padre
        close(pipefd[0]);  // Cerrar el extremo de lectura de la tubería

        while (1) {
            char entrada[1024];
            fgets(entrada, sizeof(entrada), stdin);

            if (strcmp(entrada, "exit\n") == 0) {
                break;
            }
            write(pipefd[1], entrada, strlen(entrada));

        }

        close(pipefd[1]);
        wait(NULL);
    }
    return 0;
}
// Práctica 1: Clase Ejecuta
int ejecutarComando(const char* comando, const char* opciones) {
    pid_t pid = fork();

    if (pid == -1) {
        printf("Error al crear el proceso hijo");
        exit(1);
    } else if (pid == 0) {  // Proceso hijo
        execlp(comando, comando, opciones, NULL);
        printf("Error al ejecutar el comando");
        exit(1);
    } else {  // Proceso padre
        int status;
        wait(&status);

        if (WIFEXITED(status)) {
            if (WEXITSTATUS(status) == 0) {
                printf("Comando ejecutado exitosamente\n");
            } else {
                printf("Error en la ejecución del comando\n");
            }
        }
    }
    return 0;
}

// Práctica 2: Clase juego

char *getComputerMove() {
    srand(time(NULL));
    int random = rand() % 3;
    if (random == 0) return "Piedra";
    else if (random == 1) return "Papel";
    else return "Tijeras";
}

int jugarPiedraPapelTijeras() {
        char eleccionusuario[20];
        char eleccionmaquina[20];

        int puntosmaquina = 0;
        int puntosusuario = 0;
    int pipefd[2];
    if (pipe(pipefd) == -1) {
        printf("ERROR en la creacion del pipe");
        exit(1);
    }

    pid_t pid = fork();

    if (pid == -1) {
        printf("ERROR en la creacion del hijo");
        exit(1);
    }

    if (pid == 0) {
        // Proceso hijo (ordenador)
        close(pipefd[0]); // Cierra el extremo de lectura del pipe
        char *move;

       
            while (1) {
            move = getComputerMove();
            write(pipefd[1], move, strlen(move) + 1);
            sleep(3);
        }
        
    } else {
        // Proceso padre (usuario)
        close(pipefd[1]); // Cierra el extremo de escritura del pipe


        for (int round = 1; round <= 3; round++) {
            printf("RONDA %d\n", round);
            printf("Usuario:");

            read(pipefd[0], eleccionmaquina, sizeof(eleccionmaquina));

            while (1) {
                fgets(eleccionusuario, sizeof(eleccionusuario), stdin);
                eleccionusuario[strcspn(eleccionusuario, "\n")] = '\0';

                if (strcmp(eleccionusuario, "Piedra") == 0 ||
                    strcmp(eleccionusuario, "Papel") == 0 ||
                    strcmp(eleccionusuario, "Tijeras") == 0) {
                    break;
                }
            }

            printf("Maquina:%s\n", eleccionmaquina);

            if (strcmp(eleccionmaquina, eleccionusuario) == 0) {
                printf("--Empate--\n\n");
                round--;
                
            } else if (
                (strcmp(eleccionmaquina, "Piedra") == 0 && strcmp(eleccionusuario, "Tijeras") == 0) ||
                (strcmp(eleccionmaquina, "Papel") == 0 && strcmp(eleccionusuario, "Piedra") == 0) ||
                (strcmp(eleccionmaquina, "Tijeras") == 0 && strcmp(eleccionusuario, "Papel") == 0)
            ) {
                printf("--Gana computadora--\n\n");
                puntosmaquina++;
            } else {
                printf("--Gana usuario--\n\n");
                puntosusuario++;
            }
        }

        if (puntosmaquina > puntosusuario) {
            printf("Final de la partida, ganador MAQUINA (%d - %d)\n", puntosmaquina, puntosusuario);
        } else {
            printf("Final de la partida, ganador USUARIO (%d - %d)\n", puntosusuario, puntosmaquina);
        } 
        return 0;
    }
}

int main() {
    int opcion;
    printf("Elige una práctica:\n");
    printf("\t1. Ejecutar un comando\n");
    printf("\t2. Jugar Piedra, Papel o Tijeras\n");
    printf("\t3. Mayusculas\n\n");
    printf("Respuesta: ");

    scanf("%d", &opcion);
    printf("\n");

    switch (opcion) {
        case 1:
            char comando[1000];
            char opciones[1000];

            printf("Introduce el comando: ");
            scanf("%s", comando);
            printf("Introduce las opciones: ");
            
            scanf("%s", opciones);

            ejecutarComando(comando, opciones);
            break;
        case 2:
            jugarPiedraPapelTijeras();
            break;
        case 3:
                    printf("Escribe lineas para que se devuelva con mayusculas (exit para salir): ");
            convertirAMayusculas();  
        default:
            break;
    }

    return 0;
}