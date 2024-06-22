#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <math.h>
#include <sys/wait.h>
#include <string.h>
#include <ctype.h>
#define BUFFER 1024
/*
Escribe un programa en C que acepte un número N como parámetro de línea de comandos. 
Este número N representará la cantidad de procesos hijo que el proceso padre debe crear utilizando fork. 
Cada proceso hijo debe calcular la letra del DNI para un rango específico de números, dividiendo el espacio total (desde 0 hasta 99999999) en N partes iguales.

Especificaciones:

Cada hijo calculará la letra del DNI para su rango asignado de números y escribirá en un archivo único los resultados en el formato "DNI - Letra", uno por línea.
Los archivos serán nombrados dni_output_.txt, donde es el número del proceso hijo.
El proceso padre debe esperar a que todos los hijos terminen su ejecución antes de imprimir en la consola "Procesado completado para todos los hijos".
Detalles del cálculo de la letra del DNI:
La letra del DNI en España se calcula dividiendo el número del DNI por 23 y 
el residuo determina la letra según una tabla predefinida de letras (por ejemplo, TRWAGMYFPDXBNJZSQVHLCKE).
Consideraciones:

Usa wait para asegurar que el padre espere por cada hijo.
Emplea fprintf para escribir en los archivos.
Utiliza sprintf para construir los strings que necesitas escribir en el archivo.
*/
char calcularLetra(int dni) {
    char letras[] = {"TRWAGMYFPDXBNJZSQVHLCKE"};
    return letras[dni % 23];
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Uso: %s <numeros aleatorios>\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    int n_proc = atoi(argv[1]);
    
    // Abrir el archivo en modo escritura de texto
    FILE *archivo = fopen("dni_output_.txt", "w");
    
    pid_t pid;
    for (int i = 0; i < n_proc; i++)
    {
        pid=fork();
        if(pid < 0)
        {
            perror("frork fallido");
            return 1; 
        }
        if (pid == 0)
        {
            int max = 100000000;
            for (int c1 = i; c1 < max; c1 += n_proc) {
                if (c1 % n_proc == i)
                {
                    char *letraCalculada = calcularLetra(c1);
                    fprintf(archivo, "%d%s pid = %d\n", c1, &letraCalculada, getpid());
                    //printf("%d%s\n", c1, &letraCalculada);
                }
            }
            exit(0);            
        }
    }
    for (int i = 0; i < n_proc; i++)
    {
        waitpid(pid, NULL, 0);
    }
}
