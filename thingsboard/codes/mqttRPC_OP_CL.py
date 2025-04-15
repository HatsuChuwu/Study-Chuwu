import paho.mqtt.client as mqtt
import json
import time

BROKER_IP = "127.0.0.1"  # ThingsBoard MQTT Broker 地址
BROKER_PORT = 1883  # MQTT Broker 端口
TOKEN = "wxit"  # 设备的 Access Token

# 初始化开关和LED状态
round_switch_state = False
led_state = False


# 处理设备的 RPC 请求（setValue）
def on_rpc_request(client, userdata, msg):
    global round_switch_state, led_state
    try:
        payload_str = msg.payload.decode('utf-8')  # 解码消息
        payload_dict = json.loads(payload_str)     # 将消息解析成字典

        print(f"Received RPC request: {payload_dict}")

        # 直接获取布尔值的 params（True 或 False）
        cmd = payload_dict["params"]  # 这里直接获取布尔值

        if cmd:  # 如果为 True，则开启设备
            round_switch_state = True
            led_state = True
            print("Round Switch: ON, LED: ON")
        else:  # 如果为 False，则关闭设备
            round_switch_state = False
            led_state = False
            print("Round Switch: OFF, LED: OFF")

        # 发布更新后的属性
        client.publish("v1/devices/me/attributes", json.dumps({
            "round_switch": round_switch_state,
            "led_state": led_state
        }))

    except Exception as e:
        print(f"Error handling RPC request: {e}")
