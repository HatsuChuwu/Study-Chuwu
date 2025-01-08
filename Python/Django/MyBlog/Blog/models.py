from django.db import models
from django.contrib.auth.models import User

# 自定义模型管理器
class PostManager(models.Manager):
    def get_posts_by_user(self, user):
        """获取某个用户的所有文章"""
        return self.filter(author=user)

class Post(models.Model):
    title = models.CharField(max_length=200, null=False)
    content = models.TextField()
    author = models.ForeignKey(User, on_delete=models.CASCADE)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    objects = PostManager()  # 使用自定义管理器

    def __str__(self):
        return self.title

    class Meta:
        ordering = ['-created_at']