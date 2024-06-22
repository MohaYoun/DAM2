#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/wait.h>
#include <math.h>

const int POTENCIA = 10;
/*
int main(int argc, char *argv[]){

    int m = atoi(argv[1]);
    
    for (int i = 0; i < m; i++)
    {
        
        pid_t pid = fork();
        for (int j = 0; j < m; j++)
        {
            int n = atoi(argv[2]);
        }
        
        

    }
    

}*/
// Función para verificar si un número es primo
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
    if (argc != 3) {
        fprintf(stderr, "Uso: %s <longitud_n> <num_procesos_m>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    int longitud_n = atoi(argv[1]);
    int num_procesos_m = atoi(argv[2]);

    printf("Números primos de longitud %d:\n", longitud_n);

    for (int i = 0; i < pow(POTENCIA, longitud_n); i++) {
        if (esPrimo(i)) {
            printf("%d ", i);
        }
    }

    printf("\n");

    return 0;
}