'''
类的定义:

使用 class 关键字定义类
类名通常采用驼峰命名法,如 MyClass
类中可以定义属性和方法
类的属性:

类属性:直接在类中定义的属性,所有实例共享
实例属性:在 __init__ 方法中定义的属性,每个实例独有
类的方法:

实例方法:第一个参数必须是 self,代表当前实例
类方法:第一个参数必须是 cls,代表当前类
静态方法:不需要 self 或 cls 参数
构造方法 __init__:

在创建实例时自动调用
用于初始化实例属性

继承:

子类可以继承父类的属性和方法
可以重写父类方法
可以调用父类方法

多态:

不同类的对象对同一消息作出不同的响应
体现在子类重写父类方法
总的来说,Python 的类机制提供了面向对象编程的基本功能,包括封装、继承和多态等特性,可以帮助我们更好地组织和管理代码。
'''


# 类
class People:
    """
    People类用于表示人的基本信息，包含姓名和年龄相关属性及行为。
    这里定义了两个类属性name和age，并设置了默认值，
    当创建类的实例时，如果没有对相应实例属性赋值，就会使用这些默认值。
    """
    name = '某某'
    age = 0  # 定义内置的属性，如果对象不赋值的话，默认就为这个值

    def __init__(self, n, a):
        """
        __init__方法是类的构造函数，用于初始化实例的属性。
        接收两个参数n和a，分别用来给实例的name和age属性赋值。
        """
        self.name = n
        self.age = a

    def speak(self):
        """
        speak方法用于输出实例所代表的人表述自己年龄的语句，
        将实例的name和age属性拼接成相应的字符串并打印出来。
        """
        print(str(self.name) + "说我今年" + str(self.age) + "岁")

    def eat(self):
        """
        eat方法用于输出实例所代表的人正在吃饭的语句，
        打印出包含实例name属性的正在吃饭的相关描述。
        """
        print(str(self.name) + "在吃饭")

p = People("张三", 20)
# 创建People类的一个实例p，传入姓名"张三"和年龄20作为参数，用于初始化实例的name和age属性。
p.speak()
# 调用实例p的speak方法，输出"张三说我今年20岁"。
p.eat()
# 调用实例p的eat方法，输出"张三在吃饭"。
a = People("李四", 30)
# 创建People类的另一个实例a，传入姓名"李四"和年龄30作为参数，用于初始化实例的name和age属性。
a.speak()
# 调用实例a的speak方法，输出"李四说我今年30岁"。

# 继承
class Student(People):
    """
    Student类继承自People类，表示学生这一特定人群，
    除了继承People类的属性和方法外，还新增了grade属性用于表示学生所在年级。
    """
    grade = ""
    # 定义类属性grade，初始值为空字符串，用于表示学生的年级，后续可通过实例化时传入具体值来修改。

    def __init__(self, n, a, g):
        """
        __init__方法是Student类的构造函数，用于初始化Student实例的属性。
        首先调用父类People的__init__方法，传入n和a参数来初始化从父类继承的name和age属性，
        然后使用参数g来初始化实例自身的grade属性。
        """
        super().__init__(n, a)  # 调用父类的__init__方法来初始化name和age
        self.grade = g  #这行代码会根据传入的参数g给实例属性self.grade赋值

    '''
    如果写成People.__init__(self, n, a)，这实际上是直接调用People类的__init__方法，
    但是这种方式没有考虑到Python的多重继承情况。
    在多重继承中，super()会按照方法解析顺序（MRO）来调用父类方法，
    而直接使用People.__init__(self, n, a)可能会导致方法调用顺序错误，进而引发一些难以排查的问题。
    '''
    def stuSpeak(self):
        """
        stuSpeak方法用于输出实例所代表的学生当前所在年级的信息，
        将实例的grade属性拼接成相应的字符串并打印出来。
        """
        print("今年" + str(self.grade))

# 创建Student实例时，需要传递name、age和grade参数
stu1 = Student("王五", 18, "大一")
# 创建Student类的实例stu1，传入姓名"王五"、年龄18和年级"大一"作为参数，分别初始化实例的name、age和grade属性。
stu1.speak()
# 调用实例stu1的speak方法，输出"王五说我今年18岁"。
stu1.stuSpeak()
# 调用实例stu1的stuSpeak方法，输出"今年大一"。

stu2 = Student("赵六", 19, "大一")
# 创建Student类的另一个实例stu2，传入姓名"赵六"、年龄19和年级"大一"作为参数，分别初始化实例的name、age和grade属性。
stu2.speak()
# 调用实例stu2的speak方法，输出"赵六说我今年19岁"。
stu2.stuSpeak()
# 调用实例stu2的stuSpeak方法，输出"今年大一"。