import requests
import time
import random
import datetime

# ThingsBoard 服务器地址
thingsboard_url = "http://210.28.148.162:8080/api/v1/{access_token}/telemetry"

# 设备访问令牌 (device access token)
access_token = "yuzusoft"

while True:
    # 获取当前时间戳（Unix 时间戳）
    timestamp = int(time.time()*1000)  # 当前时间的 Unix 时间戳

    # 生成随机的温度和湿度数据
    temperature = round(random.uniform(20.0, 30.0), 1)  # 温度在 20.0 到 30.0 之间
    humidity = round(random.uniform(50.0, 70.0), 1)     # 湿度在 50.0 到 70.0 之间

    # 传送的数据
    data = {
        "ts": timestamp,  # 时间戳
        "values": {
            "temperature": temperature,
            "humidity": humidity
        }
    }

    # 发送数据到 ThingsBoard
    response = requests.post(thingsboard_url.format(access_token=access_token), json=data)

    # 检查响应状态
    if response.status_code == 200:
        print(f"数据已成功发送到 ThingsBoard，时间戳: {timestamp}")
    else:
        print(f"请求失败，状态码: {response.status_code}, 错误信息: {response.text}")

    # 每隔三秒发送一次
    time.sleep(3)
