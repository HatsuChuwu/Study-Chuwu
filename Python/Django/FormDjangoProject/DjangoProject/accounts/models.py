from django.db import models

class User(models.Model):  # 定义一个User类，继承自models.Model
    username = models.CharField(max_length=20, unique=True)  # 定义一个username字段，类型为CharField，最大长度为20，唯一
    password = models.CharField(max_length=128)  # 定义一个password字段，类型为CharField，最大长度为128

    class Meta:
        managed = False  # 不生成数据库表
        db_table = "user"  # 指定数据库表名


