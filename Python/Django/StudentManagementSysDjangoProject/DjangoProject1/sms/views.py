# sms/views.py
from django.db.models import Count  # 导入 Count
from django.shortcuts import render, redirect
from .models import Admin, User, Student
from django.core.paginator import Paginator

# 检查用户是否是管理员
def is_admin(request):
    return 'admin_id' in request.session and request.session['is_admin']

# 检查用户是否已登录（管理员或普通用户）
def is_logged_in(request):
    return 'admin_id' in request.session or 'user_id' in request.session

# 管理员注册视图
def admin_register(request):
    if request.method == 'POST':
        admin_name = request.POST.get('adminName')
        admin_password = request.POST.get('adminPassword')
        admin = Admin(adminName=admin_name, adminPassword=admin_password, is_admin=True)
        admin.save()
        return redirect('admin_login')
    return render(request, 'admin_register.html')

# 普通用户注册视图
def user_register(request):
    if request.method == 'POST':
        user_name = request.POST.get('userName')
        user_password = request.POST.get('userPassword')
        user = User(userName=user_name, userPassword=user_password, is_admin=False)
        user.save()
        return redirect('user_login')
    return render(request, 'user_register.html')

# 管理员登录视图
def admin_login(request):
    if request.method == 'POST':
        admin_name = request.POST.get('adminName')
        admin_password = request.POST.get('adminPassword')
        try:
            admin = Admin.objects.get(adminName=admin_name, adminPassword=admin_password)
            request.session['admin_id'] = admin.id
            request.session['is_admin'] = True  # 标记为管理员
            return redirect('student_list')  # 登录成功后跳转到学生列表页面
        except Admin.DoesNotExist:
            return render(request, 'admin_login.html', {'error': '用户名或密码错误'})
    return render(request, 'admin_login.html')

# 普通用户登录视图
def user_login(request):
    if request.method == 'POST':
        user_name = request.POST.get('userName')
        user_password = request.POST.get('userPassword')
        try:
            user = User.objects.get(userName=user_name, userPassword=user_password)
            request.session['user_id'] = user.id
            request.session['is_admin'] = False  # 标记为普通用户
            return redirect('student_list')  # 登录成功后跳转到学生列表页面
        except User.DoesNotExist:
            return render(request, 'user_login.html', {'error': '用户名或密码错误'})
    return render(request, 'user_login.html')


def dashboard(request):
    # 检查用户是否登录
    if 'admin_id' not in request.session and 'user_id' not in request.session:
        return redirect('admin_login')  # 如果未登录，重定向到管理员登录页面

    # 学生总数
    total_students = Student.objects.count()

    # 班级分布
    class_distribution = Student.objects.values('stuClass').annotate(count=Count('stuId')).order_by('stuClass')

    # 将班级名称转换为整数并排序
    class_distribution = sorted(
        class_distribution,
        key=lambda x: int(x['stuClass'])  # 按班级名称的整数值排序
    )

    # 提取排序后的班级名称和学生数量
    class_names = [str(item['stuClass']) for item in class_distribution]  # 转换为字符串
    class_counts = [item['count'] for item in class_distribution]

    # 最近添加的学生
    recent_students = Student.objects.order_by('-stuId')[:5]  # 获取最近添加的5名学生

    # 渲染仪表盘页面，并传递数据
    return render(request, 'dashboard.html', {
        'total_students': total_students,
        'class_names': class_names,
        'class_counts': class_counts,
        'recent_students': recent_students
    })

# 学生列表视图（管理员和普通用户均可访问）
def student_list(request):
    if not is_logged_in(request):
        return redirect('admin_login')  # 如果未登录，重定向到管理员登录页面

    students = Student.objects.all()
    paginator = Paginator(students, 10)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)
    return render(request, 'student_list.html', {'page_obj': page_obj})

# 创建学生视图（管理员和普通用户均可访问）
def student_create(request):
    if not is_logged_in(request):
        return redirect('admin_login')  # 如果未登录，重定向到管理员登录页面

    if request.method == 'POST':
        stu_id = request.POST.get('stuId')
        stu_name = request.POST.get('stuName')
        stu_class = request.POST.get('stuClass')
        stu_intro = request.POST.get('stuIntro')
        student = Student(stuId=stu_id, stuName=stu_name, stuClass=stu_class, stuIntro=stu_intro)
        student.save()
        return redirect('student_list')
    return render(request, 'student_create.html')

# 更新学生视图（管理员和普通用户均可访问）
def student_update(request, stuId):
    if not is_logged_in(request):
        return redirect('admin_login')  # 如果未登录，重定向到管理员登录页面

    student = Student.objects.get(stuId=stuId)
    if request.method == 'POST':
        student.stuName = request.POST.get('stuName')
        student.stuClass = request.POST.get('stuClass')
        student.stuIntro = request.POST.get('stuIntro')
        student.save()
        return redirect('student_list')
    return render(request, 'student_update.html', {'student': student})

# 删除学生视图（管理员和普通用户均可访问）
def student_delete(request, stuId):
    if not is_logged_in(request):
        return redirect('admin_login')  # 如果未登录，重定向到管理员登录页面

    student = Student.objects.get(stuId=stuId)
    student.delete()
    return redirect('student_list')

# 普通用户列表视图（仅管理员可访问）
def user_list(request):
    if not is_admin(request):
        return redirect('admin_login')  # 如果不是管理员，重定向到管理员登录页面

    users = User.objects.all()
    paginator = Paginator(users, 10)
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)
    return render(request, 'user_list.html', {'page_obj': page_obj})

# 创建普通用户视图（仅管理员可访问）
def user_create(request):
    if not is_admin(request):
        return redirect('admin_login')  # 如果不是管理员，重定向到管理员登录页面

    if request.method == 'POST':
        user_name = request.POST.get('userName')
        user_password = request.POST.get('userPassword')
        user = User(userName=user_name, userPassword=user_password, is_admin=False)
        user.save()
        return redirect('user_list')
    return render(request, 'user_create.html')

# 更新普通用户视图（仅管理员可访问）
def user_update(request, userId):
    if not is_admin(request):
        return redirect('admin_login')  # 如果不是管理员，重定向到管理员登录页面

    user = User.objects.get(id=userId)
    if request.method == 'POST':
        user.userName = request.POST.get('userName')
        user.userPassword = request.POST.get('userPassword')
        user.save()
        return redirect('user_list')
    return render(request, 'user_update.html', {'user': user})

# 删除普通用户视图（仅管理员可访问）
def user_delete(request, userId):
    if not is_admin(request):
        return redirect('admin_login')  # 如果不是管理员，重定向到管理员登录页面

    user = User.objects.get(id=userId)
    user.delete()
    return redirect('user_list')