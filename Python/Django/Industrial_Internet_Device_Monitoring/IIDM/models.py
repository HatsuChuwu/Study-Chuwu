from django.db import models

class IndustryUser(models.Model):
    user_id = models.AutoField(primary_key=True)
    username = models.CharField(max_length=100, unique=True)
    password = models.CharField(max_length=100)
    telephone = models.CharField(max_length=15)

    def __str__(self):
        return self.username

class IndustryAdmin(models.Model):
    admin_id = models.AutoField(primary_key=True)
    admin_name = models.CharField(max_length=100, unique=True)
    admin_password = models.CharField(max_length=100)

    def __str__(self):
        return self.admin_name

class IndustryDevice(models.Model):
    device_id = models.CharField(max_length=100, primary_key=True)  # 设备 ID
    device_type = models.CharField(max_length=100)  # 设备类型
    device_state = models.CharField(max_length=10, choices=[('在线', '在线'), ('离线', '离线')])  # 设备状态
    device_update_time = models.DateTimeField(auto_now=True)  # 最后更新时间

    def __str__(self):
        return f"{self.device_type} ({self.device_state})"

class DeviceStatusHistory(models.Model):
    device = models.ForeignKey(IndustryDevice, on_delete=models.CASCADE, related_name='status_history')  # 关联设备
    state = models.CharField(max_length=10, choices=[('在线', '在线'), ('离线', '离线')])  # 设备状态
    timestamp = models.DateTimeField(auto_now_add=True)  # 状态变化时间

    def __str__(self):
        return f"{self.device.device_id} - {self.state} at {self.timestamp}"