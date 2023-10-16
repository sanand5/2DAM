#include <iostream>
#include <string>
#include <cstdlib> // Para rand y srand
#include <ctime>   // Para time
#include <unistd.h>
using namespace std;

class Juego {
public:
    void iniciarJuego() {
        int rondasUsuario = 0;
        int rondasMaquina = 0;

        for (int ronda = 1; ronda <= 3; ronda++) {
            cout << "Ronda " << ronda << ":" << endl;

            string opcionUsuario = obtenerOpcionUsuario();
            string opcionMaquina = obtenerOpcionMaquina();

            cout << "Máquina: " << opcionMaquina << endl;

            if (opcionUsuario == opcionMaquina) {
                cout << "Ronda " << ronda << " empate" << endl;
            } else if (esGanador(opcionUsuario, opcionMaquina)) {
                cout << "Ronda " << ronda << " gana usuario" << endl;
                rondasUsuario++;
            } else {
                cout << "Ronda " << ronda << " gana máquina" << endl;
                rondasMaquina++;
            }

            cout << endl;
        }

        mostrarResultadoFinal(rondasUsuario, rondasMaquina);
    }

private:
    string obtenerOpcionUsuario() {
        string opcion;
        while (true) {
            cout << "Escribe piedra, papel o tijeras: ";
            cin >> opcion;
            if (opcion == "piedra" || opcion == "papel" || opcion == "tijeras") {//mathes
                return opcion;
            } else {
                cout << "Opción no válida. Inténtalo de nuevo." << endl;
            }
        }
    }

    string obtenerOpcionMaquina() {
        srand(static_cast<unsigned>(time(nullptr)));
        string opciones[] = {"piedra", "papel", "tijeras"};
        return opciones[rand() % 3];
    }

    bool esGanador(const string& usuario, const string& maquina) {
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

int main() {
    Juego juego;
    juego.iniciarJuego();
    return 0;
}