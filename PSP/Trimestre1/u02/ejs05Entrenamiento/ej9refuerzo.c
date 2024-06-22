#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <time.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
    //Comando equivalente a "ipconfig" en Windows
    char *comando = "ipconfig /all";

    //Ejecutar el comando utilizando la función system
    int resultado = system(comando);

    if (resultado == -1) {
        perror("system");
        return -1;
    }

    return 0;
}