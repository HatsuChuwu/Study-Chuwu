# MyBlog/urls.py
from django.contrib import admin
from django.urls import path, include
from django.views.generic import RedirectView
from django.contrib.auth import views as auth_views  # 导入 Django 内置的认证视图

# 定义项目的 URL 路由规则
urlpatterns = [
    path('admin/', admin.site.urls),  # 管理员界面的路由
    path('blog/', include('Blog.urls')),  # 包含 Blog 应用的 URL 路由
    path('', RedirectView.as_view(url='/blog/')),  # 将根路径重定向到 /blog/

    # 添加 Django 内置的登录和注销视图
    path('login/', auth_views.LoginView.as_view(template_name='registration/login.html'), name='login'),
    path('logout/', auth_views.LogoutView.as_view(), name='logout'),
]
