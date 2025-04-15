#include <stdio.h>

// 全局变量定义与初始化
int globalInt = 10;
float globalFloat = 3.14f;
char globalChar = 'A';

// 函数声明
int add(int a, int b);

// 外部变量声明
extern int externalVar;

int main() {
    // 变量定义与初始化
    int localVar = 20;
    float localFloat = 2.71f;
    char localChar = 'B';

    // 指针变量定义与初始化
    int *ptr = &localVar;

    // 后续初始化变量
    int lateInit;
    lateInit = 30;

    // 多个变量定义与初始化
    int i = 1, j = 2, k = 3;

    // 左值右值示例
    int leftValue = 40;
    // 右值不能放在赋值号左边，下面语句会编译错误
    // 10 = 50; 

    // 打印变量值
    printf("全局整型变量 globalInt: %d\n", globalInt);
    printf("全局浮点型变量 globalFloat: %.2f\n", globalFloat);
    printf("全局字符型变量 globalChar: %c\n", globalChar);
    printf("局部整型变量 localVar: %d\n", localVar);
    printf("局部浮点型变量 localFloat: %.2f\n", localFloat);
    printf("局部字符型变量 localChar: %c\n", localChar);
    printf("指针 ptr 指向的值: %d\n", *ptr);
    printf("后续初始化变量 lateInit: %d\n", lateInit);
    printf("多个变量 i, j, k: %d, %d, %d\n", i, j, k);
    printf("左值变量 leftValue: %d\n", leftValue);

    // 函数调用
    int result = add(i, j);
    printf("函数调用结果 add(i, j): %d\n", result);

    return 0;
}

// 函数定义
int add(int a, int b) {
    return a + b;
}

// 外部变量定义
int externalVar = 100;
    