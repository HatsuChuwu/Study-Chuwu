# 第01章：随堂复习与企业真题（Java语言概述）

***

## 一、随堂复习

### 1. Java基础全程的学习内容

```
第1阶段：Java基本语法
> Java概述、关键字、标识符、变量、运算符、流程控制（条件判断、选择结构、循环结构）、IDEA、数组

第2阶段：Java面向对象编程
> 类及类的内部成员
> 面向对象的三大特征
> 其它关键字的使用

第3阶段：Java语言的高级应用
> 异常处理、多线程、IO流、集合框架、反射、网络编程、新特性、其它常用的API等

```

神书：《Java核心技术》、《Effective Java》、《Java编程思想》

### 2. 软件开发相关内容

#### 2.1 计算机的构成

硬件 + 软件 

#### 2.2 软件

软件，即一系列按照`特定顺序组织`的计算机`数据`和`指令`的集合。有**系统软件**和**应用软件**之分。

- 系统软件，即操作系统，windows、Mac os 、linux、android、ios
- 应用软件，即os之上的应用程序。

#### 2.3 人机交互方式

- 图形化界面（GUI）
- 命令行交互方式（CLI）
  - 熟悉常用的dos命令：dir 、 cd 、cd.. 、 cd/  cd\ 、md、rd等

#### 2.4 计算机编程语言

- 语言的分代：
  
  - 第1代：机器语言
  - 第2代：汇编语言
  - 第3代：高级语言
    - 面向过程的语言：C
    - 面向对象的语言：C++、Java、C#、Python、Go、JavaScript

- **没有“最好”的语言**，只有在特定场景下相对来说，最适合的语言而已。

### 3. Java概述

#### 3.1 Java发展史

- 几个重要的版本：1996年，发布JDK1.0; 里程碑式的版本：JDK5.0、JDK8.0(2014年发布)
  ​                                JDK11（LTS）、JDK17（LTS）long term support

#### 3.2 Java 之父

詹姆斯·高斯林

### 3.3 Java具体的平台划分

J2SE --->JavaSE

J2EE ---->JavaEE

J2ME ---> JavaME

Java目前主要的应用场景：JavaEE后台开发、Android客户端的开发、大数据的开发

### 4. Java环境的搭建

- JDK、JRE、JVM三者之间的关系
- JDK的下载（官网）
- JDK的安装
  - 安装jdk8 和 jdk17
- 环境变量的配置（重要）

### 5. HelloWorld的编写和常见问题的解决（重点）

- 第1个程序

```java
class HelloChina{
    public static void main(String[] args){
        System.out.println("hello,world!!你好，中国！");
        System.out.print("hello,world!!你好，中国！");
        System.out.println("123abc");
        System.out.println(123 + 1);
    }
}
```

- 测试程序

```java
public class HelloJava{
    public static void main(String[] args){
        System.out.println("hello");
        System.out.println(10/0);
    }
}


class HelloShangHai{

}

class HelloBeijing{

}


```

- 小结

```

总结：
1. Java程序编写和执行的过程：
步骤1：编写。将Java代码编写在.java结尾的源文件中
步骤2：编译。针对于.java结尾的源文件进行编译操作。 格式：javac 源文件名.java
步骤3：运行。针对于编译后生成的字节码文件，进行解释运行。 格式： java 字节码文件名


2. 针对于步骤1的编写进行说明。

class HelloChina{
    public static void main(String[] args){
        System.out.println("hello,world!!你好，中国！");
    }
}

其中，
① class:关键字，表示"类"，后面跟着类名。
② main()方法的格式是固定的。务必记住！表示程序的入口
  public static void main(String[] args)  

如果非要有些变化的话，只能变化String[] args结构。可以写成：方式1：String args[]   方式2：String[] a

args:全程是arguments，简写成args

③ Java程序，是严格区分大小写的。

④ 从控制台输出数据的操作：
System.out.println() : 输出数据之后，会换行。
System.out.print() : 输出数据之后，不会换行。


⑤ 每一行执行语句必须以;结束。


3. 针对于步骤2的编译进行说明。

① 如果编译不通过。可以考虑的问题：
问题1：查看编译的文件名、文件路径是否书写错误
问题2：查看代码中是否存在语法问题。如果存在，就可能导致编译不通过。

② 编译以后，会生成1个或多个字节码文件。每一个字节码文件对应一个Java类，并且字节码文件名与类名相同。


4. 针对于步骤3运行进行说明。

① 我们是针对于字节码文件对应的Java类进行解释运行的。
要注意区分大小写！

② 如果运行不通过。可以考虑的问题：
问题1：查看解释运行的的类名、字节码文件路径是否书写错误
问题2：可能存在运行时异常。（放到第9章中具体讲解）


5. 一个源文件中可以声明多个类，但是最多只能有一个类使用public进行声明。
且要求声明为public的类的类名与源文件名相同。
```

