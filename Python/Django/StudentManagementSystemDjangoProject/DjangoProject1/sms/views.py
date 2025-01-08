from django.shortcuts import render, redirect
from .models import Admin, Student
from django.core.paginator import Paginator

# 管理员注册视图
def admin_register(request):
    # 如果请求方法是POST，表示提交了注册表单
    if request.method == 'POST':
        # 从POST请求中获取管理员名称和密码
        admin_name = request.POST.get('adminName')
        admin_password = request.POST.get('adminPassword')
        # 创建一个新的Admin对象并保存到数据库
        admin = Admin(adminName=admin_name, adminPassword=admin_password)
        admin.save()
        # 注册成功后重定向到管理员登录页面
        return redirect('admin_login')
    # 如果请求方法不是POST，则渲染注册页面
    return render(request, 'admin_register.html')

# 管理员登录视图
def admin_login(request):
    # 如果请求方法是POST，表示提交了登录表单
    if request.method == 'POST':
        # 从POST请求中获取管理员名称和密码
        admin_name = request.POST.get('adminName')
        admin_password = request.POST.get('adminPassword')
        try:
            # 尝试从数据库中获取匹配的管理员对象
            admin = Admin.objects.get(adminName=admin_name, adminPassword=admin_password)
            # 登录成功，将管理员ID存储在session中
            request.session['admin_id'] = admin.id
            # 重定向到学生列表页面
            return redirect('student_list')
        except Admin.DoesNotExist:
            # 如果管理员不存在，渲染登录页面并显示错误信息
            return render(request, 'admin_login.html', {'error': '用户名或密码错误'})
    # 如果请求方法不是POST，则渲染登录页面
    return render(request, 'admin_login.html')

# 学生列表视图
def student_list(request):
    # 检查管理员是否已登录
    if 'admin_id' not in request.session:
        # 如果未登录，重定向到管理员登录页面
        return redirect('admin_login')
    # 获取所有学生对象
    students = Student.objects.all()
    # 创建分页器，每页显示10条记录
    paginator = Paginator(students, 10)
    # 获取当前页码
    page_number = request.GET.get('page')
    # 获取当前页的学生对象
    page_obj = paginator.get_page(page_number)
    # 渲染学生列表页面，并传递当前页的学生对象
    return render(request, 'student_list.html', {'page_obj': page_obj})

# 创建学生视图
def student_create(request):
    # 检查管理员是否已登录
    if 'admin_id' not in request.session:
        # 如果未登录，重定向到管理员登录页面
        return redirect('admin_login')
    # 如果请求方法是POST，表示提交了创建学生表单
    if request.method == 'POST':
        # 从POST请求中获取学生信息
        stu_id = request.POST.get('stuId')
        stu_name = request.POST.get('stuName')
        stu_class = request.POST.get('stuClass')
        stu_intro = request.POST.get('stuIntro')
        # 创建一个新的Student对象并保存到数据库
        student = Student(stuId=stu_id, stuName=stu_name, stuClass=stu_class, stuIntro=stu_intro)
        student.save()
        # 创建成功后重定向到学生列表页面
        return redirect('student_list')
    # 如果请求方法不是POST，则渲染创建学生页面
    return render(request, 'student_create.html')

# 更新学生视图
def student_update(request, stuId):
    # 检查管理员是否已登录
    if 'admin_id' not in request.session:
        # 如果未登录，重定向到管理员登录页面
        return redirect('admin_login')
    # 获取指定学生ID的学生对象
    student = Student.objects.get(stuId=stuId)
    # 如果请求方法是POST，表示提交了更新学生表单
    if request.method == 'POST':
        # 从POST请求中获取学生信息
        stu_name = request.POST.get('stuName')
        stu_class = request.POST.get('stuClass')
        stu_intro = request.POST.get('stuIntro')
        # 更新学生对象的属性
        student.stuName = stu_name
        student.stuClass = stu_class
        student.stuIntro = stu_intro
        # 保存更新后的学生对象
        student.save()
        # 更新成功后重定向到学生列表页面
        return redirect('student_list')
    # 如果请求方法不是POST，则渲染更新学生页面，并传递学生对象
    return render(request, 'student_update.html', {'student': student})

# 删除学生视图
def student_delete(request, stuId):
    # 检查管理员是否已登录
    if 'admin_id' not in request.session:
        # 如果未登录，重定向到管理员登录页面
        return redirect('admin_login')
    # 获取指定学生ID的学生对象
    student = Student.objects.get(stuId=stuId)
    # 删除学生对象
    student.delete()
    # 删除成功后重定向到学生列表页面
    return redirect('student_list')
