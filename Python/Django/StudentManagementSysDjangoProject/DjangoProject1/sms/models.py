# sms/models.py

from django.db import models

class Admin(models.Model):
    adminName = models.CharField(max_length=100)
    adminPassword = models.CharField(max_length=100)
    is_admin = models.BooleanField(default=True)  # 标记是否为管理员
    class Meta:
        db_table = 'admin'

class User(models.Model):
    userName = models.CharField(max_length=100)
    userPassword = models.CharField(max_length=100)
    is_admin = models.BooleanField(default=False)  # 标记是否为普通用户
    class Meta:
        db_table = 'user'

class Student(models.Model):
    stuId = models.IntegerField(primary_key=True)
    stuName = models.CharField(max_length=100)
    stuClass = models.CharField(max_length=100)
    stuIntro = models.TextField()
    class Meta:
        db_table = 'student'