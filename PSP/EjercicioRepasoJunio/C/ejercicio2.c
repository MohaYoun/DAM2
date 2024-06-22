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
Escribe un programa que reciba por parámetro un número, este número indicará el número de procesos a crear.

Los hijos buscarán entre los números del 10000 al 99999 los números capicúas. Cada hijo buscará más o menos un número parecido de números.
*/

int comprobarCapicua(int num){
    char buf[BUFFER];
    int n = sprintf(buf, "%d", num);
    //printf("%s", buf);
    
    int cifra,reves=0,copia,esCapicua;
    copia=num;
    while(num != 0){
        cifra = num % 10;
        reves = reves*10+cifra;
        num =num/10;
    }
    
    if(copia == reves){
        esCapicua = 1;
    } 
    else
    {
        esCapicua=0;
    }
    return esCapicua;
}
int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Uso: %s <numeros aleatorios>\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    int n_proc = atoi(argv[1]);
    
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
            int n1=10000;
            int n2=100000;
            for (int c1 = n1; c1 < n2; c1++)
            {
                if (c1 % n_proc == i)
                {
                    if(comprobarCapicua(c1)){
                        printf("El numero %d es capicua pid= %d.\n", c1, getpid());
                    }
                    
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
