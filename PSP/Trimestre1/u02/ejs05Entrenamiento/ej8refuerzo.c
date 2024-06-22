#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <time.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
    // El comando "ip" con argumentos para obtener información sobre las direcciones IP
    char *comando = "ip";
    char *argumentos[] = {"ip", "addr", NULL};

    // Ejecutar el comando "ip" con los argumentos dados
    execvp(comando, argumentos);

    // Si execvp falla, se ejecutará esta parte del código
    perror("execvp");
    return 1;
}