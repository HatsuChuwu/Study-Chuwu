import paho.mqtt.client as mqtt
import time
import random
import json

# ThingsBoard 服务器地址和端口
thingsboard_host = "210.28.148.162"
thingsboard_port = 1883

# 设备访问令牌
access_token1 = "yuzusoft"
access_token2 = "yuzu2"
access_token3 = "yuzu3"

# MQTT 主题
telemetry_topic = "v1/devices/me/telemetry"
attributes_topic = "v1/devices/me/attributes"

# 创建三个 MQTT 客户端
client1 = mqtt.Client(client_id="device1", clean_session=True, userdata=None, protocol=mqtt.MQTTv311, transport="tcp")
client1.api_version = mqtt.MQTTv311
client1.username_pw_set(access_token1)
client1.connect(thingsboard_host, thingsboard_port, 60)
client1.loop_start()

client2 = mqtt.Client(client_id="device2", clean_session=True, userdata=None, protocol=mqtt.MQTTv311, transport="tcp")
client2.api_version = mqtt.MQTTv311
client2.username_pw_set(access_token2)
client2.connect(thingsboard_host, thingsboard_port, 60)
client2.loop_start()

client3 = mqtt.Client(client_id="device3", clean_session=True, userdata=None, protocol=mqtt.MQTTv311, transport="tcp")
client3.api_version = mqtt.MQTTv311
client3.username_pw_set(access_token3)
client3.connect(thingsboard_host, thingsboard_port, 60)
client3.loop_start()

while True:
    timestamp = int(time.time() * 1000)

    # 设备1(yuzusoft)的遥测数据和属性数据
    temperature1 = round(random.uniform(20.0, 30.0), 1)
    humidity1 = round(random.uniform(50.0, 70.0), 1)
    telemetry_data1 = {
        "temperature": temperature1,
        "humidity": humidity1,
        "timestamp": timestamp
    }
    attributes_data1 = {
        "temperature": temperature1,
        "humidity": humidity1,
        "timestamp": timestamp
    }

    # 设备2的遥测数据
    temperature2 = round(random.uniform(25.0, 35.0), 1)
    humidity2 = round(random.uniform(45.0, 65.0), 1)
    telemetry_data2 = {
        "temperature": temperature2,
        "humidity": humidity2,
        "timestamp": timestamp
    }

    # 设备3的属性数据
    temperature3 = round(random.uniform(20.0, 30.0), 1)
    humidity3 = round(random.uniform(50.0, 70.0), 1)
    attributes_data3 = {
        "temperature": temperature3,
        "humidity": humidity3,
        "timestamp": timestamp
    }

    # 发送设备1的遥测数据和属性数据
    client1.publish(telemetry_topic, payload=json.dumps(telemetry_data1), qos=1)
    client1.publish(attributes_topic, payload=json.dumps(attributes_data1), qos=1)
    print(f"设备1遥测数据已发送: {telemetry_data1}")
    print(f"设备1属性数据已发送: {attributes_data1}")

    # 发送设备2的遥测数据
    client2.publish(telemetry_topic, payload=json.dumps(telemetry_data2), qos=1)
    print(f"设备2遥测数据已发送: {telemetry_data2}")

    # 发送设备3的属性数据
    client3.publish(attributes_topic, payload=json.dumps(attributes_data3), qos=1)
    print(f"设备3属性数据已发送: {attributes_data3}")

    time.sleep(3)
