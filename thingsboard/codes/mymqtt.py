import paho.mqtt.client as mqtt
import time
import json

BROKER_IP = "127.0.0.1"
BROKER_PORT = 1883
TOKEN = "wxit"

data_topic = "v1/devices/me/telemetry"
attribute_topic = "v1/devices/me/attributes"

# 添加纬度和经度到字典中
data = {
    "m_temp": 25,
    "m_humi": 95,
    "latitude": 31.499236,  # 纬度
    "longitude": 120.27418  # 经度
}

data_attribute = {
    "m_attri1": 55,
    "m_attri2": "value1"
}


def on_connect(client, userdata, flags, rc):
    print("Broker has been connected!")


def on_message(client, userdata, msg):
    print(msg)


def on_publish(client, userdata, mid):
    print(f"Message published with ID: {mid}")


if __name__ == "__main__":
    mqttClient = mqtt.Client(client_id=TOKEN)
    mqttClient.username_pw_set(TOKEN, "")
    mqttClient.on_connect = on_connect
    mqttClient.on_message = on_message
    mqttClient.on_publish = on_publish

    mqttClient.connect(BROKER_IP, BROKER_PORT, 60)

    while True:
        print("连接状态")

        # 发布消息到设备的 telemetry topic
        mqttClient.publish(data_topic, json.dumps(data))
        time.sleep(3)

        # 发布消息到设备的 attributes topic
        mqttClient.publish(attribute_topic, json.dumps(data_attribute))
