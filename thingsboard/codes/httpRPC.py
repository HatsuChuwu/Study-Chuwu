import requests
import time
import json


# ThingsBoard API URL
url = "http://210.28.148.162:8080/api/v1/yuzusoft/rpc"

# 循环每3秒发送请求
while True:
    response = requests.get(url=url)
    print(response.status_code)
    print(response.text)

    if response.status_code == 200:
        jsondata = json.loads(response.text)

        if jsondata["params"]["on"] == 1:
            print("Open Led")
        else:
            print("Close Led")

    time.sleep(3)
