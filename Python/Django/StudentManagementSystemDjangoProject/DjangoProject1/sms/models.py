from django.db import models

class Admin(models.Model):
    adminName = models.CharField(max_length=50)
    adminPassword = models.CharField(max_length=50)
    class Meta:
        db_table = 'admin'

class Student(models.Model):
    stuId = models.IntegerField(primary_key=True)
    stuName = models.CharField(max_length=50)
    stuClass = models.CharField(max_length=50)
    stuIntro = models.TextField()
    class Meta:
        db_table = 'student'