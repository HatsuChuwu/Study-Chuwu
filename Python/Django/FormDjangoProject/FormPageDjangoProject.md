

---

### **1. 创建 Django 项目**

- 使用 PyCharm 创建了一个 Django 项目，项目根目录为 `DjangoProject`。

- 项目结构如下：
  
  ```
  DjangoProject/
  ├── .venv/           # 虚拟环境目录
  ├── manage.py        # Django 管理脚本
  └── DjangoProject/   # 项目配置目录
      ├── __init__.py
      ├── settings.py
      ├── urls.py
      └── wsgi.py
  ├── templates
  ```

---

### **2. 配置数据库**

- 将默认的 SQLite 数据库切换为 MySQL。

- 修改 `settings.py` 中的 `DATABASES` 配置：
  
  ```python
  DATABASES = {
      'default': {
          'ENGINE': 'django.db.backends.mysql',
          'NAME': 'mydatabase',
          'USER': 'root',
          'PASSWORD': 'root',
          'HOST': '127.0.0.1',
          'PORT': '3306',
      }
  }
  ```

- 在 MySQL 中创建了数据库 `mydatabase`。

---

### **3. 安装 MySQL 客户端库**

- 安装了 `mysqlclient` 或 `pymysql`，以便 Django 能够连接 MySQL 数据库。
- ```
  pip install mysqlclient
  ```

---

### **4. 创建 Django 应用**

- 运行以下命令创建了一个 Django 应用 `accounts`：
  
  ```bash
  python manage.py startapp accounts
  ```

- 项目结构更新为：
  
  ```
  DjangoProject/
  ├── .venv/
  ├── manage.py
  ├── DjangoProject/
  │   ├── __init__.py
  │   ├── asgi.py
  │   ├── settings.py
  │   ├── urls.py
  │   └── wsgi.py
  └── accounts/        # 自定义应用
      ├── migrations/
      ├── __init__.py
      ├── admin.py
      ├── apps.py
      ├── models.py
      ├── tests.py
      └── views.py
  ├── templates
  ```

---

### **5. 配置 `INSTALLED_APPS`**

- 将 `accounts` 添加到 `settings.py` 的 `INSTALLED_APPS` 中：
  
  ```python
  INSTALLED_APPS = [
      'django.contrib.admin',          # 管理员站点
      'django.contrib.auth',           # 认证系统
      'django.contrib.contenttypes',   # 内容类型框架
      'django.contrib.sessions',       # 会话管理
      'django.contrib.messages',       # 消息框架
      'django.contrib.staticfiles',    # 静态文件管理
      'accounts',  # 你的自定义应用accounts
  ]
  ```

---

### **6. 配置 URL 路由**

- 在 `DjangoProject/urls.py` 中配置了 `admin` 路径：
  
  ```python
  from django.contrib import admin
  from django.urls import path
  
  urlpatterns = [
      path('admin/', admin.site.urls),  # 管理后台路径
  ]
  ```

---

### **7. 运行迁移**

- 运行以下命令，将 Django 的默认表结构迁移到 MySQL 数据库：
  
  ```bash
  python manage.py migrate
  ```

---

### **8. 创建超级用户**

- 运行以下命令创建了 Django 超级用户：
  
  ```bash
  python manage.py createsuperuser
  ```

- 设置了用户名、邮箱和密码。

---

### **9. 启动开发服务器**

- 运行以下命令启动开发服务器：
  
  ```bash
  python manage.py runserver
  ```

- 访问 `http://127.0.0.1:8000/`，看到了 Django 的默认欢迎页面。

---

### **10. 访问管理后台**

- 访问 `http://127.0.0.1:8000/admin/`，尝试登录管理后台。

- 由于 `urls.py` 中 `admin` 路径被注释，暂时无法访问管理后台。

- 取消注释后，重新配置了 `urls.py`：
  
  ```python
  from django.contrib import admin
  from django.urls import path
  
  urlpatterns = [
      path('admin/', admin.site.urls),  # 取消注释
  ]
  ```

---

11.视图构建完成

