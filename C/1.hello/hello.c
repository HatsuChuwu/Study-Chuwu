#include <stdio.h>
int main()
{
    /* 我的第一个 C 程序 */
    printf("Hello World! \n");
    return 0;
}

// 程序的第一行 #include <stdio.h> 是预处理器指令，告诉 C 编译器在实际编译之前要包含 stdio.h 文件。
// 下一行 int main() 是主函数，程序从这里开始执行。
// 下一行 /*...*/ 将会被编译器忽略，这里放置程序的注释内容。它们被称为程序的注释。
// 下一行 printf(...) 是 C 中另一个可用的函数，会在屏幕上显示消息 "Hello, World!"。
// 下一行 return 0; 终止 main() 函数，并返回值 0。

/*以上代码组成结构如下：
预处理器指令：如 #include 和 #define。
主函数：每个 C 程序都有一个 main() 函数。
变量声明：声明程序中使用的变量。
函数定义：定义程序中使用的函数。*/