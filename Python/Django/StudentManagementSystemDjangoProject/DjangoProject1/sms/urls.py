# sms/urls.py

from django.urls import path
from sms import views

urlpatterns = [
    path('admin_register/', views.admin_register, name='admin_register'),
    path('admin_login/', views.admin_login, name='admin_login'),
    path('user_register/', views.user_register, name='user_register'),
    path('user_login/', views.user_login, name='user_login'),
    path('dashboard/', views.dashboard, name='dashboard'),
    path('student_list/', views.student_list, name='student_list'),
    path('student_create/', views.student_create, name='student_create'),
    path('student_update/<int:stuId>/', views.student_update, name='student_update'),
    path('student_delete/<int:stuId>/', views.student_delete, name='student_delete'),
    path('user_list/', views.user_list, name='user_list'),  # 普通用户列表
    path('user_create/', views.user_create, name='user_create'),  # 创建普通用户
    path('user_update/<int:userId>/', views.user_update, name='user_update'),  # 更新普通用户
    path('user_delete/<int:userId>/', views.user_delete, name='user_delete'),  # 删除普通用户
]