### 6. 注释的使用

```java

/*
这是多行注释。

我们可以声明多行注释的信息！


1. Java中的注释的种类：
单行注释 、 多行注释 、 文档注释（Java特有）

2. 单行注释、多行注释的作用：
① 对程序中的代码进行解释说明
② 对程序进行调试

3. 注意：
① 单行注释和多行注释中声明的信息，不参与编译。换句话说，编译以后声明的字节码文件中不包含单行注释和
多行注释中的信息。
② 多行注释不能嵌套使用

4. 文档注释:
文档注释内容可以被JDK提供的工具 javadoc 所解析，生成一套以网页文件形式体现的该程序的说明文档。

*/
/**
这是我的第一个Java程序。很开森！^_^

@author shkstart
@version 1.0

*/
public class CommentTest{
    /**
    这是main()方法。格式是固定的。(文档注释)
    */
    /*
    这是main()方法。格式是固定的。(多行注释)
    */
    public static void main(String[] args){
        //这是输出语句
        System.out.println("hello,world!!");
        //System.out.println("hello,world!!")
    }
}
```

### 7. API文档

### 8. 练习

- 练习1

```java
class PersonalInfo{
    public static void main(String[] args) {
        System.out.println("姓名：康师傅");
        System.out.println(); //换行的操作
        System.out.println("性别：男");
        System.out.println("家庭住址：北京程序员聚集地：回龙观");
    }
}

```



- 练习2

```java
class StarPrintTest {
    public static void main(String[] args) {
        System.out.println("*    *");
        System.out.println("*\t\t*");
        System.out.println("*\n\n*");
    }
}

```



## 二、企业真题

### 1.一个”.java”源文件中是否可以包括多个类？有什么限制(明*数据)

是！

一个源文件中可以声明多个类，但是最多只能有一个类使用public进行声明。
且要求声明为public的类的类名与源文件名相同。

### 2.Java 的优势（阿**巴）

- 跨平台性
- 安全性高
- 简单性
- 高性能
- 面向对象性
- 健壮性
  
  

### 3.常用的几个命令行操作都有哪些？(至少4个)（北京数字**）

Java常用的命令行操作包括：

1. 编译Java源代码：使用`javac`命令编译Java源代码，例如：`javac HelloWorld.java`。

2. 运行Java程序：使用`java`命令运行Java程序，例如：`java HelloWorld`。

3. 生成JAR文件：使用`jar`命令将Java类文件打包成JAR文件，例如：`jar -cvf myprogram.jar *.class`。

4. 生成JavaDoc文档：使用`javadoc`命令生成JavaDoc文档，例如：`javadoc -d doc *.java`。

5. 查看Java进程：使用`jps`命令查看当前系统中的Java进程，例如：`jps`。

6. 获取Java进程堆栈信息：使用`jstack`命令获取Java进程的堆栈信息，例如：`jstack 1234`（1234为Java进程ID）。

7. 获取Java进程内存映射信息：使用`jmap`命令获取Java进程的内存映射信息，例如：`jmap 1234`（1234为Java进程ID）。

8. 生成Java进程堆转储文件：使用`jmap`命令生成Java进程堆转储文件，例如：`jmap -dump:format=b,file=heapdump.hprof 1234`（1234为Java进程ID）。

9. 启动Java飞行记录器：使用`jcmd`命令启动Java飞行记录器，例如：`jcmd 1234 VM.start_flight_recording`（1234为Java进程ID）。

10. 停止Java飞行记录器：使用`jcmd`命令停止Java飞行记录器，例如：`jcmd 1234 VM.stop_flight_recording`（1234为Java进程ID）。

### 4.Java 中是否存在内存溢出、内存泄漏？如何解决？举例说明（拼*多）

是的，Java程序也可能会出现内存溢出（OutOfMemoryError）和内存泄漏（Memory Leak）问题。以下是一些常见的解决方法：

