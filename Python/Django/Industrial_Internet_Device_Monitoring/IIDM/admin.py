from django.contrib import admin
from .models import IndustryUser, IndustryAdmin, IndustryDevice

# 注册模型
admin.site.register(IndustryUser)
admin.site.register(IndustryAdmin)
admin.site.register(IndustryDevice)