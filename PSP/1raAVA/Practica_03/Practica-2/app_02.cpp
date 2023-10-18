#include <iostream>
#include <cstring>
#include <regex>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

using namespace std;

class Juego
{
public:
    void jugar();

private:
    string rojo = "\033[1;31m", normal = "\033[0m";
    pid_t pid;
    int fd[2], retorno = 0, pUser = 0, pCPU = 0, ran;
    char buffer[7];
    string opciones[3], opcion;
    void hijo();
    void padre();
    bool esGanador(const string &usuario, const string &maquina);
};

void Juego::jugar()
{
    opciones[0] = "piedra";
    opciones[1] = "papel";
    opciones[2] = "tijeras";
    if (pipe(fd) == -1)
    {
        cout << rojo << "Error : No se ha podido crear la tuberia" << normal << endl;
        retorno = -1;
    }
    for (int i = 0; i < 3; i++)
    {
        cout << "Ronda " << i+1 << ":" << endl;
        pid = fork();

        if (pid == -1)
        {
            cout << rojo << "Error : No se ha podido crear el hijo" << normal << endl;
        }
        if (pid == 0)
        {
            hijo();
        }
        if (pid != 0 && pid != -1)
        {
            wait(NULL);
            padre();
        }
    }
    cout << "El ganador es " << ((pUser > pCPU) ? "el usuario" : "la maquina") << endl;
}
void Juego::hijo()
{
    ran = rand() % 3;
    opcion = opciones[ran];
    char mensajeHijo[sizeof(opcion)];
    strcpy(mensajeHijo, opcion.c_str());
    close(fd[0]);                                   // Cierro la lectura
    write(fd[1], mensajeHijo, strlen(mensajeHijo)); // Escribe en pipe
    close(fd[1]);                                   // Cierra la escritura

    // Eliminar{
    cout << rojo << "El hijo le ha dejado este mensaje al padre: ";
    for (int i = 0; i < strlen(mensajeHijo); i++)
    {
        cout << mensajeHijo[i];
    }
    cout << normal << endl;
}
void Juego::padre()
{

    close(fd[1]);
    read(fd[0], buffer, sizeof(buffer));
    close(fd[0]);
    bool repit;
    string userOpt; // user option string
    while (repit)
    {

        cout << "piedra / papel / tijeras" << endl
             << "?";
        cin >> userOpt;
        regex pattern("(piedra|papel|tijeras)");
        if (regex_match(userOpt, pattern))
        {
            repit = false;
        }
        else
        {
            repit = true;
            cout << "Opción no válida. Inténtalo de nuevo." << endl;
        }
    }
    cout << "Maquina: " << buffer << endl;
    if (esGanador(userOpt, buffer))
    {
        pUser++;
    }
    else
    {
        pCPU++;
    }
}
bool Juego::esGanador(const string &usuario, const string &maquina) // PADRE
{
    return (usuario == "piedra" && maquina == "tijeras") ||
           (usuario == "papel" && maquina == "piedra") ||
           (usuario == "tijeras" && maquina == "papel");
}

int main()
{
    // No entiendo poque si declaro la variable de ran dentro de el swich en la opcion 0 me da error: jump to case label     default:
    Juego game;
    game.jugar();
    return 0;
}
