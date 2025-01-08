from django.urls import path
from sms import views  # 确保这里写的是 sms

# 定义 urlpatterns 列表，包含URL路径和对应的视图函数
urlpatterns = [
    # 管理员注册页面的URL路径
    path('admin_register/', views.admin_register, name='admin_register'),
    # 管理员登录页面的URL路径
    path('admin_login/', views.admin_login, name='admin_login'),
    # 学生列表页面的URL路径
    path('student_list/', views.student_list, name='student_list'),
    # 创建学生信息页面的URL路径
    path('student_create/', views.student_create, name='student_create'),
    # 更新学生信息页面的URL路径，<int:stuId>表示学生ID的参数
    path('student_update/<int:stuId>/', views.student_update, name='student_update'),
    # 删除学生信息页面的URL路径，<int:stuId>表示学生ID的参数
    path('student_delete/<int:stuId>/', views.student_delete, name='student_delete'),
]
