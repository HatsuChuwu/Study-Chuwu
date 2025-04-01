import paho.mqtt.client as mqtt
import time
import json

BROKER_IP = "127.0.0.1"
BROKER_PORT = 1883
TOKEN = "6B5Tf1gWy343FKCKK8Ms"

data_topic = "v1/devices/me/telemetry"

attribute_topic = "v1/devices/me/attributes"

#字典 dict
data = {"m_temp":25,"m_humi":95}
data_attribute = {"m_attri1":55,"m_attri2":"value1"}

def on_connect(client, userdata, flags, rc):
    print("Broker haved been connected!")

def on_message(client,userdata,msg):
    print(msg)

def on_publish(client, userdata, mid):
    print(str(mid))

if __name__ == "__main__":
    mqttClient = mqtt.Client(client_id=TOKEN)
    mqttClient.username_pw_set(TOKEN, "")
    mqttClient.on_connect = on_connect
    mqttClient.on_message = on_message
    mqttClient.on_publish = on_publish

    mqttClient.connect(BROKER_IP, BROKER_PORT, 60)
    while 1:
        print("连接状态")
        #发布消息  主题topic  数据 payload  json
        mqttClient.publish(data_topic,json.dumps(data))
        time.sleep(3)
        mqttClient.publish(attribute_topic, json.dumps(data_attribute))
