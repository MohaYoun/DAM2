#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
/* Ejecución de un programa en el proceso hijo: Modifica el programa anterior para que, 
en lugar de imprimir un mensaje, el proceso hijo ejecute un programa diferente utilizando exec(). 
Por ejemplo, el proceso hijo podría ejecutar un programa que muestra la fecha y hora actual. 
Asegúrate de que el proceso padre todavía imprima su mensaje después de que el proceso hijo termine.*/

int main()
{
    pid_t pid; // Definición de un identificador de proceso

    pid = fork(); // Se crea un nuevo proceso

    if (pid < 0) { // Si hay un error en la creación del proceso
        fprintf(stderr, "Error al crear el proceso hijo\n");
        return 1;
    } else if (pid == 0) { // Si el valor de retorno del fork es 0, entonces es el proceso hijo
        char *fecha[] = { "/bin/date", NULL }; // Argumentos para el programa a ejecutar
        execv("/bin/date", fecha); // Ejecuta el programa con los argumentos dados
        /*execl("/bin/date", "date", NULL); /*Ejecuta el programa /bin/date,
            la función execl toma el nombre del programa a ejecutar y sus argumentos como argumentos separados,
            en lugar de un arreglo de cadenas como en el caso de execv.
            Aquí, "date" se pasa como el primer argumento después de la ruta del programa "/bin/date"*/
    } else { // Si el valor de retorno del fork es mayor que 0, entonces es el proceso padre
        wait(NULL); // Espera a que el proceso hijo termine
        printf("Soy el proceso padre\n");
    }

    return 0;
}