from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name='home'),  # 主页路由
    path('login/', views.login, name='login'),  # 登录路由
    path('register/', views.register, name='register'),  # 注册路由
    path('logout/', views.user_logout, name='logout'),  # 注销路由
]

