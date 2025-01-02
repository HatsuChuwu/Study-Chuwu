// 将值追加到显示框中
function appendToDisplay(value) {
    const display = document.getElementById('display'); // 获取显示框元素
    display.value += value; // 将值追加到显示框中
}

// 清除显示框中的内容
function clearDisplay() {
    const display = document.getElementById('display'); // 获取显示框元素
    display.value = ''; // 清空显示框
}

// 删除显示框中的最后一个字符
function deleteLast() {
    const display = document.getElementById('display'); // 获取显示框元素
    display.value = display.value.slice(0, -1); // 删除最后一个字符
}

// 计算显示框中的表达式并显示结果
function calculate() {
    const display = document.getElementById('display'); // 获取显示框元素
    try {
        display.value = eval(display.value); // 使用 eval 计算表达式
    } catch (e) {
        display.value = '错误'; // 如果计算出错，显示“错误”
    }
}