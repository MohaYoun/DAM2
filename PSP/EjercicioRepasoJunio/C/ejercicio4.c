#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdbool.h> // Booleanos
#define TIEMPO 2
#define TIEMPO2 5

#define MAX_EVENTS 10
#define MIN_DELAY 1
#define MAX_DELAY 5


void sa_handler1(int signo)
{
    int nAleat;
    //nAleat = rand() % 10;
    if (signo == SIGUSR1)
    {
        printf("Evento detectado por pid: %d. \n", getpid());
        // if ((nAleat > 0) && (nAleat < 3))
        // {
        //     printf("Soy el Hijo %d y la temperatura de hoy ed de 22º. \n", getpid());
        // }else if ((nAleat > 3) && (nAleat < 6))
        // {
        //     printf("Soy el Hijo %d y la presion del examen es alta. \n", getpid());
        // }else if ((nAleat > 6) && (nAleat < 10)){
        //     printf("Soy el Hijo %d y no se que poner. NumAl:%d \n", getpid(), nAleat);
        // }
    }else if (signo == SIGUSR2)
    {
        printf("Soy el Hijo %d y he sido eliminado. \n", getpid());
        exit(0);
    }
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Uso: %s <n>\n", argv[0]);
        return 1;
    }
    srand(time(NULL));
    int nProc = atoi(argv[1]);
    signal(SIGUSR1, sa_handler1);
    signal(SIGUSR2, sa_handler1);   

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
            // Hijo
            sleep(TIEMPO);
            kill(getpid(), SIGUSR1);
            //signal(SIGUSR1, sa_handler1);
            sleep(TIEMPO2);

            //printf("hijo: %d, envía señal SIGUSR1 al padre.\n", getpid());
            kill(getpid(), SIGUSR2);

            exit(EXIT_SUCCESS);
        }
        else
        {
            // Padre
            // signal(SIGUSR1, sa_handler1);
            // signal(SIGUSR2, sa_handler1);            

            // Esperar a que los procesos hijos terminen
            for (int i = 0; i < nProc; i++)
            {
                waitpid(pid, NULL, 0);
            }
        }
    }    
    return 0;
}




//####################################################################################
// // Función manejadora de señales para el proceso padre
// void manejador_senial_padre(int signum) {
//     if (signum == SIGUSR1) {
//         printf("Evento detectado por el proceso padre.\n");
//     }
// }

// // Función manejadora de señales para los procesos hijos
// void manejador_senial_hijo(int signum) {
//     if (signum == SIGUSR1) {
//         printf("Evento detectado por el proceso hijo con PID %d.\n", getpid());
//         exit(EXIT_SUCCESS);
//     }
// }

// int main(int argc, char *argv[]) {
//     if (argc != 2) {
//         fprintf(stderr, "Uso: %s <n>\n", argv[0]);
//         return 1;
//     }

//     int num_children = atoi(argv[1]);

//     // Establecer el manejador de señales para el proceso padre
//     signal(SIGUSR1, manejador_senial_padre);

//     // Crear procesos hijos
//     for (int i = 0; i < num_children; i++) {
//         pid_t pid = fork();

//         if (pid < 0) {
//             fprintf(stderr, "Error al crear el proceso hijo.\n");
//             return 1;
//         } else if (pid == 0) {
//             // Proceso hijo
//             // Establecer el manejador de señales para el proceso hijo
//             signal(SIGUSR1, manejador_senial_hijo);

//             // Generar un evento aleatorio después de un retraso aleatorio
//             srand(time(NULL) ^ getpid());
//             int delay = rand() % (MAX_DELAY - MIN_DELAY + 1) + MIN_DELAY;
//             sleep(delay);
//             kill(getppid(), SIGUSR1);

//             // Terminar el proceso hijo después de enviar la señal
//             exit(EXIT_SUCCESS);
//         }
//     }

//     // Esperar a que todos los procesos hijos terminen
//     for (int i = 0; i < num_children; i++) {
//         wait(NULL);
//     }

//     return 0;
// }

//###########################################################################3
// void sa_handler1(int signo)
// {
//     if (signo == SIGUSR1)
//     {
//         printf("Evento detectado por PID %d.\n", getpid());
//     }
//     else if (signo == SIGUSR2)
//     {
//         printf("Soy el Hijo %d y he sido eliminado. \n", getpid());
//         exit(0);
//     }
// }

// int main(int argc, char *argv[])
// {
//     if (argc != 2)
//     {
//         fprintf(stderr, "Uso: %s <n>\n", argv[0]);
//         return 1;
//     }

//     srand(time(NULL));
//     int nProc = atoi(argv[1]);
//     pid_t hijos[nProc];

//     for (int i = 0; i < nProc; i++)
//     {
//         pid_t pid = fork();

//         if (pid < 0)
//         {
//             fprintf(stderr, "Error al crear el proceso hijo\n");
//             return 1;
//         }

//         if (pid == 0)
//         {
//             hijos[i] = pid;
//             // Hijo
//             signal(SIGUSR1, sa_handler1);
//             signal(SIGUSR2, sa_handler1);

//             sleep(TIEMPO);

//             // Envía la señal SIGUSR1 al proceso padre
//             kill(getppid(), SIGUSR1);

//             sleep(TIEMPO2);

//             // Envía la señal SIGUSR2 al proceso padre
//             kill(getppid(), SIGUSR2);

//             // Termina naturalmente en lugar de usar exit()
//             exit(EXIT_SUCCESS);
//         }
//         else
//         {
//             // Padre
//             signal(SIGUSR1, sa_handler1);
//             signal(SIGUSR2, sa_handler1);

//             // Esperar a que todos los procesos hijos terminen
//             for (int i = 0; i < nProc; i++)
//             {
//                 waitpid(hijos[i], NULL, 0);
//             }
//             return 0;
//         }
        
//     }
// }
//###################################################################