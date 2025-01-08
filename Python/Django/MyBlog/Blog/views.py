from django.shortcuts import render, get_object_or_404, redirect
from django.core.paginator import Paginator
from django.contrib import messages
from django.contrib.auth.decorators import login_required
from .models import Post
from .forms import PostForm
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.core.files.storage import default_storage

# 文章列表
def post_list(request):
    """
    显示文章列表页面，分页显示所有文章。

    参数:
    - request: HttpRequest对象，包含用户请求的信息。

    返回:
    - HttpResponse对象，包含渲染后的文章列表页面。
    """
    # 获取所有文章，按创建时间倒序排列
    posts = Post.objects.all().order_by('-created_at')
    # 初始化分页器，每页显示5篇文章
    paginator = Paginator(posts, 5)
    # 获取当前页码
    page_number = request.GET.get('page')
    # 获取当前页码的文章对象
    page_obj = paginator.get_page(page_number)
    # 渲染文章列表页面
    return render(request, 'post_list.html', {'page_obj': page_obj})

# 文章详情
def post_detail(request, post_id):
    """
    显示文章详情页面。

    参数:
    - request: HttpRequest对象，包含用户请求的信息。
    - post_id: 文章ID，用于获取指定文章。

    返回:
    - HttpResponse对象，包含渲染后的文章详情页面。
    """
    # 获取指定文章对象，如果不存在则返回404错误
    post = get_object_or_404(Post, id=post_id)
    # 渲染文章详情页面
    return render(request, 'post_detail.html', {'post': post})

# 创建文章
@login_required
def post_create(request):
    """
    创建新文章。

    参数:
    - request: HttpRequest对象，包含用户请求的信息。

    返回:
    - HttpResponse对象，包含渲染后的文章创建表单页面或重定向到文章详情页面。
    """
    if request.method == 'POST':
        # 创建PostForm实例，用POST数据填充表单
        form = PostForm(request.POST)
        if form.is_valid():
            # 保存表单但不提交到数据库
            post = form.save(commit=False)
            # 设置文章作者为当前用户
            post.author = request.user
            # 保存文章到数据库
            post.save()
            # 显示成功消息
            messages.success(request, '文章已成功创建！')
            # 重定向到新创建的文章详情页面
            return redirect('post_detail', post_id=post.id)
    else:
        # 如果不是POST请求，则创建空表单
        form = PostForm()
    # 渲染文章创建表单页面
    return render(request, 'post_create.html', {'form': form})

# 编辑文章
@login_required
def post_update(request, post_id):
    """
    编辑指定文章。

    参数:
    - request: HttpRequest对象，包含用户请求的信息。
    - post_id: 文章ID，用于获取指定文章。

    返回:
    - HttpResponse对象，包含渲染后的文章编辑表单页面或重定向到文章详情页面。
    """
    # 获取指定文章对象，如果不存在则返回404错误
    post = get_object_or_404(Post, id=post_id)
    # 检查当前用户是否有权限编辑文章
    if post.author != request.user:
        # 如果没有权限，返回403错误页面
        return render(request, '403.html', status=403)
    if request.method == 'POST':
        # 创建PostForm实例，用POST数据填充表单并指定文章实例
        form = PostForm(request.POST, instance=post)
        if form.is_valid():
            # 保存编辑后的文章
            form.save()
            # 显示成功消息
            messages.success(request, '文章已成功更新！')
            # 重定向到编辑后的文章详情页面
            return redirect('post_detail', post_id=post.id)
    else:
        # 如果不是POST请求，创建表单并填充文章数据
        form = PostForm(instance=post)
    # 渲染文章编辑表单页面
    return render(request, 'post_update.html', {'form': form})

# 删除文章
@login_required
def post_delete(request, post_id):
    """
    删除指定文章。

    参数:
    - request: HttpRequest对象，包含用户请求的信息。
    - post_id: 文章ID，用于获取指定文章。

    返回:
    - HttpResponse对象，包含渲染后的文章删除确认页面或重定向到文章列表页面。
    """
    # 获取指定文章对象，如果不存在则返回404错误
    post = get_object_or_404(Post, id=post_id)
    # 检查当前用户是否有权限删除文章
    if post.author != request.user:
        # 如果没有权限，返回403错误页面
        return render(request, '403.html', status=403)
    if request.method == 'POST':
        # 删除文章
        post.delete()
        # 显示成功消息
        messages.success(request, '文章已成功删除！')
        # 重定向到文章列表页面
        return redirect('post_list')
    # 渲染文章删除确认页面
    return render(request, 'post_delete.html', {'post': post})

# 获取当前用户的所有文章
@login_required
def user_posts(request):
    """
    显示当前用户的所有文章。

    参数:
    - request: HttpRequest对象，包含用户请求的信息。

    返回:
    - HttpResponse对象，包含渲染后的用户文章列表页面。
    """
    # 使用自定义模型管理器获取当前用户的所有文章
    user_posts = Post.objects.get_posts_by_user(request.user)
    # 渲染用户文章列表页面
    return render(request, 'user_posts.html', {'user_posts': user_posts})

# 上传图片
@csrf_exempt
def upload_image(request):
    """
    处理图片上传请求。

    参数:
    - request: HttpRequest对象，包含用户请求的信息。

    返回:
    - JsonResponse对象，包含上传图片的位置或错误信息。
    """
    if request.method == 'POST' and request.FILES.get('file'):
        # 获取上传的图片文件
        upload = request.FILES['file']
        # 保存上传的图片文件
        file_name = default_storage.save(f'uploads/{upload.name}', upload)
        # 获取上传图片的URL
        file_url = default_storage.url(file_name)
        # 返回上传图片的位置
        return JsonResponse({'location': file_url})
    # 如果上传失败，返回错误信息
    return JsonResponse({'error': '上传失败'}, status=400)


# Blog/views.py
def post_list(request):
    """
    显示文章列表页面，分页显示所有文章。

    参数:
    - request: HttpRequest对象，包含用户请求的信息。

    返回:
    - HttpResponse对象，包含渲染后的文章列表页面。
    """
    print("post_list view is called!")  # 调试信息
    # 获取所有文章，按创建时间倒序排列
    posts = Post.objects.all().order_by('-created_at')
    # 初始化分页器，每页显示5篇文章
    paginator = Paginator(posts, 5)
    # 获取当前页码
    page_number = request.GET.get('page')
    # 获取当前页码的文章对象
    page_obj = paginator.get_page(page_number)
    # 渲染文章列表页面
    return render(request, 'post_list.html', {'page_obj': page_obj})
