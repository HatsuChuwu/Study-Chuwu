/*
C 中的类型可分为以下几种：

1	基本数据类型
    算术类型，包括整型（int）、字符型（char）、浮点型（float）和双精度浮点型（double）。
2	枚举类型：
    它们也是算术类型，被用来定义在程序中只能赋予其一定的离散整数值的变量。
3	void 类型：
    类型说明符 void 表示没有值的数据类型，通常用于函数返回值。
4	派生类型：
    包括数组类型、指针类型和结构体类型。

数组类型和结构类型统称为聚合类型。函数的类型指的是函数返回值的类型。
*/

#include <stdio.h>
#include <limits.h>
#include <float.h>

// 定义一个返回值为空的函数
void printSeparator() {
    printf("\n====== 分隔线 ======\n");
}

// 定义一个参数为空的函数
int getRandomNumber() {
    return 42;
}

int main() {
    // 整数类型
    char c = 'A';
    unsigned char uc = 255;
    signed char sc = -128;
    int i = 100;
    unsigned int ui = 200;
    short s = -32768;
    unsigned short us = 65535;
    long l = -2147483648;
    unsigned long ul = 4294967295;

    printf("整数类型示例：\n");
    printf("char: %c\n", c);
    printf("unsigned char: %u\n", uc);
    printf("signed char: %d\n", sc);
    printf("int: %d\n", i);
    printf("unsigned int: %u\n", ui);
    printf("short: %d\n", s);
    printf("unsigned short: %u\n", us);
    printf("long: %ld\n", l);
    printf("unsigned long: %lu\n", ul);
    printSeparator();

    // 使用 sizeof 运算符获取整数类型的大小
    printf("整数类型大小：\n");
    printf("sizeof(char): %lu 字节\n", sizeof(char));
    printf("sizeof(unsigned char): %lu 字节\n", sizeof(unsigned char));
    printf("sizeof(signed char): %lu 字节\n", sizeof(signed char));
    printf("sizeof(int): %lu 字节\n", sizeof(int));
    printf("sizeof(unsigned int): %lu 字节\n", sizeof(unsigned int));
    printf("sizeof(short): %lu 字节\n", sizeof(short));
    printf("sizeof(unsigned short): %lu 字节\n", sizeof(unsigned short));
    printf("sizeof(long): %lu 字节\n", sizeof(long));
    printf("sizeof(unsigned long): %lu 字节\n", sizeof(unsigned long));
    printSeparator();

    // 浮点类型
    float f = 3.14f;
    double d = 3.1415926;
    long double ld = 3.14159265358979323846L;

    printf("浮点类型示例：\n");
    printf("float: %f\n", f);
    printf("double: %lf\n", d);
    printf("long double: %Lf\n", ld);
    printSeparator();

    // 使用 float.h 中的宏获取浮点类型的范围和精度
    printf("浮点类型范围和精度：\n");
    printf("float 存储最大字节数: %lu 字节\n", sizeof(float));
    printf("float 最小值: %E\n", FLT_MIN);
    printf("float 最大值: %E\n", FLT_MAX);
    printf("float 精度值: %d 位有效位\n", FLT_DIG);
    printf("double 存储最大字节数: %lu 字节\n", sizeof(double));
    printf("double 最小值: %E\n", DBL_MIN);
    printf("double 最大值: %E\n", DBL_MAX);
    printf("double 精度值: %d 位有效位\n", DBL_DIG);
    printf("long double 存储最大字节数: %lu 字节\n", sizeof(long double));
    printf("long double 最小值: %LE\n", LDBL_MIN);
    printf("long double 最大值: %LE\n", LDBL_MAX);
    printf("long double 精度值: %d 位有效位\n", LDBL_DIG);
    printSeparator();

    // void 类型
    printf("void 类型示例：\n");
    printSeparator();
    printf("调用返回值为空的函数\n");
    printSeparator();
    printf("调用参数为空的函数，返回值: %d\n", getRandomNumber());
    printSeparator();

    // 类型转换
    printf("类型转换示例：\n");
    // 隐式类型转换
    int intValue = 10;
    float floatValue = 3.14f;
    double resultImplicit = intValue + floatValue;
    printf("隐式类型转换: int + float = double: %lf\n", resultImplicit);

    // 显式类型转换
    double doubleValue = 3.14159;
    int intResult = (int)doubleValue;
    printf("显式类型转换: double -> int: %d\n", intResult);

    return 0;
}    