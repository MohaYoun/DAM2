#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdbool.h> // Booleanos
#define TIEMPO 2
#define TIEMPO2 40

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Uso: %s <n>\n", argv[0]);
        return 1;
    }
    pid_t dameHijo;
    int nProc = atoi(argv[1]); // Número de hijos a crear
    pid_t num[nProc];

    for (int i = 0; i < nProc; i++)
    {
        pid_t pid = fork();

        if (pid < 0)
        {
            fprintf(stderr, "Error al crear el proceso hijo\n");
            return 1;
        }
        if (pid == 0)
        {
            dameHijo = getpid();
            // Código para cada proceso hijo
            // printf("Soy el proceso hijo con ID: %d\n", getpid());
            printf("Soy el proceso hijo con ID: %d\n", dameHijo);
            while (true)
            {
                // El hijo espera la señal indefinidamente
                sleep(TIEMPO);
            }
        }
        else
        {
            num[i] = pid;
        }
    }

    sleep(TIEMPO2); // Espera un momento antes de enviar la señal a los hijos

    for (int i = 0; i < nProc; i++)
    {
        printf("Hijo muerto: %d\n", num[i]);
        kill(num[i], SIGKILL);
    }
    for (int i = 0; i < nProc; i++)
    {
        wait(NULL);
    }
    // for (int i = 0; i < n; i++)
    // {
    //     kill(0, SIGTERM); // Envía la señal SIGTERM a todos los procesos en el grupo de procesos del proceso actual
    // }

    // for (int i = 0; i < n; i++)
    // {
    //     wait(NULL); // Espera a que todos los hijos terminen
    // }

    fprintf(stdout, "Todos los procesos hijos han sido terminados.\n");

    return 0;
}
