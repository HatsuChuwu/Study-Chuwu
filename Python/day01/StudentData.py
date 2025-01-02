import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

np.random.seed(1030)    #随机种子

# 生成学生姓名
# 使用列表推导式生成学生姓名列表
# f-string用于格式化字符串，将学生编号（从1开始）与“学生”前缀组合
# range(num_students)生成从0到99的整数序列，用于表示学生编号
num_students = 100
names = [f'学生{i+1}' for i in range(num_students)]

# 生成年龄（10-22岁）
# np.random.randint(10, 23, size=num_students)生成一个包含num_students个随机整数的数组，范围在10到22之间（包括10和22）
ages = np.random.randint(10, 23, size=num_students)

# 生成性别
# np.random.choice生成一个包含num_students个随机选择的性别字符串的数组，从['男', '女']中选择
genders = np.random.choice(['男', '女'], size=num_students)

# 生成成绩
# np.random.randint生成一个包含num_students个随机整数的数组，范围在0到100之间（包括0和100）
chinese_score = np.random.randint(0, 101, size=num_students)
math_score = np.random.randint(0, 101, size=num_students)
english_score = np.random.randint(0, 101, size=num_students)

# 创建一个包含学生信息的字典
data = {
    '姓名': names,  # 将学生姓名列表赋值给'姓名'键
    '性别': genders,  # 将学生性别列表赋值给'性别'键
    'AGE': ages,  # 将学生年龄列表赋值给'年龄'键
    'CHS': chinese_score,  # 将学生语文成绩列表赋值给'语文'键
    'MAT': math_score,  # 将学生数学成绩列表赋值给'数学'键
    'ENG': english_score  # 将学生英语成绩列表赋值给'英语'键
}

# 使用pandas创建一个DataFrame对象，将data字典作为参数传递给DataFrame构造函数
df = pd.DataFrame(data)

# 打印DataFrame的前10行数据，使用head()方法获取前10行数据，并使用print()函数打印结果
print(df.head(10))

# 计算每门科目的平均成绩
average_score = df[['CHS', 'MAT', 'ENG']].mean()
print("平均成绩\n")
print(average_score)

'''
# 计算并打印每门科目的平均成绩
print("\n每门科目的平均成绩：")
print(f"语文（CHS）平均成绩: {df['CHS'].mean():.2f}")
print(f"数学（MAT）平均成绩: {df['MAT'].mean():.2f}")
print(f"英语（ENG）平均成绩: {df['ENG'].mean():.2f}")
'''

# 绘制图表
import matplotlib.pyplot as plt
plt.figure(figsize=(10, 5)) # 创建一个10x5的画布
# 绘制平均成绩柱状图，图表类型为柱状图，颜色为天蓝色、浅绿色和鲑鱼色
average_score.plot(kind='bar', color=['#66CCFF', 'lightgreen', 'salmon'])
plt.title("Average Score")  # 设置标题为"average score"
plt.xlabel("Subject")       # 设置x轴标签为"subject"
plt.ylabel("Score")         # 设置y轴标签为"score"
plt.xticks(rotation=45)     # 设置x轴刻度标签旋转45度
plt.grid(axis='y')          # 显示网格线，y轴方向
plt.show()                  # 显示图表