```python
from django.shortcuts import render, redirect
from django.urls import reverse  # 导入 reverse 函数
from .models import User

'''
注册视图方法
'''
def register(request):
    if request.method == 'POST':
        username = request.POST['username']  # 获取html表单提交的用户名
        password = request.POST['password']  # 获取html表单提交的密码
        user = User(username=username, password=password)
        user.save()
        return redirect('login')  # 注册成功，跳转到登录页面
    return render(request, 'register.html')  # 注册页面

'''
登录视图方法
'''
def login(request):
    logout_message = request.GET.get('logout', False)  # 检查是否有注销参数
    if request.method == 'POST':
        username = request.POST.get('username')  # 使用 .get() 避免 KeyError, 并提供默认值为空字符串
        password = request.POST.get('password')  # 使用 .get() 避免 KeyError, 并提供默认值为空字符串
        try:
            user = User.objects.get(username=username, password=password)  # 查询数据库
            request.session['username'] = user.username  # 将用户名存入 session
            return redirect('home')  # 登录成功，跳转到主页
        except User.DoesNotExist:
            # 登录失败，返回登录页面并显示错误信息
            return render(request, 'login.html', {'error': '用户名或密码错误'})
    # 如果请求方法不是 POST，直接渲染登录页面
    return render(request, 'login.html', {'logout_message': logout_message})

'''
主页视图方法
'''
def home(request):
    username = request.session.get('username')  # 从session中获取username
    if username:
        return render(request, 'home.html', {'username': username})  # 如果username存在，跳转到home页面
    else:
        return redirect('login')  # 如果username不存在，跳转到login页面

'''
注销视图方法
'''
def user_logout(request):
    if 'username' in request.session:  # 检查用户是否登录
        del request.session['username']  # 清除session中的用户名
    # 使用 reverse 生成带查询参数的 URL
    login_url = reverse('login') + '?logout=True'
    return redirect(login_url)  # 重定向到登录页面
```

12.主次路由编辑完毕

```python
#主路由
from django.contrib import admin
from django.urls import path, include


urlpatterns = [
    path('admin/', admin.site.urls),  # 管理后台路径
    path('accounts/', include('accounts.urls')),  # 账户路径
]
```

```python
#次路由

from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name='home'),  # 主页路由
    path('login/', views.login, name='login'),  # 登录路由
    path('register/', views.register, name='register'),  # 注册路由
    path('logout/', views.user_logout, name='logout'),  # 注销路由
]
```

13.注册，登录，home三个页面完成

```html
<!-- register -->

{% load static %}
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册</title>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://lf3-cdn-tos.bytecdntp.com/cdn/expire-1-M/twitter-bootstrap/4.6.1/css/bootstrap.min.css">
    <!-- 自定义 CSS 文件 -->
    <link rel="stylesheet" href="{% static 'css/styles.css' %}">
</head>
<body>
    <!-- 切换按钮 -->
    <div class="toggle-container">
        <div class="toggle-button" id="toggle-button"></div>
    </div>

    <!-- 注册表单 -->
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="form-container">
            <h1>用户注册</h1>
            <form action="{% url 'register' %}" method="post">
                {% csrf_token %}
                <div class="input-group">
                    <input type="text" name="username" class="form-control" placeholder="用户名" required>
                </div>
                <div class="input-group">
                    <input type="password" name="password" class="form-control" placeholder="密码" required>
                </div>
                <button type="submit" class="action-button">注册</button>
            </form>
            <p>已有账号？<a href="{% url 'login' %}">立即登录</a></p>
        </div>
    </div>

    <!-- 生成静态文件URL -->
    <script>
        const imageUrls = [
            "{% static 'images/bg1.png' %}",
            "{% static 'images/bg2.png' %}",
            "{% static 'images/bg3.png' %}",
            "{% static 'images/bg4.png' %}",
            "{% static 'images/bg5.png' %}"
        ];
    </script>
    <!-- 引入 JavaScript 文件 -->
    <script src="{% static 'js/toggle.js' %}"></script>
    <script src="{% static 'js/preload.js' %}"></script>
</body>
</html>


```

