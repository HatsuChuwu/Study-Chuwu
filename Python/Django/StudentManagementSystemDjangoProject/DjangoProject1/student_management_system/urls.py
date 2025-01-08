from django.contrib import admin
from django.urls import path, include
from django.views.generic.base import RedirectView

urlpatterns = [
    # 配置 Django 管理后台的 URL，访问 /admin/ 可以进入管理后台
    path('admin/', admin.site.urls),
    # 配置根 URL 重定向，当访问网站根目录时，重定向到 /admin_login/ 页面
    path('', RedirectView.as_view(url='/admin_login/')),
    # 配置包含子应用 sms 的 URL，当访问以 / 开头的 URL 时，会调用 sms.urls 中的 URL 配置
    path('', include('sms.urls')),
]
