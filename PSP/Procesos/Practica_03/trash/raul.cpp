#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

class Juego {
public:
    void jugar() {
        int puntajeUsuario = 0;
        int puntajeMaquina = 0;

        std::string opciones[] = {"Piedra", "Papel", "Tijeras"};
        int numRonda = 1;

        int pipe_fd[2];
        if (pipe(pipe_fd) == -1) {
            std::cerr << "Error al crear el pipe." << std::endl;
            exit(1);
        }

        while (puntajeUsuario < 3 && puntajeMaquina < 3) {
            std::cout << "Ronda " << numRonda << ":" << std::endl;
            std::string eleccionUsuario;
            
            std::cout << "> ";
            std::cin >> eleccionUsuario;
            
            pid_t pid = fork();
            if (pid == -1) {
                std::cerr << "Error al crear el proceso hijo." << std::endl;
                exit(1);
            } else if (pid == 0) {
                close(pipe_fd[0]);
                std::string eleccionMaquina = generarEleccionAleatoria(opciones);
                write(pipe_fd[1], eleccionMaquina.c_str(), eleccionMaquina.size());
                close(pipe_fd[1]);
                exit(0);
            } else {
                close(pipe_fd[1]);
                char buffer[255];
                read(pipe_fd[0], buffer, sizeof(buffer));
                close(pipe_fd[0]);
                std::string eleccionMaquina(buffer);
                
                std::string resultado = determinarGanador(eleccionUsuario, eleccionMaquina);
                if (resultado == "Usuario") {
                    puntajeUsuario++;
                } else if (resultado == "Maquina") {
                    puntajeMaquina++;
                }

                std::cout << eleccionMaquina << std::endl;
                std::cout << "Ronda " << numRonda << " gana " << resultado << std::endl;
                numRonda++;
                wait(nullptr);
            }
        }

        std::cout << "Final de la partida, ganador ";
        if (puntajeUsuario > puntajeMaquina) {
            std::cout << "USUARIO (" << puntajeUsuario << " - " << puntajeMaquina << ")";
        } else if (puntajeMaquina > puntajeUsuario) {
            std::cout << "MAQUINA (" << puntajeMaquina << " - " << puntajeUsuario << ")";
        } else {
            std::cout << "EMPATE (" << puntajeUsuario << " - " << puntajeMaquina << ")";
        }
        std::cout << std::endl;
    }

private:
    std::string generarEleccionAleatoria(std::string opciones[]) {
        int indiceAleatorio = rand() % 3;
        return opciones[indiceAleatorio];
    }

    std::string determinarGanador(const std::string& usuario, const std::string& maquina) {
        if (usuario == maquina) {
            return "Empate";
        } else if (
            (usuario == "Piedra" && maquina == "Tijeras") ||
            (usuario == "Papel" && maquina == "Piedra") ||
            (usuario == "Tijeras" && maquina == "Papel")
        ) {
            return "Usuario";
        } else {
            return "Maquina";
        }   
    }
};

int main() {
    srand(static_cast<unsigned>(time(nullptr))); 
    Juego juego;
    juego.jugar();
    return 0;
}