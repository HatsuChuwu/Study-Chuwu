from django.shortcuts import render, redirect
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.models import User
from django.contrib import messages
from django.urls import reverse

'''
注册视图方法
'''
def register(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        if User.objects.filter(username=username).exists():
            messages.error(request, '用户名已存在')
            return render(request, 'register.html', {'error': '用户名已存在'})
        user = User.objects.create_user(username=username, password=password)
        login(request, user)
        return redirect('home')
    return render(request, 'register.html')

'''
登录视图方法
'''
def login_view(request):
    logout_message = request.GET.get('logout', False)
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        user = authenticate(request, username=username, password=password)
        if user is not None:
            login(request, user)
            return redirect('home')
        else:
            messages.error(request, '用户名或密码错误')
            return render(request, 'login.html', {'error': '用户名或密码错误'})
    return render(request, 'login.html', {'logout_message': logout_message})

'''
主页视图方法
'''
def home(request):
    if request.user.is_authenticated:
        users = User.objects.all()
        return render(request, 'home.html', {'username': request.user.username, 'users': users})
    else:
        return redirect('login')

'''
注销视图方法
'''
def user_logout(request):
    logout(request)
    messages.success(request, '您已成功注销')
    login_url = reverse('login') + '?logout=True'
    return redirect(login_url)
