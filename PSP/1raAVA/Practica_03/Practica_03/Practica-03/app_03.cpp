#include <iostream>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
using namespace std;

class Mayusculas{
    public:
    void mayusculas();
};
void Mayusculas::mayusculas()
{
    pid_t pid;
    int tub[2];
    char buf[1024]="";
    pipe(tub);
    pid = fork();
    if (pid < 0)
    {
        cout << "Error: fallo en el fork()" << endl;
    }
    else if (pid == 0)
    {
        read(tub[0], buf, sizeof(buf));
        close(tub[0]);
        const char *array = "./mayusculas ";
        const char *buffer = buf;
        char *all = new char[strlen(array) + strlen(buffer) + 1];
        strcpy(all, array);
        strcat(all, buffer);
        system(all);
    }
    else
    {
        string palabra;
        cin >> palabra;
        const char *msg = palabra.c_str();
        close(tub[0]);
        write(tub[1], msg, strlen(msg));
        close(tub[1]);
    }
}

int main(int argc, char const *argv[])
{
    Mayusculas m;
    while (true)
    {
        m.mayusculas();
    }
}