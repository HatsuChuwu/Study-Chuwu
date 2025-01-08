from django.urls import path
from . import views

# 定义URL模式，将URL映射到视图函数
urlpatterns = [
    # 主页，显示帖子列表
    path('', views.post_list, name='post_list'),
    # 帖子详情页，通过帖子ID获取详细信息
    path('<int:post_id>/', views.post_detail, name='post_detail'),
    # 创建新帖子页面
    path('create/', views.post_create, name='post_create'),
    # 更新帖子页面，通过帖子ID确定要更新的帖子
    path('<int:post_id>/update/', views.post_update, name='post_update'),
    # 删除帖子页面，通过帖子ID确定要删除的帖子
    path('<int:post_id>/delete/', views.post_delete, name='post_delete'),
    # 用户帖子列表页面，显示当前用户的所有帖子
    path('my-posts/', views.user_posts, name='user_posts'),  # 新增路由
]
