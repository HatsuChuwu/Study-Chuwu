import paho.mqtt.client as mqtt
import time
import random
import json

# ThingsBoard 服务器地址和端口
thingsboard_host = "210.28.148.162"
thingsboard_port = 1883

# 设备访问令牌 (device access token)
access_token = "yuzusoft"

# MQTT 主题
telemetry_topic = "v1/devices/me/telemetry"
attributes_topic = "v1/devices/me/attributes"

# 创建 MQTT 客户端
client = mqtt.Client(client_id="", clean_session=True, userdata=None, protocol=mqtt.MQTTv311, transport="tcp")

# 设置回调 API 版本
client.api_version = mqtt.MQTTv311

# 设置用户名和密码
client.username_pw_set(access_token)

# 连接到 ThingsBoard 服务器
client.connect(thingsboard_host, thingsboard_port, 60)

# 启动 MQTT 客户端循环
client.loop_start()


while True:
    # 获取当前时间戳（毫秒）
    timestamp = int(time.time() * 1000)

    # 生成随机的温度和湿度数据
    temperature = round(random.uniform(20.0, 30.0), 1)
    humidity = round(random.uniform(50.0, 70.0), 1)

    # 传送的遥测数据
    telemetry_data = {
        "temperature": temperature,
        "humidity": humidity,
        "timestamp": timestamp  # 添加时间戳字段
    }

    # 传送的属性数据
    attributes_data = {
        "device_type": "temperature_sensor",
        "location": "room_1",
        "firmware_version": "1.0.0",
        "lastUpdateTime": timestamp
    }

    # 发送数据到 ThingsBoard (使用 json.dumps 转换为 JSON 字符串)
    client.publish(telemetry_topic, payload=json.dumps(telemetry_data), qos=1)
    client.publish(attributes_topic, payload=json.dumps(attributes_data), qos=1)

    print(f"遥测数据已发送: {telemetry_data}")
    print(f"属性数据已发送: {attributes_data}")

    time.sleep(3)