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
    int jugar();
    
private:
    int esGanador(const string &usuario, const string &maquina);
};

int Juego::jugar()
{
    
    pid_t pid;
    int fd[2];
    const char *user;
    string opciones[] = {"piedra", "papel", "tijeras"};//clase
    // fd[0] = lectura, fd[1] = escritura

    char buf[1024] = "";
    if (pipe(fd) == -1)
    {
        cout << "Error: fallo en el pipe" << endl;
    }
    pid = fork();
    if (pid == -1)
    {
        cout << "Error: fallo en el fork()" << endl;
    }
    else if (pid == 0) /* Hijo */
    {
        close(fd[0]);
        int retur = system("./opcion") / 256;
        const char *retorno = opciones[retur].c_str();
        
        write(fd[1], retorno, strlen(retorno));
        close(fd[1]);
        // 1=256, 2=512
    }
    else /* Padre */
    {
        close(fd[1]);
        cout << ">";
        string usuario;
        cin >> usuario;
        read(fd[0], buf, sizeof(buf));
        
        close(fd[0]);
    return esGanador(user, buf);
    }
}
int Juego::esGanador(const string &usuario, const string &maquina)
{
    int ret;
    if ((usuario == "piedra" && maquina == "tijeras") ||
        (usuario == "papel" && maquina == "piedra") ||
        (usuario == "tijeras" && maquina == "papel"))
    {
        ret = 0;
    }
    else if (usuario == maquina)
    {
        ret = 1;
    }
    else
        ret = 2;

    return ret;
}

int main(int argc, char const *argv[])
{
    Juego ej;
    int pUser = 0;
    int pCPU = 0;

    for (int i = 0; i < 3; i++)
    {
        int res = ej.jugar();
        if (res == 0)
        {
            cout << "Ha ganado el usuario" << endl;
            pUser++;
        }
        else if (res = 2)
        {
            cout << "Ha ganado la maquina" << endl;
            pCPU++;
        }
        else
            cout << "Empate" << endl;
    }

    if (pUser > pCPU)
    {
        cout << "Ha ganado el juego el usuario" << endl;
    }
    else if (pUser == pCPU)
    {
        cout << "Ha ganado el juego la maquina" << endl;
    }
    else
    {
        cout << "El juego ha quedado en empate" << endl;
    }

    return 0;
}