```html
`<!-- login -->`

{% load static %}
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登录</title>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://lf3-cdn-tos.bytecdntp.com/cdn/expire-1-M/twitter-bootstrap/4.6.1/css/bootstrap.min.css">
    <!-- 自定义 CSS 文件 -->
    <link rel="stylesheet" href="{% static 'css/styles.css' %}">
</head>
<body>
    <!-- 切换按钮 -->
    <div class="toggle-container">
        <div class="toggle-button" id="toggle-button"></div>
    </div>

    <!-- 登录表单 -->
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="form-container">
            <h1>用户登录</h1>
            {% if error %}
                <p style="color: red;">{{ error }}</p>
            {% endif %}
            {% if logout_message %}
                <p style="color: green;">您已成功注销。</p>
            {% endif %}
            <form action="{% url 'login' %}" method="post">
                {% csrf_token %}
                <div class="input-group">
                    <input type="text" name="username" class="form-control" placeholder="用户名" required>
                </div>
                <div class="input-group">
                    <input type="password" name="password" class="form-control" placeholder="密码" required>
                </div>
                <button type="submit" class="action-button">登录</button>
            </form>
            <p>没有账号？<a href="{% url 'register' %}">立即注册</a></p>
        </div>
    </div>

    <!-- 生成静态文件URL -->
    <script>
        const imageUrls = [
            "{% static 'images/bg1.png' %}",
            "{% static 'images/bg2.png' %}",
            "{% static 'images/bg3.png' %}",
            "{% static 'images/bg4.png' %}",
            "{% static 'images/bg5.png' %}"
        ];
    </script>
    <!-- 引入 JavaScript 文件 -->
    <script src="{% static 'js/toggle.js' %}"></script>
    <script src="{% static 'js/preload.js' %}"></script>
</body>
</html>

```

```html
<!-- home -->

{% load static %}
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>主页</title>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://lf3-cdn-tos.bytecdntp.com/cdn/expire-1-M/twitter-bootstrap/4.6.1/css/bootstrap.min.css">
    <!-- 自定义 CSS 文件 -->
    <link rel="stylesheet" href="{% static 'css/styles.css' %}">
</head>
<body>
    <!-- 切换按钮 -->
    <div class="toggle-container">
        <div class="toggle-button" id="toggle-button"></div>
    </div>

    <!-- 主页内容 -->
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="form-container">
            <h1>欢迎回来，{{ username }}！</h1>

            <!-- 用户列表表格 -->
            <h2>已注册用户列表</h2>
            <div class="table-responsive">  <!-- 响应式表格容器 -->
                <table class="table table-bordered table-striped table-hover">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">用户名</th>
                        </tr>
                    </thead>
                    <tbody>
                        {% for user in users %}
                        <tr>
                            <th scope="row">{{ forloop.counter }}</th>  <!-- 显示行号 -->
                            <td>{{ user.username }}</td>
                        </tr>
                        {% endfor %}
                    </tbody>
                </table>
            </div>

            <form action="{% url 'logout' %}" method="post">
                {% csrf_token %}
                <button type="submit" class="btn btn-primary">注销</button>
            </form>
        </div>
    </div>

    <!-- 生成静态文件URL -->
    <script>
        const imageUrls = [
            "{% static 'images/bg1.png' %}",
            "{% static 'images/bg2.png' %}",
            "{% static 'images/bg3.png' %}",
            "{% static 'images/bg4.png' %}",
            "{% static 'images/bg5.png' %}"
        ];
    </script>
    <!-- 引入 JavaScript 文件 -->
    <script src="{% static 'js/toggle.js' %}"></script>
    <script src="{% static 'js/preload.js' %}"></script>
</body>
</html>

```

14.css文件完成

