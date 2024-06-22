#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

// Función de controlador de señal para SIGINT
void sigint_handler(int signo)
{
    // printf("Se recibió la señal SIGINT (Ctrl + C)\n");
    //  Aquí puedes realizar acciones adicionales antes de salir si lo deseas
    // exit(0);
    printf("No mataras a este proceso");
}

int main()
{
    // Registrar un manejador de señales para SIGINT usando la función signal
    signal(SIGINT, sigint_handler);

    printf("Ejecuta este programa y presiona Ctrl + C para generar una señal SIGINT.\n");

    // Mantén el programa en ejecución para recibir la señal
    while (1)
    {
        printf(".");
        fflush(stdout);
        sleep(1);
    }

    return 0;
}

// Función de controlador de señal para SIGINT
void sigint_handler1(int signo)
{
    printf("Hola\n");
    // Aquí puedes realizar acciones adicionales antes de salir si lo deseas
    exit(0);
}
void sigint_handler2(int signo)
{
    printf("Mundo\n");
    // Aquí puedes realizar acciones adicionales antes de salir si lo deseas
}

int main()
{
    // Registrar un manejador de señales para SIGINT usando la función signal
    signal(SIGINT, sigint_handler1);
    signal(SIGINT, sigint_handler2);

    printf("Ejecuta este programa y presiona Ctrl + C para generar una señal SIGINT.\n");

    // Mantén el programa en ejecución para recibir la señal
    while (1)
    {
        sleep(1);
    }

    return 0;
}
