/**
 * Created on 2025/3/12
 */
package ohos_app_cangjie_entry

import ohos.base.*
import ohos.component.*
import ohos.state_manage.*
import ohos.state_macro_manage.*
// 导入页面路由模块
import ohos.router.*

@Entry
@Component
class Second {
    @State
    var message: String = "Hi there"

    func build() {
        Row {
            Column() {
                Text(this.message).fontSize(50).fontWeight(FontWeight.Bold)
                Button("Back").onClick {
                    evt => Router.back()
                }.fontSize(30).width(180).height(50).margin(top: 20)
            }.width(100.percent)
        }.height(100.percent)
    }
}
