# JAVA

一个 Java 程序可以认为是一系列对象的集合，而这些对象通过调用彼此的方法来协同工作。下面简要介绍下类、对象、方法和实例变量的概念。

* **对象**：对象是类的一个实例，有状态和行为。例如，一条狗是一个对象，它的状态有：颜色、名字、品种；行为有：摇尾巴、叫、吃等。
* **类**：类是一个模板，它描述一类对象的行为和状态。
* **方法**：方法就是行为，一个类可以有很多方法。逻辑运算、数据修改以及所有动作都是在方法中完成的。
* **实例变量**：每个对象都有独特的实例变量，对象的状态由这些实例变量的值决定。

第一个 Java 程序
-----------

下面看一个简单的 Java 程序，它将输出字符串 _Hello World_

```
public class HelloWorld {
    /* 第一个Java程序
     * 它将输出字符串 Hello World
     */
    public static void main(String[] args) {
        System.out.println("Hello World"); // 输出 Hello World
    }
}
```

![662E827A-FA32-4464-B0BD-40087F429E98](https://www.runoob.com/wp-content/uploads/2013/12/662E827A-FA32-4464-B0BD-40087F429E98.jpg)

public：公共的，用它修饰的类或成员在任意位置可见

static：静态的，用它修饰的方法，可以不用创建对象就可以调用

void：表示该方法没有返回值

main：Java的主方法名，JavaSE的程序入口

String[]：字符串数组，这是main方法的形参类型，可以通过命令行参数传值

args：这是main方法的形参名，如果要在main中使用命令行参数，可以遍历该args数组。



下面将逐步介绍如何保存、编译以及运行这个程序：

* 打开代码编辑器，把上面的代码添加进去；
* 把文件名保存为：HelloWorld.java；
* 打开 cmd 命令窗口，进入目标文件所在的位置，假设是 C:\
* 在命令行窗口输入 `javac HelloWorld.java` 按下回车键编译代码。如果代码没有错误，cmd 命令提示符会进入下一行（假设环境变量都设置好了）。
* 再键输入 `java HelloWorld` 按下回车键就可以运行程序了

你将会在窗口看到 Hello World

```
$ javac HelloWorld.java
$ java HelloWorld 
Hello World
```

如果遇到编码问题，我们可以使用 `-encoding` 选项设置 **utf-8** 来编译：

```
javac -encoding UTF-8 HelloWorld.java 
java HelloWorld
```

![java-HelloWorld](http://www.runoob.com/wp-content/uploads/2013/12/java-HelloWorld.gif)



## 基本语法

编写 Java 程序时，应注意以下几点：

* **大小写敏感**：Java 是大小写敏感的，这就意味着标识符 Hello 与 hello 是不同的。
* **类名**：对于所有的类来说，类名的首字母应该大写。如果类名由若干单词组成，那么每个单词的首字母应该大写，例如 **MyFirstJavaClass** 。
* **方法名**：所有的方法名都应该以小写字母开头。如果方法名含有若干单词，则后面的每个单词首字母大写。
* **源文件名**：源文件名必须和类名相同。当保存文件的时候，你应该使用类名作为文件名保存（切记 Java 是大小写敏感的），文件名的后缀为 **.java**。（如果文件名和类名不相同则会导致编译错误）。
* **主方法入口**：所有的 Java 程序由 **public static void main(String[] args)** 方法开始执行。

Java 标识符
--------

Java 所有的组成部分都需要名字。类名、变量名以及方法名都被称为标识符。

关于 Java 标识符，有以下几点需要注意：

* 所有的标识符都应该以字母（A-Z 或者 a-z）, 美元符（$）、或者下划线（_）开始
* 首字符之后可以是字母（A-Z 或者 a-z）, 美元符（$）、下划线（_）或数字的任何字符组合
* 关键字不能用作标识符
* 标识符是大小写敏感的
* 合法标识符举例：age、$salary、_value、__1_value
* 非法标识符举例：123abc、-salary

**标识符的命名规范**（建议遵守的`软性要求`）:
    > 包名：多单词组成时所有字母都小写：xxxyyyzzz。
      例如：java.lang、com.atguigu.bean

    > 类名、接口名：多单词组成时，所有单词的首字母大写：XxxYyyZzz
      例如：HelloWorld，String，System等

    > 变量名、方法名：多单词组成时，第一个单词首字母小写，第二个单词开始每个单词首字母大写：xxxYyyZzz
      例如：age,name,bookName,main,binarySearch,getName

    > 常量名：所有字母都大写。多单词时每个单词用下划线连接：XXX_YYY_ZZZ
      例如：MAX_VALUE,PI,DEFAULT_CAPACITY

注意：在起名字时，为了提高阅读性，要尽量有意义，“见名知意”。

Java 修饰符
--------

像其他语言一样，Java 可以使用修饰符来修饰类中方法和属性。主要有两类修饰符：

* 访问控制修饰符 : default, public , protected, private
* 非访问控制修饰符 : final, abstract, static, synchronized

在后面的章节中我们会深入讨论 Java 修饰符。

## Java 变量

* 局部变量
* 类变量（静态变量）
* 成员变量（非静态变量）

Java 数组
-------

数组是储存在堆上的对象，可以保存多个同类型变量。在后面的章节中，我们将会学到如何声明、构造以及初始化一个数组。
Java 枚举

-------

Java 5.0 引入了枚举，枚举限制变量只能是预先设定好的值。使用枚举可以减少代码中的 bug。

例如，我们为果汁店设计一个程序，它将限制果汁为小杯、中杯、大杯。这就意味着它不允许顾客点除了这三种尺寸外的果汁。

### 实例

```
class FreshJuice {
   enum FreshJuiceSize{ SMALL, MEDIUM , LARGE }
   FreshJuiceSize size;
}

public class FreshJuiceTest {
   public static void main(String[] args){
      FreshJuice juice = new FreshJuice();
      juice.size = FreshJuice.FreshJuiceSize.MEDIUM  ;
   }
}
```

注意：枚举可以单独声明或者声明在类里面。方法、变量、构造函数也可以在枚举中定义。

## Java 关键字

下面列出了 Java 关键字。这些保留字不能用于常量、变量、和任何标识符的名称。

<table><tbody><tr><th>类别</th><th>关键字</th><th>说明</th></tr><tr><td rowspan="4" align="center">访问控制</td><td>private</td><td>私有的</td></tr><tr><td>protected</td><td>受保护的</td></tr><tr><td>public</td><td>公共的</td></tr><tr><td>default</td><td>默认</td></tr><tr><td rowspan="13" align="center">类、方法和变量修饰符</td><td>abstract</td><td>声明抽象</td></tr><tr><td>class</td><td>类</td></tr><tr><td>extends</td><td>扩充、继承</td></tr><tr><td>final</td><td>最终值、不可改变的</td></tr><tr><td>implements</td><td>实现（接口）</td></tr><tr><td>interface</td><td>接口</td></tr><tr><td>native</td><td>本地、原生方法（非 Java 实现）</td></tr><tr><td>new</td><td>创建</td></tr><tr><td>static</td><td>静态</td></tr><tr><td>strictfp</td><td>严格浮点、精准浮点</td></tr><tr><td>synchronized</td><td>线程、同步</td></tr><tr><td>transient</td><td>短暂</td></tr><tr><td>volatile</td><td>易失</td></tr><tr><td rowspan="11" align="center">程序控制语句</td><td>break</td><td>跳出循环</td></tr><tr><td>case</td><td>定义一个值以供 switch 选择</td></tr><tr><td>continue</td><td>继续</td></tr><tr><td>do</td><td>运行</td></tr><tr><td>else</td><td>否则</td></tr><tr><td>for</td><td>循环</td></tr><tr><td>if</td><td>如果</td></tr><tr><td>instanceof</td><td>实例</td></tr><tr><td>return</td><td>返回</td></tr><tr><td>switch</td><td>根据值选择执行</td></tr><tr><td>while</td><td>循环</td></tr><tr><td rowspan="6" align="center">错误处理</td><td>assert</td><td>断言表达式是否为真</td></tr><tr><td>catch</td><td>捕捉异常</td></tr><tr><td>finally</td><td>有没有异常都执行</td></tr><tr><td>throw</td><td>抛出一个异常对象</td></tr><tr><td>throws</td><td>声明一个异常可能被抛出</td></tr><tr><td>try</td><td>捕获异常</td></tr><tr><td rowspan="2" align="center">包相关</td><td>import</td><td>引入</td></tr><tr><td>package</td><td>包</td></tr><tr><td rowspan="8" align="center">基本类型</td><td>boolean</td><td>布尔型</td></tr><tr><td>byte</td><td>字节型</td></tr><tr><td>char</td><td>字符型</td></tr><tr><td>double</td><td>双精度浮点</td></tr><tr><td>float</td><td>单精度浮点</td></tr><tr><td>int</td><td>整型</td></tr><tr><td>long</td><td>长整型</td></tr><tr><td>short</td><td>短整型</td></tr><tr><td rowspan="3" align="center">变量引用</td><td>super</td><td>父类、超类</td></tr><tr><td>this</td><td>本类</td></tr><tr><td>void</td><td>无返回值</td></tr><tr><td rowspan="3" align="center">保留关键字</td><td>goto</td><td>是关键字，但不能使用</td></tr><tr><td>const</td><td>是关键字，但不能使用</td></tr></tbody></table>

注意：Java 的 null 不是关键字，类似于 true 和 false，它是一个字面常量，不允许作为标识符使用。

## Java 注释

类似于 C/C++、Java 也支持单行以及多行注释。

注释中的字符将被 Java 编译器忽略。

```
public class HelloWorld {
   /* 这是第一个Java程序
    * 它将输出 Hello World
    * 这是一个多行注释的示例
    */
    public static void main(String[] args){
       // 这是单行注释的示例
       /* 这个也是单行注释的示例 */
       System.out.println("Hello World"); 
    }
}
```

在计算机语言中，注释是计算机语言的一个重要组成部分，用于在源代码中解释代码的作用，可以增强程序的可读性，可维护性。

Java 注释是一种在 Java 程序中用于提供代码功能说明的文本。

注释不会被编译器包含在最终的可执行程序中，因此不会影响程序的运行。

注释是良好的编程习惯，它们帮助程序员更容易地理解代码的用途和功能，并且在团队协作中非常有用。

Java 注释主要有三种类型：

* 单行注释
* 多行注释
* 文档注释

### 单行注释

```
//
    // 这是一个单行注释
    int x = 10; // 初始化一个变量x为10
```

### 多行注释

```
多行注释以 `/*`开始，以 `*/`结束：
    /*
    这是一个多行注释
    可以用来注释多行代码
    */
    int y = 20; // 初始化一个变量y为20
```

### 文档注释

```
文档注释以 `/**` 开始，以 `*/` 结束，通常出现在类、方法、字段等的声明前面，用于生成代码文档，这种注释可以被工具提取并生成 API 文档，如 JavaDoc。
    /**
     * 这是一个文档注释示例
     * 它通常包含有关类、方法或字段的详细信息
     */
    public class MyClass {
        // 类的成员和方法
    }
```

> 文档注释的格式通常包含一些特定的标签，如 `@param` 用于描述方法参数，`@return` 用于描述返回值，`@throws` 用于描述可能抛出的异常等等，这些标签有助于生成清晰的 API 文档，以便其他开发者能够更好地理解和使用你的代码。

## Java 空行

空白行或者有注释的行，Java 编译器都会忽略掉。
继承
--

在 Java 中，一个类可以由其他类派生。如果你要创建一个类，而且已经存在一个类具有你所需要的属性或方法，那么你可以将新创建的类继承该类。

利用继承的方法，可以重用已存在类的方法和属性，而不用重写这些代码。被继承的类称为超类（super class），派生类称为子类（sub class）。
接口
--

在 Java 中，接口可理解为对象间相互通信的协议。接口在继承中扮演着很重要的角色。

接口只定义派生要用到的方法，但是方法的具体实现完全取决于派生类。
Java 源程序与编译型运行区别

----------------

如下图所示：

![![][img-2](http://www.runoob.com/wp-content/uploads/2013/12/ZSSDMld.png)
