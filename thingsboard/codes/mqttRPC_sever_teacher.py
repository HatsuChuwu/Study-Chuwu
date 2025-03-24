#客户端发起订阅请求，控制命令来源于服务端，所以是服务端控制客户端

import json
import paho.mqtt.client as mqtt
import time

# MQTT 服务器配置
BROKER_IP = "210.28.148.162"  # MQTT 代理服务器的 IP 地址
BROKER_PORT = 1883  # MQTT 服务器端口，默认为 1883
TOKEN = "yuzusoft"  # 认证令牌，用作用户名


def on_connect(client, userdata, flags, reason_code, properties):
    """MQTT 连接成功回调函数

    Args:
        client: MQTT 客户端实例
        userdata: 用户定义数据，默认为 None
        flags: 服务器响应的标志
        reason_code: 连接结果代码
        properties: 连接属性（MQTT v5.0）
    """
    print("已成功连接到 MQTT 服务器!")
    client.subscribe("v1/devices/me/rpc/request/+")  # 订阅 RPC 请求主题，'+'为通配符


# msg: 主题topic 字符串；负载payload json
def on_message(client, userdata, msg):
    topic = msg.topic
    print(topic)
    # 属性 v1/devices/me/attributes
    # 遥测数据 v1/devices/me/telemetry
    # 返回数据格式  v1/devices/me/rpc/request/$request_id
    request_id = topic[len("v1/devices/me/rpc/request/"):]
    print(request_id)
    print(msg.payload)
    # 构造响应主题 v1/devices/me/rpc/response/$request_id
    p_topic = f"v1/devices/me/rpc/response/{request_id}"
    # 假设payload是字典，这里示例构造
    client.publish(p_topic, json.dumps({"temp": 108}))
    client.publish("v1/devices/me/telemetry", json.dumps({"temp": 108}))
    # 过程调用，解码消息负载（从二进制字节串转为字符串）
    # 解码消息内容为UTF-8字符串
    payload = msg.payload.decode('utf-8')
    # 将JSON字符串转换为Python字典
    payload_dict = json.loads(payload)
    # 打印原始消息内容
    print(payload)
    # 打印方法名
    print(payload_dict["method"])
    # 获取命令参数
    cmd = payload_dict["params"]["cmd"]
    # 1:开灯 0：关灯 其他：命令不支持
    if cmd == 1:
        print("open LED!")
    elif cmd == 0:
        print("close LED!")
    else:
        print("not support!")


def main():
    """主函数：初始化并运行 MQTT 客户端"""
    # MQTT 客户端初始化
    mqtt_client = mqtt.Client(callback_api_version=mqtt.CallbackAPIVersion.VERSION2)
    mqtt_client.username_pw_set(TOKEN, "")  # 设置认证信息

    # 注册回调函数
    mqtt_client.on_connect = on_connect
    mqtt_client.on_message = on_message

    # 建立连接
    mqtt_client.connect(
        BROKER_IP,  # MQTT 代理服务器地址
        BROKER_PORT,  # 端口号
        60  # keepalive 时间（秒）
    )

    # 启动网络循环（阻塞式）
    mqtt_client.loop_forever()


if __name__ == "__main__":
    main()


# 非阻塞式运行方案（供参考）:
# def main_non_blocking():
#     try:
#         mqtt_client.loop_start()  # 在后台线程启动网络循环
#         while True:
#             time.sleep(3)
#     except KeyboardInterrupt:
#         mqtt_client.loop_stop()    # 停止网络循环
#         mqtt_client.disconnect()   # 断开连接

