#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <time.h>
#include <sys/types.h>
#include <sys/wait.h>

/* 1. Crea un proceso que cree un proceso hijo, el envíe 3 números enteros aleatorios a través de un pipe. 
El proceso hijo los ordenará y los escribirá en el fichero salida.txt.
*/

int main() {
    srand(time(NULL));// Para poder generar numeros aleatorios cuando quieras 
     // Creamos el pipe
    int fd[2];

    // Inicializamos el pipe y comprobamos por si falla
    if (pipe(fd) == -1) {
        perror("Pipe fallido");
        return 1;
    }
    // Creamos el proceso y lo inicializamos
    pid_t pid = fork();

    if (pid < 0) {
        perror("Fork fallido");
        return 1;
    }

    if (pid > 0) {
        // Proceso padre
        close(fd[0]); // Cierre la lectura en el pipe
        int nums[3]; // Creo un array Numeros donde almacenaremos los numeros random
        for (int i = 0; i < 3; i++) {
            nums[i] = rand() % 100; // Generar números aleatorios entre 0 y 100
        }
        write(fd[1], nums, 3 * sizeof(int)); // Escribir al pipe
        close(fd[1]); // Cierre la escritura en el pipe
        wait(NULL); // Esperar a que el hijo termine
    } else {
        // Proceso hijo
        close(fd[1]); // Cierre la escritura en el pipe
        int nums[3];
        read(fd[0], nums, 3 * sizeof(int)); // Leer desde el pipe

        // Ordenar los números
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        // Escribir en el fichero salida.txt
        FILE *file;
        file = fopen("salida.txt", "w");
        if (file == NULL) {
            printf("Error al abrir el archivo.");
            exit(1);
        }
        for (int i = 0; i < 3; i++) {
            fprintf(file, "%d\n", nums[i]);
            fprintf(stdout, "%d\n", nums[i]);
        }
        fclose(file);

        close(fd[0]); // Cierre la lectura en el pipe
        exit(0);
    }
    return 0;
}
