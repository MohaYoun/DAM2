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
    int fd2[2];
    pid_t pid;

    if (argc != 4) {
        fprintf(stderr, "Uso: %s <num1> <operador> <num2>\n", argv[0]);
        return 1;
    }
    if (pipe(fd) == -1 || pipe(fd2) == -1) {// INICIALIZAMOS LOS PIPES
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
        close(fd2[WRITE]);
        int num1 = atoi(argv[1]);
        char operador = argv[2][0];
        int num2 = atoi(argv[3]);
        int res;
        write(fd[WRITE], &num1, sizeof(num1));
        write(fd[WRITE], &operador, sizeof(operador));
        write(fd[WRITE], &num2, sizeof(num2));// Escribir el resultado en el pipe
        read(fd2[READ], &res, sizeof(res));
        printf("El resultado es: %d\n", res);// Leer el resultado del pipe
        close(fd[WRITE]);// Cerrar el extremo de escritura del pipe en el proceso hijo
        close(fd2[READ]);

    }else{
        // Código para el proceso Hijo
        close(fd[WRITE]);// Cerrar el extremo de escritura del pipe en el proceso padre
        close(fd2[READ]);
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
        write(fd2[WRITE], &resultado, sizeof(resultado));
        close(fd[READ]);// Cerrar el extremo de lectura del pipe en el proceso padre
        close(fd2[WRITE]);
    }
    return 0;

}
 // Redirecciones con dup2()
/*
 int main(int argc, char *argv[]) {
    int fd[2];
    int fd2[2];
    pid_t pid;

    if (argc != 4) {
        fprintf(stderr, "Uso: %s <num1> <operador> <num2>\n", argv[0]);
        return 1;
    }

    if (pipe(fd) == -1 || pipe(fd2) == -1) {
        perror("Error al hacer el pipe");
        return 1;
    }

    pid = fork();
    if (pid < 0) {
        perror("Error al crear el fork");
        return 1;
    }

    if (pid != 0) { // Código proceso Padre
        close(fd[READ]);
        close(fd2[WRITE]);

        int num1 = atoi(argv[1]);
        char operador = argv[2][0];
        int num2 = atoi(argv[3]);
        int res;

        dup2(fd[WRITE], STDOUT_FILENO); // Redirigir la salida estándar al extremo de escritura del primer pipe
        close(fd[WRITE]);

        dup2(fd2[READ], STDIN_FILENO); // Redirigir la entrada estándar al extremo de lectura del segundo pipe
        close(fd2[READ]);

        printf("%d\n%c\n%d\n", num1, operador, num2);

        // Leer el resultado del segundo pipe
        scanf("%d", &res);
        printf("El resultado es: %d\n", res);

    } else {
        // Código para el proceso Hijo
        close(fd[WRITE]);
        close(fd2[READ]);

        int resultado, n1, n2;
        char opr;

        dup2(fd[READ], STDIN_FILENO); // Redirigir la entrada estándar al extremo de lectura del primer pipe
        close(fd[READ]);

        dup2(fd2[WRITE], STDOUT_FILENO); // Redirigir la salida estándar al extremo de escritura del segundo pipe
        close(fd2[WRITE]);

        // Leer datos del primer pipe
        scanf("%d", &n1);
        scanf("%c", &opr); // Leer el operador como un carácter
        scanf("%d", &n2);

        if (opr == '+') {
            resultado = n1 + n2;
        } else if (opr == '-') {
            resultado = n1 - n2;
        } else {
            perror("El operador no es válido\n");
            return 1;
        }

        // Escribir el resultado en el segundo pipe
        printf("%d\n", resultado);
    }

    return 0;
}*/
 