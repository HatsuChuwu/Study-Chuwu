from django.contrib import admin
from django.urls import path, include
from django.views.generic import RedirectView
from . import views
from .views import update_user, delete_user

urlpatterns = [
    path('admin/', admin.site.urls),  # Django 默认的管理后台
    path('', RedirectView.as_view(url='/user_login/')),  # 根路径重定向到用户登录页面
    path('user_register/', views.user_register, name='user_register'),
    path('user_login/', views.user_login, name='user_login'),  # 确保登录页面的 URL 配置正确
    path('admin_login/', views.admin_login, name='admin_login'),
    path('user_logout/', views.user_logout, name='user_logout'),
    path('user_list/', views.user_list, name='user_list'),
    path('device_status/', views.device_status, name='device_status'),  # 确保设备状态页面的 URL 配置正确
    path('add_device/', views.add_device, name='add_device'),
    path('update_device/<int:device_id>/', views.update_device, name='update_device'),
    path('delete_device/<int:device_id>/', views.delete_device, name='delete_device'),
    path('dashboard/', views.dashboard, name='dashboard'),
    path('update_user/<int:user_id>/', update_user, name='update_user'),
    path('delete_user/<int:user_id>/', delete_user, name='delete_user'),
]