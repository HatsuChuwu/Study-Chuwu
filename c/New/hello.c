#include <stdio.h>
int c = 100; // global variable

int main()
{
    printf("Hello World!\n");
    int age = 18;
    printf("My age is %d\n", age);
    {
        int gold;
        printf("gold = %d\n", gold); 
    }
    printf("c = %d\n", c);
    return 0;
}