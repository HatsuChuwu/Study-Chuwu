# DjangoProject/urls.py
from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
    path('admin/', admin.site.urls),  # 管理后台路径
    path('accounts/', include('accounts.urls')),  # 账户路径
]

# 配置静态文件的URL模式
if settings.DEBUG:
    urlpatterns += static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)
