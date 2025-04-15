
import os
import appbuilder
# 设置环境变量和初始化
os.environ["APPBUILDER_TOKEN"] = "bce-v3/ALTAK-58y8veEVCiCi7zOIzaoPu/a9a7c6d8997f59a85e1c92e2ea6b19ff88a1df20"

app_id = "4ae23254-fdf2-4241-b064-8b39cc243625"

app_builder_client = appbuilder.AppBuilderClient(app_id)
conversation_id = app_builder_client.create_conversation()

resp = app_builder_client.run(conversation_id, "我记得你有搜索组件，能帮我搜索一下今日金价吗？")
print(resp.content.answer)