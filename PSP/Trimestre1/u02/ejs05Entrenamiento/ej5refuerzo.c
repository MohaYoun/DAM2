#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/wait.h>
#define SCNDS 20
void signal_handler(int signal) {
    printf("Recibido y terminando el proceso hijo\n");
    exit(0);
}

int main() {
    pid_t pid;
    int status;

    // Definir el manejador de señales
    signal(SIGUSR1, signal_handler);

    pid = fork();

    if (pid < 0) {
        fprintf(stderr, "Error al crear el proceso hijo\n");
        return 1;
    } else if (pid == 0) {
        // Proceso hijo
        while (1) {
            // El hijo espera la señal indefinidamente
            printf(".");
            fflush(stdout);
            sleep(1);
        }
    } else {
        // Proceso padre
        sleep(SCNDS); // Espera un momento antes de enviar la señal al hijo
        kill(pid, SIGUSR1); // Envía la señal SIGUSR1 al proceso hijo
        wait(&status); // Espera a que el proceso hijo termine
        printf("Soy el proceso padre y el proceso hijo ha terminado\n");
    }
    return 0;
}
