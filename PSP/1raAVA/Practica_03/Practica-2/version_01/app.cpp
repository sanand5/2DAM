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
    int esGanador(const string &usuario, const string &maquina);

private:
};

int Juego::jugar()
{
    // string rojo = "\033[1;31m", normal = "\033[0m";
    bool ret = false;
    pid_t pid;
    int fd[2];
    const char *user;
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
        string array[] = {"piedra", "papel", "tijeras"};
        const char *retorno = array[retur].c_str();
        // cout << "del: cmd = "<<retur << ", char = "<< retorno << endl; //del
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
        user = usuario.c_str();
        read(fd[0], buf, sizeof(buf));
        // cout << "del: La opcion del buffer es: " << buf << ", la opcion del usuario es: " << user << endl; // del
        close(fd[0]);
    }
    return esGanador(user, buf);
}
int Juego::esGanador(const string &usuario, const string &maquina) // PADRE
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
        /* code */
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
/*
Ja se perque me trau sempre pedra perque el buffer es queda en el ultim que es la pedra, si arregle lo faga primer el fill i despres el pare segurament s'arregle asoles lo de que semre me trau pedra.
no enbtiendo lo de la semilla de el random
*/