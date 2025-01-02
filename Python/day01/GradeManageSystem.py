grades = []  # 定义一个空的成绩列表

# 添加成绩的函数
def add_grade(student_name, grade):
    grades.append((student_name, grade))
    """
    外层括号：
    append()是列表的一个方法，用于在列表末尾添加一个元素。
    这里的外层括号是调用append()方法所必需的，它表示这是一个方法调用操作。
    内层括号：
    内层括号(student_name, grade)用于创建一个元组（tuple）。
    在 Python 中，元组是用圆括号括起来的一组值。
    这里将student_name和grade放在一个元组中，然后将这个元组作为一个整体添加到grades列表中。
    """
    print("已添加" + student_name + "的成绩: " + str(grade))

# 显示成绩的函数
    """
    这个函数的功能是展示当前已经记录在grades列表中的所有学生成绩信息。
    首先，通过条件判断语句检查grades列表是否为空。
    如果grades列表为空，意味着目前还没有学生成绩被记录下来，此时函数会打印出一条提示信息告知用户“没有学生的成绩记录”，然后直接返回，不再执行后续展示成绩的代码，因为没有数据可供展示。
    如果grades列表不为空，就先打印出一个表头“学生成绩: ”，用于提示接下来展示的内容是学生成绩信息。
    接着，使用for循环遍历grades列表中的每个元素。由于列表中的每个元素都是一个包含学生姓名和成绩的元组，所以在循环中可以使用解包的方式，将元组中的学生姓名赋值给student_name变量，成绩赋值给grade变量。
    在每次循环中，打印出学生姓名和成绩，中间用“：”分隔，这样就可以清晰地展示出每个学生及其对应的成绩了。
    """
def display_grades():
    if not grades:
        print("没有学生的成绩记录")
        return
    print("学生成绩: ")
    for student_name, grade in grades:
        print(student_name + "：" + str(grade))

# 计算平均成绩的函数
    """
    此函数用于计算所有已记录学生成绩的平均值。
    第一步，同样先通过条件判断检查grades列表是否为空。
    因为如果没有成绩记录，是无法进行平均成绩计算的，所以当grades列表为空时，函数会打印出“没有学生的成绩记录”提示信息，然后直接返回，终止函数执行。
    若grades列表不为空，接下来要计算总成绩。这里使用了生成器表达式(grade for _, grade in grades)，它的作用是从grades列表的每个元组元素中提取成绩部分（忽略姓名部分，用下划线_表示不使用该变量），然后将这些成绩传递给sum函数进行求和，得到总成绩total。
    最后，用总成绩除以学生人数（通过len(grades)获取成绩记录的条数，也就是学生人数），得到平均成绩average，并将平均成绩以字符串形式打印出来，方便用户查看平均成绩的具体数值。
    """
def averageGrades():
    if not grades:
        print("没有学生的成绩记录")
        return
    # 使用生成器表达式从grades列表中提取成绩，并计算总成绩
    total = sum(grade for _, grade in grades)
    average = total / len(grades)
    print("平均成绩: " + str(average))

# 查看最高分和最低分
    """
    本函数旨在查找并显示已记录成绩中的最高分和最低分对应的学生信息（包含姓名和成绩）。
    首先进行条件判断，检查grades列表是否为空。
    若为空，说明没有成绩数据可用于查找最高和最低分，此时函数会打印“没有学生的成绩记录”提示信息，并直接返回，不再执行后续查找操作。
    当grades列表不为空时，使用Python的内置函数max来查找最高分对应的学生信息。其中，key参数使用了lambda表达式(lambda x: x[1])，这个表达式的作用是告诉max函数按照元组中的第二个元素（也就是成绩）来比较大小，从而找出成绩最高的那个元组（即包含学生姓名和最高成绩的元组），并将其赋值给highest变量。
    同样地，使用min函数结合相同的key参数形式的lambda表达式来查找成绩最低的学生信息，找到的结果赋值给lowest变量。
    最后，分别打印出最高分对应的学生信息（highest）和最低分对应的学生信息（lowest），方便用户查看成绩的极值情况。
    """
def find_high_low():
    if not grades:
        print("没有学生的成绩记录")
        return
    highest = max(grades, key=lambda x: x[1])
    # 使用max函数找到成绩最高的学生信息，key参数指定按照元组中的第二个元素（成绩）进行比较
    lowest = min(grades, key=lambda x: x[1])
    # 使用min函数找到成绩最低的学生信息，key参数指定按照元组中的第二个元素（成绩）进行比较
    print(highest)
    print(lowest)

