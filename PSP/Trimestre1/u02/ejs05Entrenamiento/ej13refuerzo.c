#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>
#define ES_PRIMO 0
#define NO_ES_PRIMO 1
/* Crea un programa que reciba por parámetro dos números grandes. El programa creará dos procesos hijos.
    Cada hijo gestionará un número primo y verificará si es primo o no.
    Cada hijo al finalizar indica en su estado si el número era primo o no y el proceso padre al recoger el estado del hijo cuenta si era primo o no,
    el padre escribe el total de números primos.*/
int esPrimo(int numero)
{
    if (numero == 0 || numero == 1)
        return 0;
    /*
            El número 4 es un caso especial, pues al dividirlo entre
            2 el resultado es 2, y el ciclo nunca se cumple, indicando que
            el 4 SÍ es primo, pero realmente NO lo es, así que si el número es 4
                          inmediatamente indicamos que no es primo (regresando 0)
            Nota: solo es para el 4, los demás funcionan bien
    */
    if (numero == 4)
        return 0;
    for (int x = 2; x < numero / 2; x++)
    {
        // Si es divisible por cualquiera de estos números, No es primo
        if (numero % x == 0)
            return 0;
    }
    // Si no se pudo dividir por ninguno de los de arriba, sí es primo
    return 1;
}
int main(int argc, char *argv[])
{
    if (argc < 3)
    {
        fprintf(stderr, "ERROR. Introduce: %s num1 num2\n", argv[0]);
        return 1;
    }

    int num1 = atoll(argv[1]);
    int num2 = atoll(argv[2]);
    int contadorPrimos = 0;
    int nProcesos = 2;

    for (int i = 0; i < nProcesos; i++)
    {
        pid_t hijo = fork();

        if (hijo < 0)
        {
            perror("Error al crear el proceso hijo");
            return 1;
        }

        if (hijo == 0)
        {
            // Código del hijo
            int num = (i == 0) ? num1 : num2; // Determina cuál número procesa cada hijo
            if (esPrimo(num))
            {
                exit(ES_PRIMO);
            }
            else
            {
                exit(NO_ES_PRIMO);
            }
        }
    }

    for (int i = 0; i < nProcesos; i++)
    {
        // Código del padre
        int status;
        wait(&status);

        if (WIFEXITED(status) && WEXITSTATUS(status) == ES_PRIMO)
        {
            contadorPrimos++;
        }
    }

    printf("Total de números primos: %d\n", contadorPrimos);

    return 0;
}

// int esPrimo(int numero)
// {
//     if (numero <= 1)
//     {
//         return 0; // Los números menores o iguales a 1 no son primos
//     }
//     for (int i = 2; i * i <= numero; i++)
//     {
//         if (numero % i == 0)
//         {
//             return 0; // El número no es primo
//         }
//     }
//     return 1; // El número es primo
// }