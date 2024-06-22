#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <math.h>
#include <sys/wait.h> // Necesario para waitpid

#define READ 0
#define WRITE 1
/*
Crea un programa que acepte un número como parámetro de línea de comandos. 
El número recibido indicará la cantidad de números aleatorios generados.

El programa creará otro proceso con la llamada al sistema fork. 
El proceso padre generará n números aleatorios y se los enviará al proceso hijo a través de un pipe. 
El padre esperará a que el hijo termine (padre usa wait).

El hijo recibirá los números por el pipe e irá procesando el menor y el mayor. 
Cuando termine de recibir números el hijo escribirá por pantalla el número menor y el número mayor.
*/

int main(int argc, char *argv[])
{
    srand(time(NULL));
    if (argc != 2)
    {
        fprintf(stderr, "Uso: %s <numeros aleatorios>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    int fd[2];

    int n_aleatorios = atoi(argv[1]);

    if (pipe(fd) == -1) {
        perror("Pipe fallido");
        return 1;
    }

    pid_t pid;

    pid = fork();

    if(pid < 0){
        perror("frork fallido");
        return 1;
    }
    
    if (pid == 0)
    {
        // proceso hijo
        close(fd[WRITE]);
        int numeros[n_aleatorios];
        
        read(fd[READ], &numeros, n_aleatorios * sizeof(int));
        for (int i = 0; i < n_aleatorios; i++)
        {
            printf("numero recibido %d \n", numeros[i]);
        }
        close(fd[READ]);
        int mayor, menor;
        for (int i = 0; i < n_aleatorios; i++)
        {
            if (numeros[i] > mayor)
            {
                mayor = numeros[i];
            }
        }
        menor = mayor;
        for (int i = 0; i < n_aleatorios; i++)
        {
            
            if (numeros[i] < menor)
            {
                menor = numeros[i];
            }
        }
        printf("El numero mayor es: %d \n", mayor);
        printf("El numero menor es: %d \n", menor);

    }
    else
    {
        // proceso padre
        close(fd[READ]);
        int numero[n_aleatorios];

        for (int i = 0; i < n_aleatorios; i++)
        {
            numero[i]=rand()%100;
            printf("numero generado %d \n", numero[i]);
        }

        // Manda tantos numeros aleatorios como numeros quiera generar
        write(fd[WRITE], &numero, n_aleatorios * sizeof(int));
        close(fd[WRITE]);
        waitpid(pid, NULL, 0);
    }
}