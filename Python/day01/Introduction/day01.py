#keyword.kwlist 保留字
#['False', 'None', 'True', 'and', 'as', 'assert', 'break', 'class', 'continue', 'def', 'del', 'elif', 'else', 'except', 'finally', 'for', 'from', 'global', 'if', 'import', 'in', 'is', 'lambda', 'nonlocal', 'not', 'or', 'pass', 'raise', 'return', 'try', 'while', 'with', 'yield']

# 第一个注释
# 第二个注释

'''
第三注释
第四注释
'''

"""
第五注释
第六注释
"""

print("Hello, Python!")

'''
True:
    print ("Answer")
    print ("True")
else:
    print ("Answer")
  print ("False")    # 缩进不一致，会导致运行错误
'''

'''
等待用户输入
执行下面的程序在按回车键后就会等待用户输入：
n = input("输入:")
print(n)

n = int(input("请输入:"))
print(n + 1)
'''

'''
str = '123456789' #
print(str)  # 输出字符串
print(str[0:-1])  # 输出第一个到倒数第二个的所有字符
print(str[0])  # 输出字符串第一个字符
print(str[2:5])  # 输出从第三个开始到第六个的字符（不包含）
print(str[2:])  # 输出从第三个开始后的所有字符
print(str[1:5:2])  # 输出从第二个开始到第五个且每隔一个的字符（步长为2）
print(str * 2)  # 输出字符串两次
print(str + '你好')  # 连接字符串
print('------------------------------')
print('hello\nrunoob')  # 使用反斜杠(\)+n转义特殊字符
print(r'hello\nrunoob')  # 在字符串前面添加一个 r，表示原始字符串，不会发生转义
'''

'''
counter = 100          # 整型变量
miles   = 1000.0       # 浮点型变量
name    = "runoob"     # 字符串
print (counter)
print (miles)
print (name)
'''

'''
列表（C语言的数组）
语法格式：变量[头下标:尾下标]
索引值以 0 为开始值，-1 为从末尾的开始位置。
list = [ 'abcd', 786 , 2.23, 'runoob', 70.2 ]  # 定义一个列表
tinylist = [123, 'runoob']
print (list)            # 打印整个列表
print (list[0])         # 打印列表的第一个元素
print (list[1:3])       # 打印列表第二到第四个元素（不包含第四个元素）
print (list[2:])        # 打印列表从第三个元素开始到末尾
print (tinylist * 2)    # 打印tinylist列表两次
print (list + tinylist)  # 打印两个列表拼接在一起的结果
'''

'''
元组
元组（tuple）与列表类似，不同之处在于元组的元素不能修改。元组写在小括号 () 里，元素之间用逗号隔开。
元组中的元素类型也可以不相同：
tuple = ( 'abcd', 786 , 2.23, 'runoob', 70.2  )
tinytuple = (123, 'runoob')
print (tuple)             # 输出完整元组
print (tuple[0])          # 输出元组的第一个元素
print (tuple[1:3])        # 输出从第二个元素开始到第三个元素
print (tuple[2:])         # 输出从第三个元素开始的所有元素
print (tinytuple * 2)     # 输出两次元组
print (tuple + tinytuple) # 连接元组
'''

'''
Set（集合）
Python 中的集合（Set）是一种无序、可变的数据类型，用于存储唯一的元素。
集合中的元素不会重复，并且可以进行交集、并集、差集等常见的集合操作。
在 Python 中，集合使用大括号 {} 表示，元素之间用逗号 , 分隔。
另外，也可以使用 set() 函数创建集合。

注意：创建一个空集合必须用 set() 而不是 { }，因为 { } 是用来创建一个空字典。

创建格式：
parame = {value01,value02,...}
或者
set(value)

sites = {'Google', 'Taobao', 'Runoob', 'Facebook', 'Zhihu', 'Baidu'}
print(sites)   # 输出集合，重复的元素被自动去掉

# 成员测试
if 'Runoob' in sites :
    print('Runoob 在集合中')
else :
    print('Runoob 不在集合中')

# set可以进行集合运算
a = set('abracadabra')
b = set('alacazam')
print(a)
print(a - b)     # a 和 b 的差集
print(a | b)     # a 和 b 的并集
print(a & b)     # a 和 b 的交集
print(a ^ b)     # a 和 b 中不同时存在的元素
'''

'''
Dictionary（字典）
字典（dictionary）是Python中另一个非常有用的内置数据类型。
列表是有序的对象集合，字典是无序的对象集合。两者之间的区别在于：字典当中的元素是通过键来存取的，而不是通过偏移存取。
字典是一种映射类型，字典用 { } 标识，它是一个无序的 键(key) : 值(value) 的集合。
键(key)必须使用不可变类型。
在同一个字典中，键(key)必须是唯一的。
#student = {'name':'张三','age':'18','school':'学院'}
#print(student['name'])
#print(list(student.keys()))  or  print(student.keys()) 打印字典的键
#print(student) 直接打印字典
dict = {}
dict['one'] = "1 - 菜鸟教程"
dict[2]     = "2 - 菜鸟工具"
tinydict = {'name': 'runoob','code':1, 'site': 'www.runoob.com'}
print (dict['one'])       # 输出键为 'one' 的值
print (dict[2])           # 输出键为 2 的值
print (tinydict)          # 输出完整的字典
print (tinydict.keys())   # 输出所有键
print (tinydict.values()) # 输出所有值
'''

'''
列表（List）
列表是有序的元素集合，可以包含不同类型的元素。列表使用方括号 [] 表示，元素之间用逗号 , 分隔。
示例：
列表 = []  # 空列表
列表 = [1, 2, 3]  # 包含整数的列表
列表 = ["apple", "banana", "cherry"]  # 包含字符串的列表
列表 = [1, "apple", True]  # 包含不同类型元素的列表

元组（Tuple）
元组是有序的元素集合，与列表类似，但元组是不可变的（即不能修改元组中的元素）。元组使用圆括号 () 表示，元素之间用逗号 , 分隔。
示例：
元祖 = ()  # 空元组
元祖 = (1, 2, 3)  # 包含整数的元组
元祖 = ("apple", "banana", "cherry")  # 包含字符串的元组
元祖 = (1, "apple", True)  # 包含不同类型元素的元组

字典（Dictionary）
字典是无序的键值对集合，其中键（Key）必须是唯一的，值（Value）可以是任意类型。字典使用花括号 {} 表示，键值对之间用冒号 : 分隔，键值对之间用逗号 , 分隔。
示例：
字典 = {}  # 空字典
字典 = {"name": "Alice", "age": 30}  # 包含字符串键和整数值的字典
字典 = {1: "one", 2: "two", 3: "three"}  # 包含整数键和字符串值的字典
字典 = {"name": "Alice", "age": 30, "city": "New York"}  # 包含不同类
'''