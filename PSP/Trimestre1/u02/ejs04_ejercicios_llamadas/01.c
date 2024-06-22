#include <stdio.h>
#include <string.h>
#include <openssl/evp.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
/* Creación de un proceso hijo: Escribe un programa en C que utilice fork() para crear un proceso hijo.
El proceso hijo debe imprimir un mensaje como "Soy el proceso hijo" y el proceso padre debe imprimir "Soy el proceso padre".
Asegúrate de que ambos procesos se ejecuten correctamente y muestren su respectivo mensaje.*/

int main()
{
   pid_t pid; // Definición de un identificador de proceso

   pid = fork(); // Se crea un nuevo proceso

   if (pid < 0) { // Si hay un error en la creación del proceso
       fprintf(stderr, "Error al crear el proceso hijo \n");
       return 1;
   } else if (pid == 0) { // Si el valor de retorno del fork es 0, entonces es el proceso hijo
       printf("Soy el proceso hijo y mi id es %d\n", getpid());
   } else { // Si el valor de retorno del fork es mayor que 0, entonces es el proceso padre
       printf("Soy el proceso padre y mi id es %d\n", getpid());
   }
   return 0;
}