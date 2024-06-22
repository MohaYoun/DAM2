#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#define BUFFER 100

#define READ 0
#define WRITE 1

int main()
{
    int fd[2];
    pid_t pid;

    if(pipe(fd) == -1)
    {
        perror("Error al crear pipe.");
        exit(EXIT_FAILURE);
    }

    pid = fork();
    if(pid == -1)
    {
        perror("Error al clonar");
        exit(EXIT_FAILURE);
    }

    if(pid == 0)
    {
        char cadenaDelPadre[BUFFER];
        close(fd[WRITE]);

        read(fd[READ], cadenaDelPadre, BUFFER);
        close(fd[READ]);

        printf("Proceso hijo recibio la cadena %s\n", cadenaDelPadre);
        exit(EXIT_SUCCESS);
    }else{
        char cadenaAlHijo[] = "Prueba envio al hijo";
        close(fd[READ]);


        read(fd[WRITE], cadenaAlHijo, BUFFER);
        close(fd[WRITE]);

        wait(NULL);
        printf("El padre ya ha terminado de escribir\n");
    }
    return 0;
}