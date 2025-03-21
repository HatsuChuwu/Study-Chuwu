import paho.mqtt.client as mqtt
import time
import random
import json  # 引入 JSON 模块

# ThingsBoard 服务器地址和端口
thingsboard_host = "210.28.148.162"
thingsboard_port = 1883

# 设备访问令牌 (device access token)
access_token = "yuzusoft"

# MQTT 主题，已更新为属性发送主题
topic = "v1/devices/me/attributes"
# my_topic = "v1/devices/me/mydata"  # 注释掉 my_topic

# 创建 MQTT 客户端
client = mqtt.Client(client_id="", clean_session=True, userdata=None, protocol=mqtt.MQTTv311, transport="tcp")

# 设置回调 API 版本
client.api_version = mqtt.MQTTv311

# 设置用户名和密码
client.username_pw_set(access_token)

# 启用调试日志
client.enable_logger()


# 定义消息回调函数
def on_message(client, userdata, msg):
    print(f"接收到消息: {msg.topic} -> {msg.payload.decode()}")


# 定义发布回调函数
def on_publish(client, userdata, mid):
    print(f"消息已发布，消息ID: {mid}")


# 定义订阅回调函数
def on_subscribe(client, userdata, mid, granted_qos):
    print(f"成功订阅，消息ID: {mid}，QoS: {granted_qos}")


# 将回调函数绑定到客户端
client.on_message = on_message
client.on_publish = on_publish
client.on_subscribe = on_subscribe  # 绑定 on_subscribe 回调函数

# 连接到 ThingsBoard 服务器
client.connect(thingsboard_host, thingsboard_port, 60)

# 设置自动重连
client.reconnect_delay_set(min_delay=1, max_delay=120)

# 订阅相关主题，确保可以接收到两个主题的消息
client.subscribe("v1/devices/me/attributes/response/+")
client.subscribe("v1/devices/me/attributes/request/+")
client.subscribe("v1/devices/me/attributes/+/+")

# 启动 MQTT 客户端循环
client.loop_start()

print("MQTT 客户端已启动")

while True:
    # 获取当前时间戳（Unix 时间戳）
    timestamp = int(time.time() * 1000)  # 当前时间的 Unix 时间戳

    # 生成随机的温度和湿度数据
    temperature = round(random.uniform(20.0, 30.0), 1)  # 温度在 20.0 到 30.0 之间
    humidity = round(random.uniform(50.0, 70.0), 1)  # 湿度在 50.0 到 70.0 之间

    # 示例数据 1：字典格式
    data1 = {
        "temperature": temperature,
        "humidity": humidity,
        "timestamp": timestamp,
        "sensor_id": "sensor_1"
    }

    # 示例数据 2：列表格式
    data2 = [temperature, humidity, timestamp]

    # 示例数据 3：JSON 格式
    data3 = json.dumps({
        "temperature": temperature,
        "humidity": humidity,
        "timestamp": timestamp,
        "sensor": "sensor_1"
    })

    # 示例数据 4：嵌套字典格式
    data4 = {
        "sensor_data": {
            "temperature": temperature,
            "humidity": humidity
        },
        "timestamp": timestamp,
        "sensor_id": "sensor_1"
    }

    # 发送数据到 ThingsBoard，演示不同的数据格式
    print("正在发送数据到 ThingsBoard...")

    # 使用 QoS 0 发布数据到两个主题
    result1 = client.publish(topic, payload=str(data1), qos=0)  # 发布到 v1/devices/me/attributes
    # result2 = client.publish(my_topic, payload=str(data1), qos=0)  # 发布到 v1/devices/me/mydata, 注释掉

    # 检查发布结果
    if result1.rc == mqtt.MQTT_ERR_SUCCESS:
        print(f"数据已成功发布到主题: {topic}")
    else:
        print(f"数据发布失败，错误码: {result1.rc}")

    # 每隔三秒发送一次
    time.sleep(3)
