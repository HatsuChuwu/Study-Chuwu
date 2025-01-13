from django.contrib.auth import authenticate, login, logout
from django.shortcuts import render, redirect, get_object_or_404
from django.contrib import messages
from django.contrib.auth.decorators import login_required
from .models import IndustryUser, IndustryDevice, DeviceStatusHistory
from django.utils import timezone


# 用户注册
def user_register(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        telephone = request.POST['telephone']

        # 检查用户名是否已存在
        if IndustryUser.objects.filter(username=username).exists():
            messages.error(request, '用户名已存在')
            return redirect('user_register')

        # 创建新用户
        IndustryUser.objects.create(username=username, password=password, telephone=telephone)
        messages.success(request, '注册成功，请登录')
        return redirect('user_login')

    return render(request, '用户注册.html')


# 用户登录
def user_login(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']

        # 验证用户
        user = IndustryUser.objects.filter(username=username, password=password).first()
        if user:
            # 登录成功
            request.session['user_id'] = user.user_id
            request.session['username'] = user.username  # 设置会话中的用户名
            print(f"用户 {username} 登录成功，用户 ID: {user.user_id}")  # 调试信息
            messages.success(request, '登录成功')

            # 直接跳转到设备状态页面
            return redirect('device_status')
        else:
            print(f"用户 {username} 登录失败，用户名或密码错误")  # 调试信息
            messages.error(request, '用户名或密码错误')
            return redirect('user_login')

    return render(request, '用户登录.html')

# 管理员登录
def admin_login(request):
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']

        # 验证管理员
        user = authenticate(request, username=username, password=password)
        if user is not None and user.is_staff:  # 检查用户是否是管理员
            login(request, user)
            messages.success(request, '管理员登录成功')
            return redirect('user_list')
        else:
            messages.error(request, '管理员名或密码错误')
            return redirect('admin_login')

    return render(request, '管理员登录.html')


# 用户注销
def user_logout(request):
    if 'user_id' in request.session:
        del request.session['user_id']
    if request.user.is_authenticated:
        logout(request)
    messages.success(request, '已注销')
    return redirect('user_login')


# 用户列表（无需权限）
def user_list(request):
    users = IndustryUser.objects.all()
    return render(request, '用户列表.html', {'users': users})


# 设备状态页面（无需登录）
def device_status(request):
    devices = IndustryDevice.objects.all()
    return render(request, '设备状态.html', {'devices': devices})

# 添加设备
@login_required
def add_device(request):
    if request.method == 'POST':
        device_id = request.POST['device_id']
        device_type = request.POST['device_type']
        device_state = request.POST['device_state']

        # 检查设备 ID 是否已存在
        if IndustryDevice.objects.filter(device_id=device_id).exists():
            messages.error(request, '设备 ID 已存在')
            return redirect('add_device')

        # 创建新设备
        IndustryDevice.objects.create(device_id=device_id, device_type=device_type, device_state=device_state)
        messages.success(request, '设备添加成功')
        return redirect('device_status')

    return render(request, 'add_device.html')


# 更新设备信息
@login_required
def update_device(request, device_id):
    device = get_object_or_404(IndustryDevice, device_id=device_id)

    if request.method == 'POST':
        device.device_type = request.POST['device_type']
        device.device_state = request.POST['device_state']
        device.save()
        messages.success(request, '设备信息更新成功')
        return redirect('device_status')

    return render(request, 'update_device.html', {'device': device})


# 删除设备
@login_required
def delete_device(request, device_id):
    device = get_object_or_404(IndustryDevice, device_id=device_id)
    device.delete()
    messages.success(request, '设备删除成功')
    return redirect('device_status')


# 仪表板（数据展示）
@login_required
def dashboard(request):
    devices = IndustryDevice.objects.all()
    devices_online = devices.filter(device_state='在线').count()
    devices_offline = devices.filter(device_state='离线').count()

    # 计算每个设备的在线时长和故障次数
    device_history = {}
    for device in devices:
        history = DeviceStatusHistory.objects.filter(device=device).order_by('timestamp')
        online_duration = 0  # 在线时长（秒）
        failure_count = 0  # 故障次数
        previous_state = None
        previous_time = None

        for record in history:
            if previous_state == '在线' and record.state == '离线':
                # 从在线切换到离线，计算在线时长
                online_duration += (record.timestamp - previous_time).total_seconds()
            if record.state == '离线':
                failure_count += 1  # 记录故障次数
            previous_state = record.state
            previous_time = record.timestamp

        # 如果设备当前状态为“在线”，则计算从最后一次记录到当前时间的在线时长
        if device.device_state == '在线' and previous_time:
            online_duration += (timezone.now() - previous_time).total_seconds()

        device_history[device.device_id] = {
            'online_duration': online_duration,
            'failure_count': failure_count
        }

    return render(request, 'dashboard.html', {
        'devices': devices,
        'devices_online': devices_online,
        'devices_offline': devices_offline,
        'device_history': device_history  # 传递设备历史数据
    })

# 更新用户信息
def update_user(request, user_id):
    user = get_object_or_404(IndustryUser, user_id=user_id)

    if request.method == 'POST':
        # 获取表单数据
        username = request.POST['username']
        telephone = request.POST['telephone']

        # 更新用户信息
        user.username = username
        user.telephone = telephone
        user.save()

        messages.success(request, '用户信息更新成功')
        return redirect('user_list')

    return render(request, 'update_user.html', {'user': user})

# 删除用户
def delete_user(request, user_id):
    user = get_object_or_404(IndustryUser, user_id=user_id)

    if request.method == 'POST':
        # 执行删除操作
        user.delete()
        messages.success(request, '用户删除成功')
        return redirect('user_list')

    # 渲染确认页面
    return render(request, 'delete_user.html', {'user': user})