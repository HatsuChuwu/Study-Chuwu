import requests
import time

# ThingsBoard 服务器地址
thingsboard_url = "http://210.28.148.162:8080/api/v1/{access_token}/telemetry"

# 设备访问令牌 (device access token)
access_token = "yuzusoft"

while True:
    # 固定的温度和湿度数据
    temperature = 23.0
    humidity = 37.3

    # 传送的数据 (例如，传感器数据)
    data = {
        "temperature": temperature,
        "humidity": humidity
    }

    # 发送数据到 ThingsBoard
    response = requests.post(thingsboard_url.format(access_token=access_token), json=data)

    # 检查响应状态
    if response.status_code == 200:
        print("数据已成功发送到 ThingsBoard")
    else:
        print(f"请求失败，状态码: {response.status_code}, 错误信息: {response.text}")

    # 每隔三秒发送一次
    time.sleep(3)
    