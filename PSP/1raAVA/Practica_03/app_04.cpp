#include <iostream>
#include <string>
#include <cstdlib> // Para rand y srand
#include <ctime>   // Para time
#include <unistd.h>
#include <sys/types.h> // Para fork y pipe
#include <sys/wait.h>  //ERROR


using namespace std;
class Juego
{
    string opciones[] = {"piedra", "papel", "tijeras"}; //incomplete type is not allowed, an initializer cannot be specified for a flexible array member
    char buffer[7];
    int fd[2];
    int rondasUsuario = 0;
    int rondasMaquina = 0;
    int ronda = 0;

public:
    void iniciarJuego()
    {

        pid_t pid;
        int retorno = 0;

        if (pipe(fd) == -1) //identifier "pipe" is undefined
        {
            printf("### Error : No se ha podido crear la tuberia ###");
            retorno = -1;
        }
        for (int ronda = 1; ronda <= 3; ronda++) {
        pid = fork(); //identifier "fork" is undefined
        switch (pid)
        {
        case -1:
            cout << "### Error : No se ha podido crear el hijo ###";
            retorno = -1;
            break;
        case 0:
            /* code */
            break;
        default:
            wait(NULL); //identifier "wait" is undefined
            break;
        }}
        mostrarResultadoFinal(rondasUsuario, rondasMaquina);
    }

private:
    void hijo()
    {
        char opcion[] = opciones[3].c_str(); //TODO
        close(fd[0]);                         // Cierro la lectura
        write(fd[1], opcion, strlen(opcion)); // Escribe en pipe
        close(fd[1]);                         // Cierra la escriture
        printf("El hijo envía el mensaje al padre...\n");
    }
    void padre()
    {
        cout << "Ronda " << ronda << ":" << endl;
        close(fd[1]);
        read(fd[0], buffer, sizeof(buffer));
        string usuario = obtenerOpcionUsuario();
        string maquina = buffer;
        cout << "Máquina: " << maquina << endl;

        if (usuario == maquina)
        {
            cout << "Ronda " << ronda << " empate" << endl;
        }
        else if (esGanador(usuario, maquina))
        {
            cout << "Ronda " << ronda << " gana usuario" << endl;
            rondasUsuario++;
        }
        else
        {
            cout << "Ronda " << ronda << " gana máquina" << endl;
            rondasMaquina++;
        }


        close(fd[0]);
    }
    string obtenerOpcionUsuario()
    {
        string opcion;
        while (true)
        {
            cout << "Escribe piedra, papel o tijeras: ";
            cin >> opcion;
            if (opcion == "piedra" || opcion == "papel" || opcion == "tijeras")
            { // mathes
                return opcion;
            }
            else
            {
                cout << "Opción no válida. Inténtalo de nuevo." << endl;
            }
        }
    }
    bool esGanador(const string &usuario, const string &maquina)
    {
        return (usuario == "piedra" && maquina == "tijeras") ||
               (usuario == "papel" && maquina == "piedra") ||
               (usuario == "tijeras" && maquina == "papel");
    }
    void mostrarResultadoFinal(int rondasUsuario, int rondasMaquina) {
        cout << "Final de la partida" << endl;
        if (rondasUsuario > rondasMaquina) {
            cout << "Ganador: USUARIO (" << rondasUsuario << " - " << rondasMaquina << ")" << endl;
        } else if (rondasMaquina > rondasUsuario) {
            cout << "Ganador: MÁQUINA (" << rondasMaquina << " - " << rondasUsuario << ")" << endl;
        } else {
            cout << "Empate (" << rondasUsuario << " - " << rondasMaquina << ")" << endl;
        }
    }

};

int main()
{
    Juego juego;
    juego.iniciarJuego();
    return 0;
}