```css
/* 全局样式 */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f3f3f3; /* 设置与图片色调一致的背景色 */
    background-image: url('images/bg1.png'); /* 默认背景图片 */
    background-size: cover; /* 背景图片铺满整个页面 */
    background-position: center; /* 背景图片居中 */
    position: relative; /* 相对定位 */
    transition: background-image 4s ease-in-out; /* 使用 background-image 过渡，时间调整为2秒 */
}

/* 隐藏的图片预加载 */
body::before {
    display: none;
}

/* 切换按钮容器 */
.toggle-container {
    position: absolute;
    top: 20px;
    right: 20px;
    display: flex;
    align-items: center;
    gap: 10px;
}

/* 切换按钮 */
.toggle-button {
    width: 40px;
    height: 20px;
    background-color: #ccc;
    border-radius: 10px;
    position: relative;
    cursor: pointer;
    transition: background-color 0.3s;
}

/* 切换按钮的滑块 */
.toggle-button::before {
    content: '';
    position: absolute;
    top: 2px;
    left: 2px;
    width: 16px;
    height: 16px;
    background-color: white;
    border-radius: 50%;
    transition: transform 0.3s;
}

/* 切换按钮激活状态 */
.toggle-button.active {
    background-color: #55c5ea;
}

.toggle-button.active::before {
    transform: translateX(20px);
}

/* 表单容器 */
.form-container {
    background-color: rgba(255, 255, 255, 0.7); /* 表单背景为白色，透明度90% */
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.7); /* 添加阴影效果 */
    width: 300px; /* 固定宽度 */
    text-align: left; /* 表单内容左对齐 */
    position: relative; /* 相对定位 */
}

/* 标题样式 */
.form-container h1 {
    margin: 0 0 10px 0; /* 上右下左 */
    font-size: 24px;
    color: #333;
    overflow: hidden; /* 隐藏溢出文本 */
    white-space: nowrap; /* 防止文本换行 */
    border-right: 2px solid transparent; /* 初始光标透明 */
    animation: typing 3.5s steps(30, end) forwards 2s; /* 动画结束后保持最后一帧 */
}

/* 打字机动画 */
@keyframes typing {
    from { width: 0; }
    to { width: 100%; }
}

/* 输入框容器 */
.input-group {
    margin-bottom: 16px;
}

/* 输入框样式 */
.input-group input {
    width: 100%; /* 宽度自适应 */
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
    outline: none;
}

/* 输入框占位符样式 */
.input-group input::placeholder {
    color: #aaa;
}

/* 按钮样式 */
.action-button {
    width: 100%; /* 宽度自适应 */
    padding: 10px;
    background-color: #55c5ea;
    color: #fff;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
    margin-bottom: 10px; /* 添加底部边距 */
}

.action-button:hover {
    background-color: #77e8fe;
}

/* 链接样式 */
.form-container a {
    color: #55c5ea;
    text-decoration: none;
}

.form-container a:hover {
    text-decoration: underline;
}

/* 自定义表格样式 */
.table th {
    background-color: #55c5ea;  /* 表头背景色 */
    color: white;  /* 表头文字颜色 */
}

.table-striped tbody tr:nth-of-type(odd) {
    background-color: rgba(85, 197, 234, 0.1);  /* 斑马纹奇数行背景色 */
}

.table-hover tbody tr:hover {
    background-color: rgba(85, 197, 234, 0.2);  /* 悬停行背景色 */
}

```

15.切换背景图按钮逻辑构建完成

```javascript
//toggle.js

// 获取切换按钮和 body 元素
const toggleButton = document.getElementById('toggle-button');
const body = document.body;

// 从 localStorage 中读取用户偏好
const savedSlideshow = localStorage.getItem('slideshow');
if (savedSlideshow === 'enabled') {
    body.classList.add('slideshow');
    toggleButton.classList.add('active');
}

// 切换按钮点击事件
toggleButton.addEventListener('click', () => {
    // 切换轮播状态
    body.classList.toggle('slideshow');

    // 切换按钮状态
    toggleButton.classList.toggle('active');

    // 更新 localStorage
    if (body.classList.contains('slideshow')) {
        localStorage.setItem('slideshow', 'enabled');
    } else {
        localStorage.setItem('slideshow', 'disabled');
    }
});


```

```javascript
//preload

document.addEventListener("DOMContentLoaded", () => {
    // 使用从HTML模板传递过来的URL
    const images = imageUrls;
    let loadedImages = 0; // 用于跟踪已加载的图片数量
    const preloadedImages = []; // 存储预加载的图片

    // 预加载图片
    images.forEach(src => {
        const img = new Image();
        img.src = src;
        img.onload = () => { // 图片加载完成后的回调函数
            loadedImages++; // 增加已加载的图片数量
            preloadedImages.push(img); // 存储预加载的图片
            if (loadedImages === images.length) {
                console.log("所有背景图片已预加载完成");
                // 初始化轮播图
                startSlideshow();
            }
        };
        img.onerror = () => { // 图片加载失败时的回调函数
            console.error(`图片加载失败: ${src}`); // 输出失败的图片 URL
        };
    });

    // 将预加载的图片存储在全局变量中
    window.preloadedImages = preloadedImages;
});

function startSlideshow() {
    let currentIndex = 0;
    const preloadedImages = window.preloadedImages;

    function updateBackgroundImage() {
        if (preloadedImages && preloadedImages.length > 0) {
            document.body.style.backgroundImage = `url(${preloadedImages[currentIndex].src})`;
            currentIndex = (currentIndex + 1) % preloadedImages.length;
        }
    }

    // 初始化轮播图
    setInterval(updateBackgroundImage, 5000); // 每5秒切换一次背景图片
}

```
