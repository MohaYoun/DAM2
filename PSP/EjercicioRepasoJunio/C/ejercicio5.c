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

#define READ 0
#define WRITE 1
/*
Crea un programa en C que simule un simple sistema de cálculo con un proceso padre que interactúa con el usuario y un proceso hijo que realiza los cálculos. 
El padre mostrará un menú para que el usuario seleccione entre sumar, restar o salir, 
y enviará los detalles de la operación al hijo a través de una tubería. 
El hijo procesará estos datos y enviará el resultado de vuelta al padre a través de otra tubería.

Especificaciones:

El programa debe mostrar un menú continuamente hasta que el usuario seleccione la opción de "salir".
El menú ofrecerá las opciones:
Sumar dos números.
Restar dos números.
Salir.
El proceso padre recoge la elección del usuario. Si es una operación, 
también solicita dos números y los envía, junto con la operación, al hijo a través de una tubería.
El proceso hijo recibe los datos, realiza el cálculo y envía el resultado de vuelta al padre a través de otra tubería.
Si el usuario selecciona "salir", el padre enviará una señal de terminación 
(SIGKILL, es decir, señal -9) al hijo para terminar su ejecución de manera inmediata y luego el padre terminará su propia ejecución.
Detalles adicionales:

Se deben usar dos tuberías: una para enviar datos del padre al hijo y otra para enviar el resultado de vuelta al padre.
El padre debe manejar adecuadamente la apertura y cierre de los extremos de las tuberías para evitar bloqueos y fugas de recursos.
Implementa una gestión adecuada de errores para la creación de procesos y tuberías.
Asegúrate de que el padre envía la señal SIGKILL al hijo solo cuando el usuario elija salir, 
lo que garantiza una terminación limpia del hijo antes de que el padre también termine.
Estructura del programa:

Menú interactivo: El padre muestra el menú y recoge la elección del usuario.
Comunicación padre-hijo: El padre envía la operación y los números al hijo a través de una tubería, 
y recibe los resultados del cálculo del hijo a través de otra tubería.
Terminación del hijo: Cuando el usuario elige salir, el padre envía una señal SIGKILL al hijo para terminar su proceso de manera inmediata.
*/
int main(int argc, char *argv[]) {
    int fd[2];
    int fd2[2];
    pid_t pid;

    if (pipe(fd) == -1 || pipe(fd2) == -1) {// INICIALIZAMOS LOS PIPES
        perror("Error al hacer el pipe");
        return 1;
    }
    pid = fork();
    if (pid < 0)
    {
        perror("Error al crear el fork");
        return 1;
    }
    if (pid == 0)// Código proceso Hijo
    {
        close(fd[WRITE]);// Cerrar el extremo de escritura del pipe en el proceso padre
        close(fd2[READ]);
        while (1)
        {
            int resultado, n1, n2;
            char opr;
            read(fd[READ], &n1, sizeof(n1));
            read(fd[READ], &opr, sizeof(opr));
            read(fd[READ], &n2, sizeof(n2));
            printf("n1:%d\n", n1);
            printf("n2:%d\n", n2);
            printf("operador:%s\n", &opr);
            if(opr == '+'){
                resultado = n1 + n2;
            }else if(opr == '-'){
                resultado = n1 - n2;
            }else{
                perror("El operador no es valido\n");
                return 1;
            }
            write(fd2[WRITE], &resultado, sizeof(int));
            
            // close(fd[READ]);// Cerrar el extremo de lectura del pipe en el proceso padre
            // close(fd2[WRITE]);
            // exit(EXIT_SUCCESS);
        }
        
        
    }else{
        int opc;
        close(fd[READ]);// Cerrar el extremo de lectura del pipe en el proceso hijo
        close(fd2[WRITE]);
        do
        {
            printf("Introduce una opcion numerica:\n 1.Sumar dos numeros.\n 2.Restar dos numeros.\n 3.Salir.\n");
            scanf("%d",&opc);
            if ((opc > 3)||(opc < 0))
            {       sleep(1);
                printf("La opcion seleccionada no es correcta\n");
            }
            else if (opc == 3)
            {
                kill(pid, -9);
                exit(EXIT_SUCCESS);
            }
            else
            {
                // Código para el proceso Padre
                int n1, n2, res;
                char op;
                printf("Introduce el primero numero.\n");
                scanf("%d",&n1);
                printf("Introduce el segundo numero.\n");
                scanf("%d",&n2);
                printf("Introduce el operador numero.\n");
                scanf("%s",&op);
                write(fd[WRITE], &n1, sizeof(int));
                write(fd[WRITE], &op, sizeof(char));
                write(fd[WRITE], &n2, sizeof(int));// Escribir el resultado en el pipe
                read(fd2[READ], &res, sizeof(int));
                printf("El resultado es: %d\n", res);// Leer el resultado del pipe
                // close(fd[WRITE]);// Cerrar el extremo de escritura del pipe en el proceso hijo
                // close(fd2[READ]);
            }
        } while (opc != 3);
        wait(NULL);
    }
    return 0;
}