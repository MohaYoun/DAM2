#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdbool.h>  // Booleanos
#include <sys/wait.h> // Necesario para waitpid

#define MAXNUMGENER 20

#define READ 0
#define WRITE 1
/*2. Crea un proceso que cree dos procesos hijos, luego generará N (20) números aleatorios. Enviará los pares al primer hijo,
 los impares al segundo. Los hijos escribirán por pantalla "Soy el hijo 1|2, he recibido ". Por cada número que mande el padre.
*/
int main()
{
    // Hay que utilizar una semilla para generar numeros aleatorios cada vez que se ejecuta el programa, esta utiliza la hora actual
    srand(time(NULL));
    int pipe1[2];
    int pipe2[2];
    pid_t pid1, pid2;
    int numero;

    // Crear un pipe
    if (pipe(pipe1) == -1 || pipe(pipe2) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }
    // Crear el primer proceso hijo
    pid1 = fork();

    if (pid1 < 0)
    {
        perror("Error al crear el primer proceso hijo");
        return 1;
    }
    else if (pid1 == 0)
    {
        close(pipe2[WRITE]);
        close(pipe2[READ]);
        close(pipe1[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
        int numero;
        while (read(pipe1[READ], &numero, sizeof(numero)) > 0)
        {
            printf("Soy el hijo 1 he recibido el numero: %d\n", numero);
        }
        close(pipe1[READ]); // Cerrar el descriptor de lectura después de leer
        exit(0);
    }
    // El proceso padre continúa aquí
    pid2 = fork(); // Crear el segundo proceso hijo
    if (pid2 < 0)
    {
        perror("Error al crear el segundo proceso hijo");
        return 1;
    }
    else if (pid2 == 0)
    {
        close(pipe1[READ]);
        close(pipe1[WRITE]);
        close(pipe2[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
        int numero;
        while (read(pipe2[READ], &numero, sizeof(numero)) > 0)
        {
            printf("Soy el hijo 2 he recibido el numero: %d\n", numero);
        }
        close(pipe2[READ]); // Cerrar el descriptor de lectura después de leer
        exit(0);
    }
    for (int i = 1; i <= MAXNUMGENER; i++)
    {
        numero = rand() % 100;
        if (numero % 2 == 0)
        {
            close(pipe1[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura
            // Escribimos los números en el pipe
            write(pipe1[WRITE], &numero, sizeof(numero));
        }
        else
        {
            close(pipe2[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura
            // Escribimos los números en el pipe
            write(pipe2[WRITE], &numero, sizeof(numero));
        }
    }

    close(pipe1[WRITE]); // Cerrar el descriptor de escritura después de escribir
    close(pipe1[READ]);
    close(pipe2[WRITE]); // Cerrar el descriptor de escritura después de escribir
    close(pipe2[READ]);
    // Esperar a que los procesos hijos terminen
    // waitpid(pid1, pid2);
    // wait(NULL);
    // wait(NULL);
    waitpid(pid1, NULL, 0);
    waitpid(pid2, NULL, 0);
    return 0;
}
// ##########################################################################################################################################################
int main()
{
    // Hay que utilizar una semilla para generar numeros aleatorios cada vez que se ejecuta el programa, esta utiliza la hora actual
    srand(time(NULL));
    int pipe1[2];
    int pipe2[2];
    int pipe3[2];
    int pipe4[2];
    pid_t pid1, pid2;
    int numero;

    // Crear un pipe
    if (pipe(pipe1) == -1 || pipe(pipe2) == -1 || pipe(pipe3) == -1 || pipe(pipe4) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }
    // Crear el primer proceso hijo
    pid1 = fork();

    if (pid1 < 0)
    {
        perror("Error al crear el primer proceso hijo");
        return 1;
    }
    else if (pid1 == 0)
    {
        close(pipe4[WRITE]);
        close(pipe4[READ]);
        close(pipe2[WRITE]);
        close(pipe2[READ]);
        close(pipe3[READ]);
        close(pipe1[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
        int numero;
        int sumPar;
        while (read(pipe1[READ], &numero, sizeof(numero)) > 0)
        {
            printf("Soy el hijo 1 he recibido el numero: %d, que par.\n", numero);
            sumPar += numero;
        }
        write(pipe3[WRITE], &sumPar, sizeof(sumPar));

        close(pipe1[READ]); // Cerrar el descriptor de lectura después de leer
        close(pipe3[WRITE]);

        exit(0);
    }
    // El proceso padre continúa aquí
    pid2 = fork(); // Crear el segundo proceso hijo
    if (pid2 < 0)
    {
        perror("Error al crear el segundo proceso hijo");
        return 1;
    }
    else if (pid2 == 0)
    {
        close(pipe3[WRITE]);
        close(pipe3[READ]);
        close(pipe1[WRITE]);
        close(pipe1[READ]);
        close(pipe4[READ]);
        close(pipe2[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
        int numero;
        int sumImpar;
        while (read(pipe2[READ], &numero, sizeof(numero)) > 0)
        {
            printf("Soy el hijo 2 he recibido el numero: %d, que es impar\n", numero);
            sumImpar += numero;
        }
        write(pipe4[WRITE], &sumImpar, sizeof(sumImpar));
        close(pipe2[READ]); // Cerrar el descriptor de lectura después de leer
        close(pipe4[WRITE]);
        exit(0);
    }

    for (int i = 1; i <= MAXNUMGENER; i++)
    {
        numero = rand() % 100;
        if (numero % 2 == 0)
        {
            close(pipe1[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura
            // Escribimos los números en el pipe
            write(pipe1[WRITE], &numero, sizeof(numero));
        }
        else
        {
            close(pipe2[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura
            // Escribimos los números en el pipe
            write(pipe2[WRITE], &numero, sizeof(numero));
        }
    }

    close(pipe2[WRITE]);
    close(pipe2[READ]);
    close(pipe1[WRITE]);
    close(pipe1[READ]);
    // close(pipe1[WRITE]);
    // close(pipe2[WRITE]);

    int sumPares, sumImpares;

    close(pipe3[WRITE]);
    read(pipe3[READ], &sumPares, sizeof(sumPares));
    close(pipe3[READ]);
    close(pipe4[WRITE]);
    read(pipe4[READ], &sumImpares, sizeof(sumImpares));
    close(pipe4[READ]);

    printf("Suma del numero par: %d\n", sumPares);
    printf("Suma del numero Impar: %d\n", sumImpares);
    // Esperar a que los procesos hijos terminen
    // waitpid(pid1, pid2);
    // wait(NULL);
    // wait(NULL);
    waitpid(pid1, NULL, 0);
    waitpid(pid2, NULL, 0);
    return 0;
}