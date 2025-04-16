<template>
  <div>
    <button :class="['theme-toggle', { 'dark-mode': isDark }]" @click="toggleTheme">
      <i class="fas fa-sun sun-icon"></i>
      <i class="fas fa-moon moon-icon"></i>
    </button>
    
    <div class="calculator">
      <input type="text" id="display" v-model="display" readonly>
      <div class="buttons">
        <!-- 第一行：清除、括号、删除、除号 -->
        <button @click="clearDisplay" class="function-btn">
          <i class="fas fa-c"></i>
        </button>
        <button @click="appendToDisplay('(')" class="operator">
          <i class="fas fa-parentheses"></i>(
        </button>
        <button @click="appendToDisplay(')')" class="operator">
          <i class="fas fa-parentheses"></i>)
        </button>
        <button @click="appendToDisplay('/')" class="operator">
          <i class="fas fa-divide"></i>
        </button>
        <!-- 第二行：数字7、8、9、乘号 -->
        <button @click="appendToDisplay('7')" class="number">7</button>
        <button @click="appendToDisplay('8')" class="number">8</button>
        <button @click="appendToDisplay('9')" class="number">9</button>
        <button @click="appendToDisplay('*')" class="operator">
          <i class="fas fa-times"></i>
        </button>
        <!-- 第三行：数字4、5、6、减号 -->
        <button @click="appendToDisplay('4')" class="number">4</button>
        <button @click="appendToDisplay('5')" class="number">5</button>
        <button @click="appendToDisplay('6')" class="number">6</button>
        <button @click="appendToDisplay('-')" class="operator">
          <i class="fas fa-minus"></i>
        </button>
        <!-- 第四行：数字1、2、3、加号 -->
        <button @click="appendToDisplay('1')" class="number">1</button>
        <button @click="appendToDisplay('2')" class="number">2</button>
        <button @click="appendToDisplay('3')" class="number">3</button>
        <button @click="appendToDisplay('+')" class="operator">
          <i class="fas fa-plus"></i>
        </button>
        <!-- 第五行：数字0、小数点、删除、等于号 -->
        <button @click="appendToDisplay('0')" class="number">0</button>
        <button @click="appendToDisplay('.')" class="number">.</button>
        <button @click="deleteLast" class="function-btn">
          <i class="fas fa-backspace"></i>
        </button>
        <button @click="calculate" class="equal">
          <i class="fas fa-equals"></i>
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'

const display = ref('')
const isDark = ref(false)

// 格式化显示数字
const formatNumber = (num) => {
  if (typeof num !== 'number') return num
  return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
}

// 音效反馈
const playButtonSound = () => {
  const audio = new Audio('data:audio/wav;base64,UklGRhwEAABXQVZFZm10IBAAAAABAAEARKwAAIhYAQACABAAZGF0YfgDAAD/f/9//3//f/9//3//f/9//3//f/9//3//f/9//3//f/9//3//f/9//3//f/9//38AAP4A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gAAAP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4AAAD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0A/gD9AP4A/QD+AP0AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQABAAEAAQABAAEAAQABAAEAAQABAAEAAQABAAEAAQABAAEAAQABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABAAAAAQAAAAEAAAABAAAAAQAAAAEAAAABAAAAAQAAAAEAAAABAAAAAQAAAAEAAAABAAAAAQAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP//AAD//wAA//8AAP//AAD//wAA//8AAP//AAD//wAA//8AAP//AAD//wAA//8AAP//AAD//wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA//8AAP//AAD//wAA//8AAP//AAD//wAA//8AAP//AAD//wAA//8AAP//AAD//wAA//8AAP//')
  audio.volume = 0.2
  audio.play().catch(() => {}) // 忽略自动播放限制错误
}

// 计算器功能
const appendToDisplay = (value) => {
  playButtonSound()
  display.value += value
}

const clearDisplay = () => {
  playButtonSound()
  display.value = ''
}

const deleteLast = () => {
  playButtonSound()
  display.value = display.value.slice(0, -1)
}

const calculate = () => {
  playButtonSound()
  try {
    const result = eval(display.value)
    if (!isFinite(result)) {
      throw new Error('计算结果无效')
    }
    display.value = typeof result === 'number' ? 
      formatNumber(parseFloat(result.toFixed(8))) : 
      result
  } catch (e) {
    display.value = '错误'
    console.error('计算错误:', e.message)
  }
}

// 主题切换
const toggleTheme = () => {
  playButtonSound()
  isDark.value = !isDark.value
  document.body.classList.toggle('dark-mode')
  localStorage.setItem('theme', isDark.value ? 'dark' : 'light')
}

// 键盘事件处理
const handleKeyboard = (e) => {
  const key = e.key
  if (/[\d+\-*\/().=]/.test(key)) {
    e.preventDefault()
    if (key === '=') {
      calculate()
    } else {
      appendToDisplay(key)
    }
  } else if (key === 'Enter') {
    e.preventDefault()
    calculate()
  } else if (key === 'Backspace') {
    e.preventDefault()
    deleteLast()
  } else if (key === 'Escape') {
    e.preventDefault()
    clearDisplay()
  }
}

// 组件挂载和卸载
onMounted(() => {
  // 加载保存的主题
  const savedTheme = localStorage.getItem('theme')
  if (savedTheme === 'dark') {
    isDark.value = true
    document.body.classList.add('dark-mode')
  }

  // 添加键盘事件监听
  document.addEventListener('keydown', handleKeyboard)
})

onUnmounted(() => {
  // 移除键盘事件监听
  document.removeEventListener('keydown', handleKeyboard)
})
</script>