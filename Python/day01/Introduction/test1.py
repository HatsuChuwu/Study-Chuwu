'''
score = int(input("请输入成绩: "))
if score > 60:
    print("及格")
else:
    print("不及格")
'''

score = int(input("请输入成绩: "))
if score > 100 or score < 0:
    print("成绩输入有误")
else:
    if score > 60:
        print("及格")
    else:
        print("不及格")