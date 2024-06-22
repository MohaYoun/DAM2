#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#define BUFFER 1024
/*
Desarrolla un programa en C que cree varios procesos hijo para ejecutar tareas de determinación de primalidad, 
usando el estado de finalización para controlar el éxito o fallo de cada proceso hijo basado en si el número generado es primo o no.

Especificaciones:

El programa debe aceptar un número N como parámetro de línea de comandos, que especificará el número de procesos hijo a crear.
Cada proceso hijo generará un número aleatorio entre 1 y 100 y determinará si este número es primo.
Un proceso hijo que determine que su número es primo terminará con exit(1) para indicar fallo, y si no es primo, con exit(0) para éxito.
Detalles adicionales:

El proceso padre debe esperar a que todos los hijos finalicen y luego debe revisar los códigos de estado de cada 
uno para determinar cuántos procesos determinaron un número primo (fallidos) y cuántos no (exitosos).
El padre debe imprimir cuántos procesos resultaron en números primos y cuántos en números no primos.
Uso del estado de finalización:

El padre analizará el valor devuelto por cada waitpid usando macros como WIFEXITED y WEXITSTATUS para determinar si 
el proceso hijo terminó normalmente y cuál fue su código de salida.
Estructura del programa:

Creación de Procesos: Crea N procesos hijo que cada uno realizará la tarea de determinar la primalidad de un número generado aleatoriamente.
Determinación de Primalidad en los Hijos: Cada hijo genera un número aleatorio, comprueba si es primo, y termina con un código de estado adecuado.
Recopilación de Resultados en el Padre: El padre recoge y analiza los estados de finalización para contar cuántos números eran primos y cuántos no.
Reporte de Resultados: El padre imprime los resultados de cuántos números fueron determinados como primos y cuántos como no primos.
*/
int esPrimo(int num) {
    if (num < 2) {
        return 0; // No es primo
    }
    for (int i = 2; i <= sqrt(num); i++) {
        if (num % i == 0) {
            return 0; // No es primo
        }
    }
    return 1; // Es primo
}

int main(int argc, char *argv[]) {
    if (argc != 2)
    {
        fprintf(stderr, "Uso: %s <n>\n", argv[0]);
        return 1;
    }
    srand(time(NULL));
    int nProc = atoi(argv[1]);
    pid_t pids[BUFFER];  

    for (int i = 0; i < nProc; i++)
    {
        pid_t pid = fork();
        pids[i] = pid;

        if (pid < 0)
        {
            fprintf(stderr, "Error al crear el proceso hijo\n");
            return 1;
        }
        if (pid == 0)
        {
            // Hijo
            srand(time(NULL));
            //int nAleat;
            int nAleat = rand() % 100;
            printf("numero: %d, hijo: %d\n", nAleat, getpid());
            int estado = esPrimo(nAleat);
            sleep(1);
            exit(estado);
        }
        else
        {
            // Padre

            // Esperar a que los procesos hijos terminen
            int estado;
            wait(&estado);
            if (WEXITSTATUS(estado)==1)
            {
                printf("El hijo  %d, ha finalizado y es primo\n",pids[i]);
            }else{
                printf("El hijo  %d, ha finalizado no es primo\n",pids[i]);
            }
            //exit(0);
            //printf("El hijo  %d, ha finalizado %s es primo\n",pids[i],(WEXITSTATUS(estado)==1)?"":"no ");
        }
    }
    // for (int i = 0; i < nProc; i++)
    // {
    //     // Padre

    //         // Esperar a que los procesos hijos terminen
    //         int estado;
    //         wait(&estado);
    //         printf("El hijo  %d, ha finalizado %s es primo\n",pids[i],(WEXITSTATUS(estado)==1)?"":"no ");
    // }
        
    return 0;
}