1. 内存溢出（OutOfMemoryError）：

原因：程序在申请内存时，没有足够的内存空间供其使用，出现OutOfMemoryError。

解决方法：

- 检查程序是否存在内存泄漏，修复内存泄漏问题。
- 优化程序，降低内存使用量。
- 增加程序运行时的可用内存，通过参数`-Xmx`设置最大堆内存。

示例：

```java
public class MemoryLeakExample {
    static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            list.add(new Object()); // 内存泄漏
        }
    }
}
```

解决方法：

- 修复内存泄漏问题，例如：在不需要时删除或清空集合中的元素。

- 增加程序运行时的可用内存，通过参数`-Xmx`设置最大堆内存。
2. 内存泄漏（Memory Leak）：

原因：程序在运行过程中，由于设计错误、编码错误等原因，导致一些不再使用的对象没有被及时回收，从而造成内存空间的浪费。

解决方法：

- 检查程序是否存在内存泄漏，使用内存分析工具（如VisualVM、JProfiler等）进行内存分析，找出内存泄漏的地方并进行修复。
- 优化程序，降低内存使用量。
- 定期手动触发垃圾回收，通过调用`System.gc()`方法。

示例：

```java
public class MemoryLeakExample {
    static Map<String, Object> map = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            String key = UUID.randomUUID().toString();
            map.put(key, new Object()); // 内存泄漏
        }
    }
}
```

解决方法：

- 修复内存泄漏问题，例如：在不需要时删除或清空映射中的元素。
- 定期手动触发垃圾回收，通过调用`System.gc()`方法。

注意：频繁手动触发垃圾回收可能导致性能问题，因此建议在确实需要时再使用。

### 5. 如何看待Java是一门半编译半解释型的语言（携*）

半编译半解释型的语言是指编程语言的源代码被编译成中间代码，然后在运行时由解释器解释执行中间代码的一种编程语言。这种语言的特点介于编译型语言和解释型语言之间。

编译型语言（如C、C++等）的源代码被直接编译成机器码，然后在运行时由机器码直接执行，因此执行速度较快，但跨平台性较差。

解释型语言（如Python、Ruby等）的源代码在运行时被解释器解释执行，无需编译，因此具有很好的跨平台性，但执行速度较慢。

半编译半解释型的语言（如Java、C#等）的源代码被编译成中间代码，然后在运行时由解释器解释执行中间代码。这种语言的执行速度比解释型语言快，但比编译型语言慢；跨平台性比编译型语言好，但比解释型语言差。

半编译半解释型的语言的优点是跨平台性和动态性，缺点是性能问题、内存占用和调试困难。在实际应用中，需要根据具体场景和需求选择合适的编程语言。

<img title="" src="images/image002.jpg" alt="img" style="zoom:100%;">

Java是一种半编译半解释型的语言，这种特点既有优点也有缺点。

优点：

1. 跨平台性：Java的半编译半解释特性使得Java程序可以在不同的操作系统上运行，具有很好的跨平台性。Java程序被编译成字节码，然后在不同的平台上由解释器解释执行，因此可以跨平台运行。

2. 动态性：Java的半编译半解释特性使得Java程序具有动态性。Java程序可以在运行时动态加载类库，方便程序的扩展和升级。

3. 安全性：Java的半编译半解释特性使得Java程序具有安全性。Java编译器不会将代码直接编译成机器码，而是编译成字节码，字节码在执行时由解释器解释执行，这样可以避免一些常见的安全漏洞，如缓冲区溢出等。

缺点：

1. 性能问题：由于Java程序被编译成字节码，然后在运行时由解释器解释执行，因此相比于完全编译型的语言，Java程序的执行速度可能较慢。

2. 内存占用：Java程序的半编译半解释特性可能导致内存占用较大。字节码需要在运行时由解释器解释执行，因此可能导致内存占用较大。

3. 调试困难：由于Java程序被编译成字节码，然后在运行时由解释器解释执行，因此调试Java程序可能比较困难。堆栈信息等可能无法直接映射到源代码中，导致调试困难。

综上所述，Java作为一门半编译半解释型的语言，具有跨平台性、动态性和安全性等优点，但也存在性能问题、内存占用和调试困难等缺点。在实际应用中，需要根据具体场景和需求选择合适的编程语言。
