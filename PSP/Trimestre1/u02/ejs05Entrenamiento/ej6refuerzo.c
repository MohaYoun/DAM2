#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <time.h>
#include <sys/types.h>
#include <sys/wait.h>

#define BUFFER 20

#define READ 0
#define WRITE 1

int main(int argc, char *argv[]) {
    int fd[2];
    pid_t pid;

    if (argc != 4) {
        fprintf(stderr, "Uso: %s <num1> <operador> <num2>\n", argv[0]);
        return 1;
    }
     if (pipe(fd) == -1)
    {
        perror("Error al hacer el pipe");
        return 1;
    }
    pid = fork();
    if (pid < 0)
    {
        perror("Error al crear el fork");
        return 1;
    }
    if (pid != 0)// Código proceso Padre
    {
        close(fd[READ]);// Cerrar el extremo de lectura del pipe en el proceso hijo
        int num1 = atoi(argv[1]);
        char operador = argv[2][0];
        int num2 = atoi(argv[3]);
        write(fd[WRITE], &num1, sizeof(num1));
        write(fd[WRITE], &operador, sizeof(operador));
        write(fd[WRITE], &num2, sizeof(num2));// Escribir el resultado en el pipe
        close(fd[WRITE]);// Cerrar el extremo de escritura del pipe en el proceso hijo
    }else{
        // Código para el proceso Hijo
        close(fd[WRITE]);// Cerrar el extremo de escritura del pipe en el proceso padre
        int resultado, n1, n2;
        char opr;
        read(fd[READ], &n1, sizeof(n1));
        read(fd[READ], &opr, sizeof(opr));
        read(fd[READ], &n2, sizeof(n2));
        if(opr == '+'){
            resultado = n1 + n2;
        }else if(opr == '-'){
            resultado = n1 - n2;
        }else{
            perror("El operador no es valido\n");
            return 1;
        }
        // Leer el resultado del pipe
        printf("El resultado es: %d\n", resultado);
        close(fd[READ]);// Cerrar el extremo de lectura del pipe en el proceso padre
    }

}