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


def on_message(client, userdata, msg):
    """MQTT 消息接收回调函数

    Args:
        client: MQTT 客户端实例
        userdata: 用户定义数据，默认为 None
        msg: 收到的消息对象，包含 topic（主题）和 payload（负载）
    """
    try:
        # 解析消息主题和请求ID
        topic = msg.topic
        request_id = topic.split('/')[-1]

        # 解码消息负载（从字节串转为字符串）
        payload = msg.payload.decode('utf-8')

        # 打印接收到的消息详情
        print(f"""
收到 ThingsBoard 消息:
- 主题 (topic): {topic}
- 负载 (payload): {payload}
        """)

        # RPC 响应示例（已注释）
        # response_topic = f"v1/devices/me/rpc/response/{request_id}"
        # client.publish(response_topic, '{"response": "success"}')

    except Exception as e:
        print(f"处理消息时出错: {e}")


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

