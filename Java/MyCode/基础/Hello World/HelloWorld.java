//这是单行注释

/*
 * 这是多行注释*/


/**
 * HelloWorld类是一个Java程序的入口类，用于打印"Hello World!你好世界！"的消息。
 * 这是文档注释，用于说明程序的功能和用法。 
 * 查看文档注释的方法是使用`javadoc`命令：javadoc -d docfiles(创建文件夹的名称) -author -version HelloWorld.java（文件名)
 * @author HJW
 * @version 1.0
 */

public class HelloWorld {
    public static void main(String[] args){
        // 使用System.out.println方法打印消息到控制台
        System.out.println("Hello World!你好世界！");
    }
}

/*
这段Java代码的含义是打印"Hello World!"。

实现原理：
1. `System.out`是一个名为`PrintStream`的类，它是Java标准库中的一个类，用于在程序中输出信息。
2. `println`是一个方法，它接受一个字符串参数，并将该字符串输出到`System.out`。,输出数据后自动换行；'print'方法则不自动换行。
3. 在Java中，通常使用`System.out.println()`方法来输出信息，因为`System.out`对象本身就可以直接输出字符串。

* main方法是程序的入口点，当程序运行时会首先执行main方法,然后执行main方法中的代码。
* @param args 是命令行参数，作用是接收命令行参数，一般不使用。

用途：
1. 作为Java程序的第一行，用于输出“Hello World!”，以验证程序是否正常工作。
2. 在一些简单的编程任务中，可以使用`System.out.println()`来输出中间结果，以便于调试和观察程序运行过程。

注意事项：
1. Java代码必须包含在`{}`大括号中，即使只有一行代码。
2. Java代码的文件扩展名为`.java`，而不是`.txt`或其他文件格式。
3. Java代码的编码通常为UTF-8，以确保不同语言字符的正确显示。
4. 在实际项目中，通常会有多个文件（包括`.java`文件）和目录结构，需要遵循特定的命名约定和目录结构。

*/