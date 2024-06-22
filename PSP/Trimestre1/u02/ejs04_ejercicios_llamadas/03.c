#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

/* Espera a que el proceso hijo termine: Crea un programa que utiliza fork() para crear un proceso hijo. 
Luego, el proceso padre debe esperar a que el proceso hijo termine antes de imprimir un mensaje final. 
Utiliza wait() o waitpid() para lograr esta espera.*/
int main() {
    pid_t pid; // Definición de un identificador de proceso
    int status; // Variable para almacenar el estado de salida del proceso hijo

    pid = fork(); // Se crea un nuevo proceso

    if (pid < 0) { // Si hay un error en la creación del proceso
        fprintf(stderr, "Error al crear el proceso hijo\n");
        return 1;
    } else if (pid == 0) { // Si el valor de retorno del fork es 0, entonces es el proceso hijo
        printf("Soy el proceso hijo\n");
        // Cualquier tarea que desees realizar en el proceso hijo puede ir aquí
        exit(0); // El proceso hijo sale
    } else { // Si el valor de retorno del fork es mayor que 0, entonces es el proceso padre
        wait(&status); // El proceso padre espera a que el proceso hijo termine y luego imprime su mensaje
        printf("Soy el proceso padre\n");
    }
return 0;
}