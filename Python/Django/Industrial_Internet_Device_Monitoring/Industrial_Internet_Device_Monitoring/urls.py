from django.contrib import admin
from django.urls import path, include
from django.shortcuts import redirect  # 从 django.shortcuts 导入 redirect
from django.views.generic import RedirectView

urlpatterns = [
    path('admin/', admin.site.urls),  # Django 默认的管理后台
    path('', RedirectView.as_view(url='/user_login/')),  # 根路径重定向到用户登录页面
    path('', include('IIDM.urls')),  # 包含 IIDM 应用的路由
]