#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

/* Creación de múltiples procesos hijos: 
Modifica el programa para que el proceso padre cree varios procesos hijos en lugar de uno solo. 
Cada proceso hijo debe realizar una tarea diferente o imprimir un mensaje único. 
Asegúrate de que el proceso padre espere a que todos los hijos terminen antes de finalizar.

Uso de valores de retorno: 
Modifica el programa para que cada proceso hijo devuelva un valor de salida diferente utilizando exit(). 
El proceso padre debe recoger estos valores de salida utilizando wait() o waitpid() y mostrarlos en su salida.*/

int main() {
    pid_t pid;
    int i, status;
    int numProcesos = 3; // Número de procesos hijos a crear

    for (i = 0; i < numProcesos; i++) {
        pid = fork();

        if (pid < 0) { // Si hay un error en la creación del proceso
            fprintf(stderr, "Error al crear el proceso hijo\n");
            return 1;
        } else if (pid == 0) { // Si el valor de retorno del fork es 0, entonces es el proceso hijo
            printf("Soy el proceso hijo %d con ID: %d\n", i + 1, getpid());
            // Realiza alguna tarea específica para cada hijo
            exit(0); // El proceso hijo termina
        }
    }

    for (i = 0; i < numProcesos; i++) {
        wait(&status); // El proceso padre espera a que cada proceso hijo termine
    }

    printf("Soy el proceso padre\n");

    return 0;
}