# 删除学生信息的函数
"""
#使用列表推导式
def delete_student(student_name):
    if not grades:
        print("没有学生的成绩记录")
        return
  
    # 使用列表推导式查找学生索引，如果找到则返回索引，否则返回 -1
    student_index = next((i for i, (name, _) in enumerate(grades) if name == student_name), -1)
  
    if student_index == -1:
        print("未找到该学生的信息")
        return
  
    # 删除学生信息
    del grades[student_index]
    print("已删除" + student_name + "的信息")
    
def update_grade(student_name, new_grade):
    if not grades:
        print("没有学生的成绩记录")
        return
  
    # 使用列表推导式查找学生索引，如果找到则返回索引，否则返回 -1
    student_index = next((i for i, (name, _) in enumerate(grades) if name == student_name), -1)
  
    if student_index == -1:
        print("未找到该学生的信息")
        return
  
    # 更新学生成绩
    grades[student_index] = (student_name, new_grade)
    print("已更新" + student_name + "的成绩为: " + str(new_grade))
"""

"""
#使用生成器表达式
def delete_student(student_name):
    if not grades:
        print("没有学生的成绩记录")
        return
  
    # 使用生成器表达式和 next() 函数查找学生索引
    student_index = next((i for i, (name, _) in enumerate(grades) if name == student_name), -1)
  
    if student_index == -1:
        print("未找到该学生的信息")
        return
  
    # 删除学生信息
    del grades[student_index]
    print("已删除" + student_name + "的信息")

def update_grade(student_name, new_grade):
    if not grades:
        print("没有学生的成绩记录")
        return
  
    # 使用生成器表达式和 next() 函数查找学生索引
    student_index = next((i for i, (name, _) in enumerate(grades) if name == student_name), -1)
  
    if student_index == -1:
        print("未找到该学生的信息")
        return
  
    # 更新学生成绩
    grades[student_index] = (student_name, new_grade)
    print("已更新" + student_name + "的成绩为: " + str(new_grade))
"""

def delete_student(student_name):
    if not grades:  # 检查是否有学生成绩记录
        print("没有学生的成绩记录")
        return
    # 查找学生信息是否存在
    student_index = -1  # 初始化学生索引为-1,表示未找到
    for i, (name, _) in enumerate(grades):  # 遍历grades列表
        if name == student_name:    # 如果学生名字匹配
            student_index = i   # 找到后立即退出循环
            break
    if student_index == -1:
        print("未找到该学生的信息")   # 如果 student_index 仍然是初始值 -1,表示未找到学生信息
        return
    # 删除学生信息
    del grades[student_index]   # 根据找到的学生索引,从 grades 列表中删除该学生记录
    print("已删除" + student_name + "的信息")

# 更改学生成绩的函数

def update_grade(student_name, new_grade):
    if not grades:  # 检查是否有学生成绩记录
        print("没有学生的成绩记录")
        return
    # 查找学生信息是否存在
    student_index = -1
    for i, (name, _) in enumerate(grades):  # 遍历成绩列表，获取每个学生的名字和成绩
        if name == student_name:    # 如果找到匹配的学生名字
            student_index = i    # 记录该学生的索引
            break
    if student_index == -1:  # 如果学生索引仍为-1，表示未找到学生
        print("未找到该学生的信息")  # 输出未找到的信息
        return
    # 更新学生成绩
    grades[student_index] = (student_name, new_grade)    # 更新找到的学生的成绩
    print("已更新" + student_name + "的成绩为: " + str(new_grade))  # 输出更新后的成绩信息

def main():
    """
    main函数作为整个学生成绩管理系统的主控制函数，通过一个无限循环来持续展示系统的操作菜单，并根据用户的选择调用相应的功能函数来实现具体的操作。
    在循环中，首先打印出系统的操作菜单，包含添加学生成绩、显示所有成绩、计算平均成绩、查找最高成绩和最低成绩以及退出这几个选项，方便用户选择要执行的操作。
    """
    while True:
        print("\n欢迎使用学生成绩管理系统")
        print("1. 添加学生成绩")
        print("2. 显示所有成绩")
        print("3. 计算平均成绩")
        print("4. 查找最高成绩和最低成绩")
        print("5. 删除学生信息")
        print("6. 更改学生成绩")
        print("7. 退出")

        choice = input("请选择操作(1-7) ")
        if choice == '1':
            name = input("请输入学生姓名: ")
            grade = int(input("请输入学生成绩: "))
            add_grade(name, grade)
        elif choice == '2':
            display_grades()
        elif choice == '3':
            averageGrades()
        elif choice == '4':
            find_high_low()
        elif choice == '5':
            name = input("请输入要删除的学生姓名: ")
            delete_student(name)
        elif choice == '6':
            name = input("请输入要更改成绩的学生姓名: ")
            new_grade = int(input("请输入新的成绩: "))
            update_grade(name, new_grade)
        elif choice == '7':
            print("感谢使用学生管理系统，再见！！！")
            break
        else:
            print("无效的选择，请输入1到7之间的数字。")

if __name__ == '__main__':
    # 当模块作为主程序运行时（即直接执行该脚本文件），__name__变量的值会被设置为'__main__'
    # 通过这个条件判断，可以确保main函数只在脚本直接运行时被调用，而在被导入到其他模块时不会被调用
    main()