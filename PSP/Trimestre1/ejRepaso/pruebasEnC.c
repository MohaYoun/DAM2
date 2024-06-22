#include <stdio.h>

int main() {
    int var = 5;
    int *ptr;

    ptr = &var;
    printf("El valor de var es: %d\n", var);
    printf("La dirección de memoria de var es: %p\n", &var);
    printf("El valor de ptr es: %p\n", ptr);
    printf("El valor al que apunta ptr es: %d\n", *ptr);

    *ptr = 7;
    printf("El nuevo valor de var es: %d\n", var);

    return 0;
}

